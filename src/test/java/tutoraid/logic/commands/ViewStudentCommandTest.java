package tutoraid.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutoraid.logic.commands.CommandTestUtil.assertCommandFailure;
import static tutoraid.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tutoraid.logic.commands.CommandTestUtil.showStudentAtIndex;
import static tutoraid.logic.commands.ViewStudentCommand.MESSAGE_VIEW_STUDENT_SUCCESS;
import static tutoraid.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static tutoraid.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static tutoraid.testutil.TypicalLessons.getTypicalLessonBook;
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
 * {@code ViewStudentCommand}.
 */
public class ViewStudentCommandTest {

    private Model model = new ModelManager(getTypicalStudentBook(), getTypicalLessonBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToView = model.getFilteredStudentList().get(INDEX_FIRST_ITEM.getZeroBased());
        ViewStudentCommand viewStudentCommand = new ViewStudentCommand(INDEX_FIRST_ITEM);
        String expectedMessage = String.format(MESSAGE_VIEW_STUDENT_SUCCESS, studentToView.toNameString());
        ModelManager expectedModel = new ModelManager(model.getStudentBook(), model.getLessonBook(), new UserPrefs());
        expectedModel.viewStudent(studentToView);

        assertCommandSuccess(viewStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        ViewStudentCommand viewStudentCommand = new ViewStudentCommand(outOfBoundIndex);

        assertCommandFailure(viewStudentCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_ITEM);

        Student studentToView = model.getFilteredStudentList().get(INDEX_FIRST_ITEM.getZeroBased());
        ViewStudentCommand viewStudentCommand = new ViewStudentCommand(INDEX_FIRST_ITEM);
        String expectedMessage = String.format(MESSAGE_VIEW_STUDENT_SUCCESS, studentToView.toNameString());
        Model expectedModel = new ModelManager(model.getStudentBook(), model.getLessonBook(), new UserPrefs());
        expectedModel.viewStudent(studentToView);

        assertCommandSuccess(viewStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showStudentAtIndex(model, INDEX_FIRST_ITEM);

        Index outOfBoundIndex = INDEX_SECOND_ITEM;
        // ensures that outOfBoundIndex is still in bounds of student list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getStudentBook().getStudentList().size());

        ViewStudentCommand viewStudentCommand = new ViewStudentCommand(outOfBoundIndex);

        assertCommandFailure(viewStudentCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewStudentCommand viewFirstCommand = new ViewStudentCommand(INDEX_FIRST_ITEM);
        ViewStudentCommand viewSecondCommand = new ViewStudentCommand(INDEX_SECOND_ITEM);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewStudentCommand viewFirstCommandCopy = new ViewStudentCommand(INDEX_FIRST_ITEM);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }
}
