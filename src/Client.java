
// Client sử dụng Service.
public class Client {
	@Inject
	private Service service;

	public void doWork() {
		service.perform();
	}

}
