import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Component {

}
// @Component sẽ được sử dụng để đánh dấu các class muốn tự động tạo object và inject