package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertPositionCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showPositionAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_POSITION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_POSITION;
import static seedu.address.testutil.TypicalPositions.getTypicalPositionBook;

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
public class DeletePositionCommandTest {

    private Model model = new ModelManager(getTypicalPositionBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Position positionToDelete = model.getFilteredPositionList().get(INDEX_FIRST_POSITION.getZeroBased());
        DeletePositionCommand deletePositionCommand = new DeletePositionCommand(INDEX_FIRST_POSITION);

        String expectedMessage = String.format(DeletePositionCommand.MESSAGE_DELETE_POSITION_SUCCESS, positionToDelete);

        ModelManager expectedModel = new ModelManager(model.getPositionBook(), new UserPrefs());
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
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPositionAtIndex(model, INDEX_FIRST_POSITION);

        Index outOfBoundIndex = INDEX_SECOND_POSITION;
        // ensures that outOfBoundIndex is still in bounds of position book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPositionBook().getPositionList().size());

        DeletePositionCommand deletePositionCommand = new DeletePositionCommand(outOfBoundIndex);

        assertPositionCommandFailure(deletePositionCommand, model, Messages.MESSAGE_INVALID_POSITION_DISPLAYED_INDEX);
    }

}
