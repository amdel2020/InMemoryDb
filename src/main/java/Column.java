import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Column<T> {
    private String name;
    private Class<T> type;
}
