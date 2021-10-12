package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.getTypicalInventory;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand.SortOrder;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.ItemCountComparator;
import seedu.address.model.item.ItemNameComparator;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
public class SortCommandTest {

    private Model model = new ModelManager(getTypicalInventory(), new UserPrefs());

    @Test
    public void constructor_nullSortOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortCommand(null));
    }

    @Test
    public void execute_sortByName_successful() throws Exception {
        SortCommand command = new SortCommand(SortCommand.SortOrder.BY_NAME);

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "name");
        Model expectedModel = new ModelManager(getTypicalInventory(), new UserPrefs());
        expectedModel.sortItems(new ItemNameComparator());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortByCount_successful() throws Exception {
        SortCommand command = new SortCommand(SortCommand.SortOrder.BY_COUNT);

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "count");
        Model expectedModel = new ModelManager(getTypicalInventory(), new UserPrefs());
        expectedModel.sortItems(new ItemCountComparator());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    public void equals() {
        SortCommand byNameCommand = new SortCommand(SortCommand.SortOrder.BY_NAME);
        SortCommand byCountCommand = new SortCommand(SortCommand.SortOrder.BY_COUNT);

        // same object -> returns true
        assertTrue(byNameCommand.equals(byNameCommand));

        // null -> returns false
        assertFalse(byNameCommand.equals(null));

        // different types -> returns false
        assertFalse(byNameCommand.equals(new ClearCommand()));

        // different order -> returns false
        assertFalse(byNameCommand.equals(byCountCommand));

        // both by same order -> returns true
        assertTrue(byNameCommand.equals(new SortCommand(SortOrder.BY_NAME)));
    }
}
