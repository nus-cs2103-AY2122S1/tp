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
import tutoraid.model.StudentBook;
import tutoraid.model.UserPrefs;
import tutoraid.model.student.Student;
import tutoraid.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code PaidCommand}.
 */
public class PaidCommandTest {

    private Model model = new ModelManager(getTypicalStudentBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToEdit = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(studentToEdit).withPaymentStatus(true).build();

        PaidCommand paidCommand = new PaidCommand(INDEX_FIRST_STUDENT);

        String expectedMessage = String.format(PaidCommand.MESSAGE_SET_TO_PAID_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new StudentBook(model.getStudentBook()), new UserPrefs());
        expectedModel.setStudent(studentToEdit, editedStudent);

        assertCommandSuccess(paidCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        PaidCommand paidCommand = new PaidCommand(outOfBoundIndex);

        assertCommandFailure(paidCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student studentToEdit = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(studentToEdit).withPaymentStatus(true).build();

        PaidCommand paidCommand = new PaidCommand(INDEX_FIRST_STUDENT);

        String expectedMessage = String.format(PaidCommand.MESSAGE_SET_TO_PAID_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new StudentBook(model.getStudentBook()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);

        assertCommandSuccess(paidCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of student book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getStudentBook().getStudentList().size());

        PaidCommand paidCommand = new PaidCommand(outOfBoundIndex);

        assertCommandFailure(paidCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        PaidCommand paidFirstCommand = new PaidCommand(INDEX_FIRST_STUDENT);
        PaidCommand paidSecondCommand = new PaidCommand(INDEX_SECOND_STUDENT);

        // same object -> returns true
        assertTrue(paidFirstCommand.equals(paidFirstCommand));

        // same values -> returns true
        PaidCommand paidFirstCommandCopy = new PaidCommand(INDEX_FIRST_STUDENT);
        assertTrue(paidFirstCommand.equals(paidFirstCommandCopy));

        // different types -> returns false
        assertFalse(paidFirstCommand.equals(1));

        // null -> returns false
        assertFalse(paidFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(paidFirstCommand.equals(paidSecondCommand));
    }
}
