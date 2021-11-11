package seedu.intern.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intern.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.intern.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.intern.logic.commands.CommandTestUtil.showApplicantAtIndex;
import static seedu.intern.testutil.TypicalApplicants.getTypicalInternWatcher;
import static seedu.intern.testutil.TypicalSelections.SELECTION_ALL;
import static seedu.intern.testutil.TypicalSelections.SELECTION_FIRST_APPLICANT;
import static seedu.intern.testutil.TypicalSelections.SELECTION_SECOND_APPLICANT;

import org.junit.jupiter.api.Test;

import seedu.intern.commons.core.Messages;
import seedu.intern.commons.core.selection.Index;
import seedu.intern.commons.core.selection.Selection;
import seedu.intern.model.Model;
import seedu.intern.model.ModelManager;
import seedu.intern.model.UserPrefs;
import seedu.intern.model.applicant.Applicant;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalInternWatcher(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Applicant applicantToDelete = model.getFilteredApplicantList()
                .get(SELECTION_FIRST_APPLICANT.getIndexZeroBased());

        DeleteCommand deleteCommand = new DeleteCommand(SELECTION_FIRST_APPLICANT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_APPLICANT_SUCCESS, applicantToDelete);

        ModelManager expectedModel = new ModelManager(model.getInternWatcher(), new UserPrefs());
        expectedModel.deleteApplicant(applicantToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validAllUnfilteredList_success() {
        DeleteCommand deleteCommand = new DeleteCommand(SELECTION_ALL);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ALL_SUCCESS,
                model.getFilteredApplicantList().size());

        ModelManager expectedModel = new ModelManager();

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validAllFilteredList_success() {
        showApplicantAtIndex(model, SELECTION_FIRST_APPLICANT.getIndex());

        Applicant applicantToDelete = model.getFilteredApplicantList()
                .get(SELECTION_FIRST_APPLICANT.getIndexZeroBased());

        DeleteCommand deleteCommand = new DeleteCommand(SELECTION_ALL);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ALL_SUCCESS,
                model.getFilteredApplicantList().size());

        Model expectedModel = new ModelManager(model.getInternWatcher(), new UserPrefs());
        expectedModel.deleteApplicant(applicantToDelete);
        showNoApplicant(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicantList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(Selection.fromIndex(outOfBoundIndex));

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showApplicantAtIndex(model, SELECTION_FIRST_APPLICANT.getIndex());

        Applicant applicantToDelete = model.getFilteredApplicantList()
                .get(SELECTION_FIRST_APPLICANT.getIndexZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(SELECTION_FIRST_APPLICANT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_APPLICANT_SUCCESS, applicantToDelete);

        Model expectedModel = new ModelManager(model.getInternWatcher(), new UserPrefs());
        expectedModel.deleteApplicant(applicantToDelete);
        showNoApplicant(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showApplicantAtIndex(model, SELECTION_FIRST_APPLICANT.getIndex());

        Selection outOfBoundIndex = SELECTION_SECOND_APPLICANT;
        // ensures that outOfBoundIndex is still in bounds of intern book list
        assertTrue(outOfBoundIndex.getIndexZeroBased() < model.getInternWatcher().getApplicantList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(SELECTION_FIRST_APPLICANT);
        DeleteCommand deleteSecondCommand = new DeleteCommand(SELECTION_SECOND_APPLICANT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(SELECTION_FIRST_APPLICANT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different applicant -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoApplicant(Model model) {
        model.updateFilteredApplicantList(p -> false);

        assertTrue(model.getFilteredApplicantList().isEmpty());
    }
}
