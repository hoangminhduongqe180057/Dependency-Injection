import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {

}

// Tạo 1 annotation để đánh dấu các dependence muốn inject.