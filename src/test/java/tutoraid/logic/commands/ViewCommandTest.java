package tutoraid.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutoraid.logic.commands.CommandTestUtil.assertCommandFailure;
import static tutoraid.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tutoraid.logic.commands.CommandTestUtil.showStudentAtIndex;
import static tutoraid.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static tutoraid.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static tutoraid.testutil.TypicalStudents.getTypicalStudentBook;

import org.junit.jupiter.api.Test;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.model.Model;
import tutoraid.model.ModelManager;
import tutoraid.model.UserPrefs;
import tutoraid.model.student.Student;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ViewCommand}.
 */
public class ViewCommandTest {

    private Model model = new ModelManager(getTypicalStudentBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToView = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_STUDENT);

        String expectedMessage = "Viewing requested student";

        ModelManager expectedModel = new ModelManager(model.getStudentBook(), new UserPrefs());
        expectedModel.viewStudent(studentToView);

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student studentToView = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_STUDENT);

        String expectedMessage = "Viewing requested student";

        Model expectedModel = new ModelManager(model.getStudentBook(), new UserPrefs());
        expectedModel.viewStudent(studentToView);

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of student list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getStudentBook().getStudentList().size());

        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewCommand viewFirstCommand = new ViewCommand(INDEX_FIRST_STUDENT);
        ViewCommand viewSecondCommand = new ViewCommand(INDEX_SECOND_STUDENT);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(INDEX_FIRST_STUDENT);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }
}
