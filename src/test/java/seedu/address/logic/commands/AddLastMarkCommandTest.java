package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.getTypicalClassmate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Classmate;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentMark;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddLastMarkCommand.
 */
public class AddLastMarkCommandTest {

    private Model model = new ModelManager(getTypicalClassmate(), new UserPrefs());

    @Test
    public void execute_markSpecifiedUnfilteredList_success() {
        AddLastMarkCommand addLastMarkCommand = new AddLastMarkCommand(INDEX_FIRST_STUDENT, StudentMark.GOOD);
        Student editedStudent = new StudentBuilder(ALICE).withMarks("LOW", "HIGH", "GOOD").build();

        String expectedMessage = String.format(AddLastMarkCommand.MESSAGE_ADD_MARK_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new Classmate(model.getClassmate()), new UserPrefs());

        assertCommandSuccess(addLastMarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(studentInFilteredList).withMarks("LOW", "HIGH", "GOOD").build();
        AddLastMarkCommand addLastMarkCommand = new AddLastMarkCommand(INDEX_FIRST_STUDENT, StudentMark.GOOD);

        String expectedMessage = String.format(AddLastMarkCommand.MESSAGE_ADD_MARK_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new Classmate(model.getClassmate()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);

        assertCommandSuccess(addLastMarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final AddLastMarkCommand standardCommand = new AddLastMarkCommand(INDEX_FIRST_STUDENT, StudentMark.EXCELLENT);

        // same values -> returns true
        AddLastMarkCommand commandWithSameValues = new AddLastMarkCommand(INDEX_FIRST_STUDENT, StudentMark.EXCELLENT);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddLastMarkCommand(INDEX_SECOND_STUDENT, StudentMark.EXCELLENT)));

        // different mark -> returns false
        assertFalse(standardCommand.equals(new AddLastMarkCommand(INDEX_FIRST_STUDENT, StudentMark.GOOD)));
    }

}
