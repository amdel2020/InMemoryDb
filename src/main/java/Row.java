import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;

@Data
public class Row {
    private String rowId;
    private HashMap<String, Cell<?>> cells;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
}
