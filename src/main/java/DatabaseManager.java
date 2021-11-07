import lombok.Data;

import java.util.HashMap;

@Data
public class DatabaseManager {
    private HashMap<String, Database> databases;
}
