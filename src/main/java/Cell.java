import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cell<T> {
    private String name;
    private T value;
}
