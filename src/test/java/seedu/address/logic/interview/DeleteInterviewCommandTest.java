package seedu.address.logic.interview;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.interview.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.interview.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.interview.CommandTestUtil.showInterviewAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERVIEW;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_INTERVIEW;
import static seedu.address.testutil.TypicalPersons.getTypicalHrManager;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.interview.Interview;

public class DeleteInterviewCommandTest {

    private Model model = new ModelManager(getTypicalHrManager(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Interview interviewToDelete = model.getFilteredInterviewList().get(INDEX_FIRST_INTERVIEW.getZeroBased());
        DeleteInterviewCommand deleteInterviewCommand = new DeleteInterviewCommand(INDEX_FIRST_INTERVIEW);

        String expectedMessage = String.format(DeleteInterviewCommand.MESSAGE_DELETE_INTERVIEW_SUCCESS,
                interviewToDelete.getDisplayString());

        ModelManager expectedModel = new ModelManager(model.getHrManager(), new UserPrefs());
        expectedModel.deleteInterview(interviewToDelete);

        assertCommandSuccess(deleteInterviewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInterviewList().size() + 1);
        DeleteInterviewCommand deleteInterviewCommand = new DeleteInterviewCommand(outOfBoundIndex);

        assertCommandFailure(deleteInterviewCommand, model, Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showInterviewAtIndex(model, INDEX_FIRST_INTERVIEW);

        Interview interviewToDelete = model.getFilteredInterviewList().get(INDEX_FIRST_INTERVIEW.getZeroBased());
        DeleteInterviewCommand deleteInterviewCommand = new DeleteInterviewCommand(INDEX_FIRST_INTERVIEW);

        String expectedMessage = String.format(DeleteInterviewCommand.MESSAGE_DELETE_INTERVIEW_SUCCESS,
                interviewToDelete.getDisplayString());
        Model expectedModel = new ModelManager(getTypicalHrManager(), new UserPrefs());
        expectedModel.deleteInterview(interviewToDelete);
        showNoInterview(expectedModel);
        assertCommandSuccess(deleteInterviewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showInterviewAtIndex(model, INDEX_FIRST_INTERVIEW);

        Index outOfBoundIndex = INDEX_SECOND_INTERVIEW;
        // ensures that outOfBoundIndex is still in bounds of HrManager's list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getHrManager().getInterviewList().size());

        DeleteInterviewCommand deleteInterviewCommand = new DeleteInterviewCommand(outOfBoundIndex);

        assertCommandFailure(deleteInterviewCommand, model, Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteInterviewCommand deleteFirstCommand = new DeleteInterviewCommand(INDEX_FIRST_INTERVIEW);
        DeleteInterviewCommand deleteSecondCommand = new DeleteInterviewCommand(INDEX_SECOND_INTERVIEW);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteInterviewCommand deleteFirstCommandCopy = new DeleteInterviewCommand(INDEX_FIRST_INTERVIEW);
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
    private void showNoInterview(Model model) {
        model.updateFilteredInterviewList(i -> false);
        assertTrue(model.getFilteredInterviewList().isEmpty());
    }

}
