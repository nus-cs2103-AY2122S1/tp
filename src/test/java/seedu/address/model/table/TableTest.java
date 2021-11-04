package seedu.address.model.table;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TABLE_ID_HUNDRED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TABLE_ID_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TABLE_SIZE_HUNDRED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TABLE_SIZE_ONE;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class TableTest {

    @Test
    public void constructor_invalidTableSize_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Table(0));
        assertThrows(IllegalArgumentException.class, () -> new Table(-1));
    }

    @Test
    public void constructor_invalidTableSizeValidTableId_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Table(0, 1));
        assertThrows(IllegalArgumentException.class, () -> new Table(-1, 2));
    }

    @Test
    public void constructor_invalidTableSizeInvalidTableId_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Table(0, 0));
        assertThrows(IllegalArgumentException.class, () -> new Table(0, -1));
        assertThrows(IllegalArgumentException.class, () -> new Table(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> new Table(-1, -1));
    }

    @Test
    public void isValidTable() {
        // invalid tables
        assertFalse(Table.checkIfValidValue(0));
        assertFalse(Table.checkIfValidValue(-1));
        assertFalse(Table.checkIfValidValue(Integer.MIN_VALUE));

        // valid phone numbers
        assertTrue(Table.checkIfValidValue(1));
        assertTrue(Table.checkIfValidValue(2));
        assertTrue(Table.checkIfValidValue(10));
        assertTrue(Table.checkIfValidValue(Integer.MAX_VALUE));
    }

    @Test
    public void canFit() {
        Table table = new Table(VALID_TABLE_SIZE_HUNDRED, VALID_TABLE_ID_HUNDRED);
        assertTrue(table.canFit(1));
        assertTrue(table.canFit(99));
        assertTrue(table.canFit(100));

        assertFalse(table.canFit(101));
        assertFalse(table.canFit(102));
        assertFalse(table.canFit(Integer.MAX_VALUE));
        assertFalse(table.canFit(Integer.MAX_VALUE + 1));
    }

    @Test
    public void equals() {
        Table table = new Table(VALID_TABLE_SIZE_HUNDRED, VALID_TABLE_ID_HUNDRED);

        // same values -> returns true
        Table toCopy = new Table(VALID_TABLE_SIZE_HUNDRED, VALID_TABLE_ID_HUNDRED);
        assertTrue(table.equals(toCopy));

        // same object -> returns true
        assertTrue(table.equals(table));

        // null -> returns false
        assertFalse(table.equals(null));

        // different type -> returns false
        assertFalse(table.equals(5));

        // different table size -> returns false
        Table differentSize = new Table(VALID_TABLE_SIZE_ONE, VALID_TABLE_ID_HUNDRED);
        assertFalse(table.equals(differentSize));

        // different table id -> returns false
        Table differentId = new Table(VALID_TABLE_SIZE_HUNDRED, VALID_TABLE_ID_ONE);
        assertFalse(table.equals(differentId));

        // different table size and id -> returns false
        Table differentSizeAndId = new Table(VALID_TABLE_SIZE_ONE, VALID_TABLE_ID_ONE);
        assertFalse(table.equals(differentSizeAndId));
    }

}
