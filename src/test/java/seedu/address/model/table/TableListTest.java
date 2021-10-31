package seedu.address.model.table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TABLE_ID_HUNDRED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TABLE_ID_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TABLE_SIZE_HUNDRED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TABLE_SIZE_ONE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.table.exception.TableNotFoundException;

class TableListTest {
    public static final Table DUMMY_TABLE = new Table(VALID_TABLE_SIZE_HUNDRED, VALID_TABLE_ID_HUNDRED);
    public static final Table TABLE_NOT_IN_LIST = new Table(VALID_TABLE_SIZE_ONE, VALID_TABLE_ID_ONE);

    private TableList tableList = new TableList();

    @Test
    public void contains_nullTable_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tableList.contains(null));
    }

    @Test
    public void contains_tableNotInList_returnsFalse() {
        assertFalse(tableList.contains(TABLE_NOT_IN_LIST));
    }

    @Test
    public void contains_tableInList_returnsTrue() {
        tableList.add(DUMMY_TABLE);
        assertTrue(tableList.contains(DUMMY_TABLE));
    }

    @Test
    public void add_nullTable_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tableList.add(null));
    }

    @Test
    public void setTable_nullTargetTable_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                tableList.setTable(null, TABLE_NOT_IN_LIST));
    }

    @Test
    public void setTable_nullEditedTable_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                tableList.setTable(DUMMY_TABLE, null));
    }

    @Test
    public void setTable_targetTableNotInList_throwsTableNotFoundException() {
        assertThrows(TableNotFoundException.class, () ->
                tableList.setTable(TABLE_NOT_IN_LIST, DUMMY_TABLE));
    }

    @Test
    public void setTable_editedTableIsSameTable_success() {
        tableList.add(DUMMY_TABLE);
        tableList.setTable(DUMMY_TABLE, DUMMY_TABLE);
        TableList expected = new TableList();
        expected.add(DUMMY_TABLE);
        assertEquals(expected, tableList);
    }

    @Test
    public void setTable_editedTableOfDifferentIdentity() {
        tableList.add(DUMMY_TABLE);
        tableList.setTable(DUMMY_TABLE, TABLE_NOT_IN_LIST);
        TableList expected = new TableList();
        expected.add(TABLE_NOT_IN_LIST);
        assertEquals(expected, tableList);
    }

    @Test
    public void remove_nullTable_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                tableList.remove(null));
    }

    @Test
    public void remove_reservationDoesNotExist_throwsTableNotFoundException() {
        assertThrows(TableNotFoundException.class, () ->
                tableList.remove(TABLE_NOT_IN_LIST));
    }

    @Test
    public void remove_reservationInList_success() {
        tableList.add(DUMMY_TABLE);
        tableList.remove(DUMMY_TABLE);
        TableList expected = new TableList();
        assertEquals(expected, tableList);
    }

    @Test
    public void setTables_nullTableList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                tableList.setTables((TableList) null));
    }

    @Test
    public void setTables_validTableList_success() {
        tableList.add(DUMMY_TABLE);
        TableList expected = new TableList();
        expected.add(TABLE_NOT_IN_LIST);
        tableList.setTables(expected);
        assertEquals(expected, tableList);
    }

    @Test
    public void setTables_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                tableList.setTables((List<Table>) null));
    }

    @Test
    public void setTables_validList_success() {
        tableList.add(DUMMY_TABLE);
        List<Table> validList = Collections.singletonList(DUMMY_TABLE);
        tableList.setTables(validList);
        TableList expected = new TableList();
        expected.add(DUMMY_TABLE);
        assertEquals(expected, tableList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                tableList.asUnmodifiableObservableList().remove(0));
    }
}
