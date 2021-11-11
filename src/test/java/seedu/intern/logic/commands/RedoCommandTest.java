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
 * Contains integration tests (interaction with the Model) and unit tests for RedoCommand.
 */
public class RedoCommandTest {
    private Model model = new ModelManager(getTypicalInternWatcher(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalInternWatcher(), new UserPrefs());

    @Test
    public void execute_redoAdd_success() throws CommandException {
        Applicant applicant = new ApplicantBuilder().build();
        String commitText = String.format(AddCommand.MESSAGE_COMMIT_ADD, applicant);
        model.addApplicant(applicant);
        model.commitInternWatcher(commitText);
        model.undoInternWatcher();

        expectedModel.addApplicant(applicant);

        String expectedMessage = String.format(RedoCommand.MESSAGE_SUCCESS, commitText);

        assertCommandSuccess(new RedoCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_redoDelete_success() throws CommandException {
        Applicant applicantToDelete = model.getFilteredApplicantList()
                .get(SELECTION_FIRST_APPLICANT.getIndexZeroBased());

        String commitText = String.format(DeleteCommand.MESSAGE_COMMIT_DELETE, applicantToDelete);

        model.deleteApplicant(applicantToDelete);
        model.commitInternWatcher(commitText);
        model.undoInternWatcher();

        expectedModel.deleteApplicant(applicantToDelete);

        String expectedMessage = String.format(RedoCommand.MESSAGE_SUCCESS, commitText);

        assertCommandSuccess(new RedoCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_redoDeleteAll_success() throws CommandException {
        List<Applicant> lastShownList = model.getFilteredApplicantList();
        int length = lastShownList.size();
        for (int i = 0; i < length; i++) {
            Applicant applicantToDelete = lastShownList.get(0);
            model.deleteApplicant(applicantToDelete);
            expectedModel.deleteApplicant(applicantToDelete);
        }
        String commitText = String.format(DeleteCommand.MESSAGE_COMMIT_DELETE_ALL, String.valueOf(length));
        model.commitInternWatcher(commitText);
        model.undoInternWatcher();

        String expectedMessage = String.format(RedoCommand.MESSAGE_SUCCESS, commitText);

        assertCommandSuccess(new RedoCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_redoEdit_success() throws CommandException {
        Applicant editedApplicant = new ApplicantBuilder().build();
        String commitText = String.format(EditCommand.MESSAGE_COMMIT_EDIT, editedApplicant);
        model.setApplicant(model.getFilteredApplicantList().get(0), editedApplicant);
        model.commitInternWatcher(commitText);
        model.undoInternWatcher();

        expectedModel.setApplicant(model.getFilteredApplicantList().get(0), editedApplicant);

        String expectedMessage = String.format(RedoCommand.MESSAGE_SUCCESS, commitText);

        assertCommandSuccess(new RedoCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_redoClear_success() throws CommandException {
        String commitText = ClearCommand.MESSAGE_COMMIT_CLEAR;

        model.setInternWatcher(new InternWatcher());
        model.commitInternWatcher(commitText);
        model.undoInternWatcher();

        expectedModel.setInternWatcher(new InternWatcher());

        String expectedMessage = String.format(RedoCommand.MESSAGE_SUCCESS, commitText);

        assertCommandSuccess(new RedoCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noRedo_throwsCommandException() {
        RedoCommand redoCommand = new RedoCommand();

        assertThrows(CommandException.class, RedoCommand.MESSAGE_NO_REDO, () -> redoCommand.execute(model));
    }
}
