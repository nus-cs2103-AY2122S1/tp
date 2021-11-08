package seedu.address.logic.position;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.position.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.position.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.position.CommandTestUtil.showPositionAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_POSITION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_POSITION;
import static seedu.address.testutil.TypicalPersons.getTypicalHrManager;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.position.Position;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeletePositionCommand}.
 */
class DeletePositionCommandTest {

    private Model model = new ModelManager(getTypicalHrManager(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Position positionToDelete = model.getFilteredPositionList().get(INDEX_FIRST_POSITION.getZeroBased());
        DeletePositionCommand deletePositionCommand = new DeletePositionCommand(INDEX_FIRST_POSITION);

        String expectedMessage = String.format(DeletePositionCommand.MESSAGE_DELETE_POSITION_SUCCESS, positionToDelete);

        ModelManager expectedModel = new ModelManager(model.getHrManager(), new UserPrefs());
        expectedModel.deletePosition(positionToDelete);

        assertCommandSuccess(deletePositionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPositionList().size() + 1);
        DeletePositionCommand deletePositionCommand = new DeletePositionCommand(outOfBoundIndex);

        assertCommandFailure(deletePositionCommand, model, Messages.MESSAGE_INVALID_POSITION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPositionAtIndex(model, INDEX_FIRST_POSITION);

        Position positionToDelete = model.getFilteredPositionList().get(INDEX_FIRST_POSITION.getZeroBased());
        DeletePositionCommand deletePositionCommand = new DeletePositionCommand(INDEX_FIRST_POSITION);

        String expectedMessage = String.format(DeletePositionCommand.MESSAGE_DELETE_POSITION_SUCCESS, positionToDelete);
        Model expectedModel = new ModelManager(getTypicalHrManager(), new UserPrefs());
        expectedModel.deletePosition(positionToDelete);
        showNoPosition(expectedModel);
        assertCommandSuccess(deletePositionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPositionAtIndex(model, INDEX_FIRST_POSITION);

        Index outOfBoundIndex = INDEX_SECOND_POSITION;
        // ensures that outOfBoundIndex is still in bounds of HrManager's list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getHrManager().getPositionList().size());

        DeletePositionCommand deletePositionCommand = new DeletePositionCommand(outOfBoundIndex);

        assertCommandFailure(deletePositionCommand, model, Messages.MESSAGE_INVALID_POSITION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeletePositionCommand deleteFirstCommand = new DeletePositionCommand(INDEX_FIRST_POSITION);
        DeletePositionCommand deleteSecondCommand = new DeletePositionCommand(INDEX_SECOND_POSITION);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeletePositionCommand deleteFirstCommandCopy = new DeletePositionCommand(INDEX_FIRST_POSITION);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different position -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPosition(Model model) {
        model.updateFilteredPositionList(p -> false);
        assertTrue(model.getFilteredPositionList().isEmpty());
    }
}
