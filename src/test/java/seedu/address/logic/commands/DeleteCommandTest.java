package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalItems.APPLE_PIE;
import static seedu.address.testutil.TypicalItems.BAGEL;
import static seedu.address.testutil.TypicalItems.getTypicalInventory;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalInventory(), new UserPrefs());

    @Test
    public void execute_deleteExistingItemByName_success() {
        DeleteCommand deleteCommand = new DeleteCommand(APPLE_PIE.getName(), 1);

        Model expectedModel = new ModelManager(model.getInventory(), new UserPrefs());
        expectedModel.deleteItem(APPLE_PIE.getName(), 1);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ITEM_SUCCESS, APPLE_PIE.updateCount(1));

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteExistingItemById_success() {
        DeleteCommand deleteCommand = new DeleteCommand(APPLE_PIE.getId(), 1);

        Model expectedModel = new ModelManager(model.getInventory(), new UserPrefs());
        expectedModel.deleteItem(APPLE_PIE.getId(), 1);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ITEM_SUCCESS, APPLE_PIE.updateCount(1));

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonexistentName_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand(BAGEL.getName(), -1);
        String expectedMessage = String.format(DeleteCommand.MESSAGE_ITEM_NAME_NOT_FOUND, BAGEL.getName());

        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void execute_nonexistentId_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand(BAGEL.getId(), -1);
        String expectedMessage = String.format(DeleteCommand.MESSAGE_ITEM_ID_NOT_FOUND, BAGEL.getId());

        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(VALID_NAME_BAGEL, -1);
        DeleteCommand deleteSecondCommand = new DeleteCommand(VALID_NAME_DONUT, -1);
        DeleteCommand deleteThirdCommand = new DeleteCommand(VALID_ID_BAGEL, -1);
        DeleteCommand deleteFourthCommand = new DeleteCommand(VALID_NAME_DONUT, 2);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(VALID_NAME_BAGEL, -1);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different values -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
        // different id -> returns false
        assertFalse(deleteFirstCommand.equals(deleteThirdCommand));
        // different count -> returns false
        assertFalse(deleteFirstCommand.equals(deleteFourthCommand));
    }
}
