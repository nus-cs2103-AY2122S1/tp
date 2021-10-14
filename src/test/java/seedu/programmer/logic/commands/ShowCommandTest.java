package seedu.programmer.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.programmer.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.programmer.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.programmer.testutil.TypicalStudents.getTypicalProgrammerError;

import org.junit.jupiter.api.Test;

import seedu.programmer.commons.core.Messages;
import seedu.programmer.commons.core.index.Index;
import seedu.programmer.model.Model;
import seedu.programmer.model.ModelManager;
import seedu.programmer.model.UserPrefs;
import seedu.programmer.model.student.Student;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ShowCommand}.
 */
public class ShowCommandTest {

    private Model model = new ModelManager(getTypicalProgrammerError(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        Student studentToShow = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        ShowCommand showCommand = new ShowCommand(INDEX_FIRST_STUDENT);

        String expectedMessage = String.format(ShowCommand.MESSAGE_SHOW_STUDENT_SUCCESS, studentToShow);

        ModelManager expectedModel = new ModelManager(model.getProgrammerError(), new UserPrefs());

        CommandResult expectedCommandResult = new CommandResult(ShowCommand.MESSAGE_SHOW_STUDENT_SUCCESS,
                false, false, true, studentToShow);

        assertCommandSuccess(showCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        ShowCommand showCommand = new ShowCommand(outOfBoundIndex);

        assertCommandFailure(showCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ShowCommand showFirstCommand = new ShowCommand(INDEX_FIRST_STUDENT);
        ShowCommand showSecondCommand = new ShowCommand(INDEX_SECOND_STUDENT);

        // same object -> returns true
        assertTrue(showFirstCommand.equals(showFirstCommand));

        // same values -> returns true
        ShowCommand showFirstCommandCopy = new ShowCommand(INDEX_FIRST_STUDENT);
        assertTrue(showFirstCommand.equals(showFirstCommandCopy));

        // different types -> returns false
        assertFalse(showFirstCommand.equals(1));

        // null -> returns false
        assertFalse(showFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(showFirstCommand.equals(showSecondCommand));
    }

}
