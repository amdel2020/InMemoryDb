import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    private final String tableName = "TestTable";

    private Database db;

    @BeforeEach
    public void setupDb() {
        String dbName = "TestDb";
        db = new Database(dbName);
    }

    @Test
    public void testIfCreateTableReturnTrueAfterCreation() {
        Set<Column<?>> columns = new HashSet<>();
        columns.add(new Column<>("Id", String.class));
        columns.add(new Column<>("Name", String.class));

        // Create table
        boolean isCreated = db.createTable(tableName, columns);

        assertTrue(isCreated);
    }

    @Test
    public void testIfTableExistsThenCreateTableReturnsFalse() {
        testIfCreateTableReturnTrueAfterCreation();

        assertFalse(db.createTable(tableName, null));
    }

    @Test
    public void testIfGetTableNameReturnTableName() {
        testIfCreateTableReturnTrueAfterCreation();

        final Optional<Table> tableByName = db.getTableByName(tableName);

        assertTrue(tableByName.isPresent());
        assertEquals(tableByName.get().getName(), tableName);
    }

    @Test
    public void testIfGetTableByNameReturnsEmptyIfNoTableExists() {
        final Optional<Table> table = db.getTableByName(tableName);
        assertTrue(table.isEmpty());
    }

    @Test
    public void testIfGetTableNameReturnEmptyTableNotExists() {
        testIfCreateTableReturnTrueAfterCreation();

        final Optional<Table> tableByName = db.getTableByName("TableNotExists");

        assertEquals(1, db.size());
        assertTrue(tableByName.isEmpty());
    }

    @Test
    void testIfGetAllTableNamesReturnCorrectTableNames() {
        testIfCreateTableReturnTrueAfterCreation();

        final List<String> tables = db.getAllTableNames();
        assertEquals(1, db.size());
        assertIterableEquals(List.of(tableName), tables);

    }

    @Test
    void testIfGetAllTableNamesReturnEmptyIfNoTableExists() {
        final List<String> tables = db.getAllTableNames();
        assertEquals(0, tables.size());
    }

    @Test
    public void testIfDeleteTableReturnsTrueIfGivenTableDeleted() {
        testIfCreateTableReturnTrueAfterCreation();

        assertEquals(1, db.size());

        final boolean isDeleted = db.deleteTable(tableName);
        assertTrue(isDeleted);
        assertEquals(0, db.size());
    }

    @Test
    public void testIfDeleteTableReturnsFalseIfGivenTableNotExists() {
        testIfCreateTableReturnTrueAfterCreation();

        final boolean isDeleted = db.deleteTable("RandomName");
        assertFalse(isDeleted);
    }

    @Test
    public void testIfDeleteTableReturnsFalseIfNoTableExists() {
        final boolean isDeleted = db.deleteTable(tableName);
        assertFalse(isDeleted);
    }
}