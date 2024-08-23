
public class Main {
	public static void main(String[] args) {
		DependencyInjector injector = new DependencyInjector();
		Client client = new Client();

		injector.injectDependencies(client);
		client.doWork();

		DependencyInjector _injector = new DependencyInjector("default package");
		Service service = (Service) _injector.getDependencyInstance(Service.class);
		service.execute();
	}
}
