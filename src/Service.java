
// Service cần được inject vào một class khác.
@Component
public class Service {
	@Inject
	private Repository repository;

	public void execute() {
		repository.save();
	}

	public void perform() {
		System.out.println("Service is performing...");
	}

}
