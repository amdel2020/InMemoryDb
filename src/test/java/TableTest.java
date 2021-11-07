import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    private Table testTable;

    @BeforeEach
    public void setupDbAndTable() {
        //Create DB with columns
        Database db = new Database("TestDb");
        Set<Column<?>> columns = new HashSet<>();
        columns.add(new Column<>("Id", String.class));
        columns.add(new Column<>("Name", String.class));

        // Create table
        boolean isCreated = db.createTable("TestTable", columns);
        assertTrue(isCreated);

        Optional<Table> optionalTable = db.getTableByName("TestTable");
        assertTrue(optionalTable.isPresent());

        testTable = optionalTable.get();
    }

    @Test
    public void testIfRowInsertedThenInsertReturnsTrue() {

        // Insert a row
        Row row = new Row();
        row.setRowId("1");
        row.setUpdatedAt(LocalDateTime.now());
        boolean isInserted = testTable.insert(row);

        assertTrue(isInserted);
        assertEquals(1, testTable.size());
    }

    @Test
    public void testIfRowIsNullThenInsertReturnsFalse() {
        assertFalse(testTable.insert(null));
    }

    @Test
    public void testIfRowAlreadyPresentThenInsertReturnsFalse() {
        testIfRowInsertedThenInsertReturnsTrue();

        Row row = new Row();
        row.setRowId("1");

        assertFalse(testTable.insert(row));
        assertEquals(1, testTable.size());
    }
}