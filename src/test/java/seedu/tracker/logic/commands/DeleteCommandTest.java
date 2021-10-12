package seedu.tracker.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tracker.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.tracker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tracker.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.tracker.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.tracker.testutil.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.tracker.testutil.TypicalModules.getTypicalModuleTracker;

import org.junit.jupiter.api.Test;

import seedu.tracker.commons.core.Messages;
import seedu.tracker.commons.core.index.Index;
import seedu.tracker.model.Model;
import seedu.tracker.model.ModelManager;
import seedu.tracker.model.UserPrefs;
import seedu.tracker.model.module.Module;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalModuleTracker(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Module personToDelete = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_MODULE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_MODULE_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getModuleTracker(), new UserPrefs());
        expectedModel.deleteModule(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredModuleList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);

        Module personToDelete = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_MODULE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_MODULE_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getModuleTracker(), new UserPrefs());
        expectedModel.deleteModule(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);

        Index outOfBoundIndex = INDEX_SECOND_MODULE;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getModuleTracker().getModuleList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_MODULE);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_MODULE);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_MODULE);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredModuleList(p -> false);

        assertTrue(model.getFilteredModuleList().isEmpty());
    }
}
