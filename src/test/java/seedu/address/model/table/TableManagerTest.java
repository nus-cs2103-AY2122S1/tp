package seedu.address.model.table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalTables.DUMMY_LIST_OF_TABLES;
import static seedu.address.testutil.TypicalTables.DUMMY_TABLE_1;
import static seedu.address.testutil.TypicalTables.DUMMY_TABLE_3;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.reservation.exception.ReservationException;

class TableManagerTest {
    @Test
    public void constructor_nullArgument_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TableManager(null));
    }

    @Test
    public void getNumberOfTablesTest() {
        TableList tableList = new TableList();
        tableList.setTables(DUMMY_LIST_OF_TABLES);
        TableManager tableManager = new TableManager(tableList);

        assertEquals(tableManager.getNumberOfTables(), DUMMY_LIST_OF_TABLES.size());
    }

    @Test
    public void getAvailableTable_validInputs_returnsCorrectTable() throws ReservationException {
        TableList tableList = new TableList();
        tableList.setTables(DUMMY_LIST_OF_TABLES);
        TableManager tableManager = new TableManager(tableList);

        assertEquals(DUMMY_TABLE_1, tableManager.getAvailableTable(1, List.of()));
        assertEquals(DUMMY_TABLE_3, tableManager.getAvailableTable(3, List.of()));
        assertThrows(ReservationException.class, () -> tableManager.getAvailableTable(6, List.of()));
    }

    @Test
    public void getAvailableTable_validInputsTablesNotSorted_returnsSmallestValidTable() throws ReservationException {
        Table table1 = new Table(4, 1);
        Table table2 = new Table(3, 2);
        Table table3 = new Table(10, 3);
        Table table4 = new Table(6, 4);
        Table table5 = new Table(1, 5);
        TableList tableList = new TableList();
        tableList.setTables(List.of(table1, table2, table3, table4, table5));
        TableManager tableManager = new TableManager(tableList);

        assertEquals(table5, tableManager.getAvailableTable(1, List.of()));
        assertEquals(table2, tableManager.getAvailableTable(3, List.of()));
        assertEquals(table3, tableManager.getAvailableTable(10, List.of()));
        assertThrows(ReservationException.class, () -> tableManager.getAvailableTable(11, List.of()));
    }

    @Test
    public void getAvailableTable_validInputsNoTables_throwsException() throws ReservationException {
        TableList tableList = new TableList();
        TableManager tableManager = new TableManager(tableList);

        assertThrows(ReservationException.class, () -> tableManager.getAvailableTable(1, List.of()));
    }

    @Test
    public void equals() {
        TableList tableList = new TableList();
        tableList.setTables(DUMMY_LIST_OF_TABLES);
        TableManager tableManager = new TableManager(tableList);

        TableList tableList2 = new TableList();
        tableList2.setTables(DUMMY_LIST_OF_TABLES);
        TableManager tableManagerCopied = new TableManager(tableList2);

        // same object -> returns true
        assertTrue(tableManager.equals(tableManager));

        // same values -> returns true
        assertTrue(tableManager.equals(tableManagerCopied));

        // different types -> returns false
        assertFalse(tableManager.equals(2));

        // null -> returns false
        assertFalse(tableManager.equals(null));

        // different tableList -> returns false
        assertFalse(tableManager.equals(new TableManager()));
    }

}
