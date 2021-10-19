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
import seedu.address.model.item.ItemDescriptor;
import seedu.address.testutil.ItemDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code RemoveCommand}.
 */
public class RemoveCommandTest {

    private Model model = new ModelManager(getTypicalInventory(), new UserPrefs());

    @Test
    public void execute_deleteExistingItemByName_success() {
        model.addItem(BAGEL);

        ItemDescriptor bagelDescripter = new ItemDescriptorBuilder().withName(VALID_NAME_BAGEL).build();
        RemoveCommand deleteCommand = new RemoveCommand(bagelDescripter);

        Model expectedModel = new ModelManager(model.getInventory(), new UserPrefs());
        expectedModel.addItem(BAGEL);
        expectedModel.deleteItem(BAGEL);

        String expectedMessage = String.format(String.format(RemoveCommand.MESSAGE_SUCCESS, BAGEL));

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteExistingItemById_success() {
        RemoveCommand deleteCommand = new RemoveCommand(APPLE_PIE.getId(), 1);

        Model expectedModel = new ModelManager(model.getInventory(), new UserPrefs());
        expectedModel.deleteItem(APPLE_PIE.getId(), 1);

        String expectedMessage = String.format(RemoveCommand.MESSAGE_DELETE_ITEM_SUCCESS, APPLE_PIE.updateCount(1));

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonexistentName_throwsCommandException() {
        RemoveCommand deleteCommand = new RemoveCommand(BAGEL.getName(), -1);
        String expectedMessage = String.format(RemoveCommand.MESSAGE_ITEM_NAME_NOT_FOUND, BAGEL.getName());

        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void execute_nonexistentId_throwsCommandException() {
        RemoveCommand deleteCommand = new RemoveCommand(BAGEL.getId(), -1);
        String expectedMessage = String.format(RemoveCommand.MESSAGE_ITEM_ID_NOT_FOUND, BAGEL.getId());

        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        RemoveCommand deleteFirstCommand = new RemoveCommand(VALID_NAME_BAGEL, -1);
        RemoveCommand deleteSecondCommand = new RemoveCommand(VALID_NAME_DONUT, -1);
        RemoveCommand deleteThirdCommand = new RemoveCommand(VALID_ID_BAGEL, -1);
        RemoveCommand deleteFourthCommand = new RemoveCommand(VALID_NAME_DONUT, 2);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        RemoveCommand deleteFirstCommandCopy = new RemoveCommand(VALID_NAME_BAGEL, -1);
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
