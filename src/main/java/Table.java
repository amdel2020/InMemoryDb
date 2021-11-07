import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Data
@Builder
public class Table {
    private String name;
    private Map<String, Row> rows;
    private Map<String, Column<?>> columns;

    public long size() {
        return rows.size();
    }

    public boolean insert(Row row) {

        if (row == null) {
            return false;
        }

        // if row already exists, return false
        if (rows.containsKey(row.getRowId())) {
            return false;
        }

        try {
            rows.put(row.getRowId(), row);
            return true;
        } catch (UnsupportedOperationException
                | ClassCastException
                | NullPointerException
                | IllegalArgumentException
                | IllegalStateException ex) {
            return false;
        }
    }

    public boolean update(Row row) {
        return false;
    }

    public boolean delete(String rowId) {
        return false;
    }

    public Optional<Row> getByPrimaryKey(String rowId) {
        return Optional.empty();
    }

    public Optional<Set<Row>> getAllRows() {
        return Optional.empty();
    }
}
