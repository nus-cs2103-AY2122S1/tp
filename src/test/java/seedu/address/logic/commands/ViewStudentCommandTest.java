package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalClassmate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ViewStudentCommand}.
 */
class ViewStudentCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalClassmate(), new UserPrefs());
        expectedModel = new ModelManager(model.getClassmate(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToView = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        ViewStudentCommand viewStudentCommand = new ViewStudentCommand(INDEX_FIRST_STUDENT);

        String expectedMessage = String.format(ViewStudentCommand.MESSAGE_VIEW_STUDENT_SUCCESS, studentToView);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, true,
                false, studentToView);

        assertCommandSuccess(viewStudentCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        ViewStudentCommand viewStudentCommand = new ViewStudentCommand(outOfBoundIndex);

        assertCommandFailure(viewStudentCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);


        Student studentToView = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        ViewStudentCommand viewStudentCommand = new ViewStudentCommand(INDEX_FIRST_STUDENT);

        String expectedMessage = String.format(ViewStudentCommand.MESSAGE_VIEW_STUDENT_SUCCESS, studentToView);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, true,
                false, studentToView);
        showStudentAtIndex(expectedModel, INDEX_FIRST_STUDENT);

        assertCommandSuccess(viewStudentCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of ClassMATE list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getClassmate().getStudentList().size());

        ViewStudentCommand viewStudentCommand = new ViewStudentCommand(outOfBoundIndex);

        assertCommandFailure(viewStudentCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {

        ViewStudentCommand viewFirstCommand = new ViewStudentCommand(INDEX_FIRST_STUDENT);
        ViewStudentCommand viewSecondCommand = new ViewStudentCommand(INDEX_SECOND_STUDENT);


        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewStudentCommand viewFirstCommandCopy = new ViewStudentCommand(INDEX_FIRST_STUDENT);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }
}
