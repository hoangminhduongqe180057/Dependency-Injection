import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

public class DependencyInjector {

	// Map để giữ các instance đã được tạo
	private Map<Class<?>, Object> dependencyInstances = new HashMap<>();

	public DependencyInjector(String b) {
		// Scan tất cả components trong các package được chỉ định
		scanForComponents(b);
	}

	public DependencyInjector() {

	}

	// Hàm để scan và tạo instance cho tất cả các class có @Component
	private void scanForComponents(String b) {
		Reflections reflections = new Reflections(b);
		Set<Class<?>> componentClasses = reflections.getTypesAnnotatedWith(Component.class);
		for (Class<?> componentClass : componentClasses) {
			Object instance = getDependencyInstance(componentClass);
			dependencyInstances.put(componentClass, instance);
		}
	}

	// Hàm để inject các dependency vào object
	public void injectDependencies(Object object) {
		Class<?> c = object.getClass();
		Field[] fields = c.getDeclaredFields();

		for (Field field : fields) {
			if (field.isAnnotationPresent(Inject.class)) {
				Object dependency = getDependencyInstance(field.getType());
				field.setAccessible(true);

				try {
					field.set(object, dependency);
				} catch (IllegalAccessException e) {
					throw new RuntimeException("Failed to inject dependency: " + field.getType(), e);
				}
			}
		}
	}

	// Hàm tạo instance cho 1 dependency
	Object getDependencyInstance(Class<?> c) {
		if (dependencyInstances.containsKey(c)) {
			return dependencyInstances.get(c);
		}

		try {
			Object instance = c.getDeclaredConstructor().newInstance();
			dependencyInstances.put(c, instance);
			injectDependencies(instance);
			return instance;
		} catch (Exception e) {
			throw new RuntimeException("Failed to create instance of: " + c, e);
		}
	}
}
