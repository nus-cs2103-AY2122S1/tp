package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.getTypicalClassmate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Classmate;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for DeleteAllMarkCommand.
 */
public class DeleteAllMarkCommandTest {

    private Model model = new ModelManager(getTypicalClassmate(), new UserPrefs());

    @Test
    public void execute_indexSpecifiedUnfilteredList_success() {
        DeleteAllMarkCommand deleteAllMarkCommand = new DeleteAllMarkCommand(INDEX_FIRST_STUDENT);
        Student finalStudent = new StudentBuilder(ALICE).build();

        String expectedMessage = String
                .format(DeleteAllMarkCommand.MESSAGE_DELETE_ALL_MARK_STUDENT_SUCCESS, finalStudent);
        expectedMessage = expectedMessage.substring(0, expectedMessage.length() - 18);

        Model expectedModel = new ModelManager(new Classmate(model.getClassmate()), new UserPrefs());

        assertCommandSuccess(deleteAllMarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(studentInFilteredList).build();
        DeleteAllMarkCommand deleteAllMarkCommand = new DeleteAllMarkCommand(INDEX_FIRST_STUDENT);

        String expectedMessage =
                String.format(DeleteAllMarkCommand.MESSAGE_DELETE_ALL_MARK_STUDENT_SUCCESS, editedStudent);
        expectedMessage = expectedMessage.substring(0, expectedMessage.length() - 18);

        Model expectedModel = new ModelManager(new Classmate(model.getClassmate()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);

        assertCommandSuccess(deleteAllMarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteEmptyMarksFilteredList_failure() {
        DeleteAllMarkCommand deleteAllMarkCommand = new DeleteAllMarkCommand(INDEX_THIRD_STUDENT);

        assertCommandFailure(deleteAllMarkCommand, model, DeleteAllMarkCommand.MESSAGE_NO_MARKS);
    }

    @Test
    public void equals() {
        final DeleteAllMarkCommand standardCommand = new DeleteAllMarkCommand(INDEX_FIRST_STUDENT);

        // same values -> returns true
        DeleteAllMarkCommand commandWithSameValues = new DeleteAllMarkCommand(INDEX_FIRST_STUDENT);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new DeleteAllMarkCommand(INDEX_SECOND_STUDENT)));
    }

}
