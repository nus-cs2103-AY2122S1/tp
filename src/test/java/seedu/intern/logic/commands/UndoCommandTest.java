package seedu.intern.logic.commands;

import static seedu.intern.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.intern.testutil.Assert.assertThrows;
import static seedu.intern.testutil.TypicalApplicants.getTypicalInternWatcher;
import static seedu.intern.testutil.TypicalSelections.SELECTION_FIRST_APPLICANT;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.intern.logic.commands.exceptions.CommandException;
import seedu.intern.model.InternWatcher;
import seedu.intern.model.Model;
import seedu.intern.model.ModelManager;
import seedu.intern.model.UserPrefs;
import seedu.intern.model.applicant.Applicant;
import seedu.intern.testutil.ApplicantBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for UndoCommand.
 */
public class UndoCommandTest {
    private Model model = new ModelManager(getTypicalInternWatcher(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalInternWatcher(), new UserPrefs());

    @Test
    public void execute_undoAdd_success() {
        Applicant applicant = new ApplicantBuilder().build();
        String commitText = String.format(AddCommand.MESSAGE_COMMIT_ADD, applicant);
        model.addApplicant(applicant);
        model.commitInternWatcher(commitText);

        String expectedMessage = String.format(UndoCommand.MESSAGE_SUCCESS, commitText);

        assertCommandSuccess(new UndoCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_undoDelete_success() {
        Applicant applicantToDelete = model.getFilteredApplicantList()
                .get(SELECTION_FIRST_APPLICANT.getIndexZeroBased());

        String commitText = String.format(DeleteCommand.MESSAGE_COMMIT_DELETE, applicantToDelete);

        model.deleteApplicant(applicantToDelete);
        model.commitInternWatcher(commitText);

        String expectedMessage = String.format(UndoCommand.MESSAGE_SUCCESS, commitText);

        assertCommandSuccess(new UndoCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_undoDeleteAll_success() {
        List<Applicant> lastShownList = model.getFilteredApplicantList();
        int length = lastShownList.size();
        for (int i = 0; i < length; i++) {
            Applicant applicantToDelete = lastShownList.get(0);
            model.deleteApplicant(applicantToDelete);
        }
        String commitText = String.format(DeleteCommand.MESSAGE_COMMIT_DELETE_ALL, String.valueOf(length));
        model.commitInternWatcher(commitText);

        String expectedMessage = String.format(UndoCommand.MESSAGE_SUCCESS, commitText);

        assertCommandSuccess(new UndoCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_undoEdit_success() {
        Applicant editedApplicant = new ApplicantBuilder().build();
        String commitText = String.format(EditCommand.MESSAGE_COMMIT_EDIT, editedApplicant);
        model.setApplicant(model.getFilteredApplicantList().get(0), editedApplicant);
        model.commitInternWatcher(commitText);

        String expectedMessage = String.format(UndoCommand.MESSAGE_SUCCESS, commitText);

        assertCommandSuccess(new UndoCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_undoClear_success() {
        String commitText = ClearCommand.MESSAGE_COMMIT_CLEAR;

        model.setInternWatcher(new InternWatcher());
        model.commitInternWatcher(commitText);

        String expectedMessage = String.format(UndoCommand.MESSAGE_SUCCESS, commitText);

        assertCommandSuccess(new UndoCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noUndo_throwsCommandException() {
        UndoCommand undoCommand = new UndoCommand();

        assertThrows(CommandException.class, UndoCommand.MESSAGE_NO_UNDO, () -> undoCommand.execute(model));
    }
}
