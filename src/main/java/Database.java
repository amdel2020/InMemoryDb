import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Database {
    private final String name;
    private final HashMap<String, Table> tables;

    public Database(String name) {
        this.name = name;
        tables = new HashMap<>();
    }

    public List<String> getAllTableNames() {
        return new ArrayList<>(this.tables.keySet());
    }

    public Optional<Table> getTableByName(String tableName) {
        if (tables.isEmpty()) {
            return Optional.empty();
        }

        if (!tables.containsKey(tableName)) {
            return Optional.empty();
        }

        return Optional.of(tables.get(tableName));
    }

    public boolean createTable(String name, Set<Column<?>> columns) {

        if (tables.containsKey(name)) {
            return false;
        }

        Map<String, Column<?>> colsMap = columns.stream()
                .collect(Collectors.toMap(Column::getName, v -> v));

        Table table = Table.builder()
                .name(name)
                .rows(new HashMap<>())
                .columns(colsMap)
                .build();

        tables.put(name, table);
        return true;
    }

    public boolean deleteTable(String name) {
        if (size() == 0) {
            return false;
        }

        if (getTableByName(name).isEmpty()) {
            return false;
        }

        tables.remove(name);
        return true;
    }

    public long size() {
        return tables.size();
    }
}
