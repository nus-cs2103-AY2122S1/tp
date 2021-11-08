package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalCustomers.getTypicalRhrhCustomers;
import static seedu.address.testutil.TypicalTables.DUMMY_LIST_OF_TABLES;
import static seedu.address.testutil.TypicalTables.DUMMY_LIST_OF_TABLE_SIZES;
import static seedu.address.testutil.TypicalTables.DUMMY_TABLE_1;
import static seedu.address.testutil.TypicalTables.DUMMY_TABLE_2;
import static seedu.address.testutil.TypicalTables.DUMMY_TABLE_3;
import static seedu.address.testutil.TypicalTables.DUMMY_TABLE_4;
import static seedu.address.testutil.TypicalTables.DUMMY_TABLE_5;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


class SetTablesCommandTest {
    private Model model = new ModelManager(getTypicalRhrhCustomers(), new UserPrefs());

    @Test
    public void constructor_nullArgument_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SetTablesCommand(null));
    }

    @Test
    public void execute_validListOfTableSizes_addSuccessful() throws Exception {
        SetTablesCommand command = new SetTablesCommand(DUMMY_LIST_OF_TABLE_SIZES);
        model.setTableList(DUMMY_LIST_OF_TABLES);

        command.execute(model);

        assertTrue(model.hasTable(DUMMY_TABLE_1));
        assertTrue(model.hasTable(DUMMY_TABLE_2));
        assertTrue(model.hasTable(DUMMY_TABLE_3));
        assertTrue(model.hasTable(DUMMY_TABLE_4));
        assertTrue(model.hasTable(DUMMY_TABLE_5));
    }

    @Test
    public void equals() {

        List<Integer> list1 = DUMMY_LIST_OF_TABLE_SIZES;
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5);

        SetTablesCommand setTablesCommand = new SetTablesCommand(list1);
        SetTablesCommand setTablesCommandCopied = new SetTablesCommand(list2);

        // same object -> returns true
        assertTrue(setTablesCommand.equals(setTablesCommand));

        // same values -> returns true
        assertTrue(setTablesCommand.equals(setTablesCommandCopied));

        // different types -> returns false
        assertFalse(setTablesCommand.equals(2));

        // null -> returns false
        assertFalse(setTablesCommand.equals(null));

        // different list values -> returns false
        assertFalse(setTablesCommand.equals(new SetTablesCommand(Arrays.asList(99, 2, 3, 4, 5))));
    }
}
