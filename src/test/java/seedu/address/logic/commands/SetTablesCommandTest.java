package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalCustomers.getTypicalRhrhCustomers;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.table.Table;


class SetTablesCommandTest {
    private Model model = new ModelManager(getTypicalRhrhCustomers(), new UserPrefs());

    @Test
    public void constructor_nullArgument_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SetTablesCommand(null));
    }

    @Test
    public void execute_validListOfTableSizes_addSuccessful() throws Exception {
        List<Integer> tableSizeList = Arrays.asList(1, 2, 3, 4, 5);
        List<Table> tableList = Arrays.asList(
                new Table(1, 1),
                new Table(2, 2),
                new Table(3, 3),
                new Table(4, 4),
                new Table(5, 5)
        );
        SetTablesCommand command = new SetTablesCommand(tableSizeList);
        model.setTableList(tableList);

        command.execute(model);

        assertTrue(model.hasTable(new Table(1, 1)));
        assertTrue(model.hasTable(new Table(2, 2)));
        assertTrue(model.hasTable(new Table(3, 3)));
        assertTrue(model.hasTable(new Table(4, 4)));
        assertTrue(model.hasTable(new Table(5, 5)));
    }

    @Test
    public void equals() {

        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

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
        assertFalse(setTablesCommand.equals(new SetTablesCommand(Arrays.asList(99, 2, 3, 4, 5, 6, 7, 8, 9, 10))));
    }
}
