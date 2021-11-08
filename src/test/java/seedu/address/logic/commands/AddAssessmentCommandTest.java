package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssessments.FINALS;
import static seedu.address.testutil.TypicalAssessments.MIDTERMS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalCsBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.CsBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddAssessmentCommand.
 */
public class AddAssessmentCommandTest {

    private Model model = new ModelManager(getTypicalCsBook(), new UserPrefs());

    @Test
    public void constructor_nullAssessment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAssessmentCommand(INDEX_FIRST_STUDENT, null));
    }

    @Test
    public void execute_unfilteredList_success() throws Exception {
        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(studentInFilteredList).withAssessment(FINALS).build();
        AddAssessmentCommand addAssessmentCommand = new AddAssessmentCommand(INDEX_FIRST_STUDENT, FINALS);

        String expectedMessage = String.format(AddAssessmentCommand.MESSAGE_SUCCESS, editedStudent.getName(), FINALS);

        Model expectedModel = new ModelManager(new CsBook(model.getCsBook()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);

        assertCommandSuccess(addAssessmentCommand, model, expectedMessage, expectedModel);
        studentInFilteredList.deleteAssessment(FINALS); // remove assessment from student to not affect other test cases
    }

    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(studentInFilteredList).withAssessment(FINALS).build();
        AddAssessmentCommand addAssessmentCommand = new AddAssessmentCommand(INDEX_FIRST_STUDENT, FINALS);

        String expectedMessage = String.format(AddAssessmentCommand.MESSAGE_SUCCESS, editedStudent.getName(), FINALS);

        Model expectedModel = new ModelManager(new CsBook(model.getCsBook()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);
        showStudentAtIndex(expectedModel, INDEX_FIRST_STUDENT);

        assertCommandSuccess(addAssessmentCommand, model, expectedMessage, expectedModel);
        studentInFilteredList.deleteAssessment(FINALS); // remove assessment from student to not affect other test cases
    }

    @Test
    public void execute_duplicateAssessmentUnfilteredList_failure() {
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());

        AddAssessmentCommand addAssessmentCommand = new AddAssessmentCommand(INDEX_FIRST_STUDENT, MIDTERMS);
        String expectedMessage = String.format(AddAssessmentCommand.MESSAGE_DUPLICATE_ASSESSMENT,
                firstStudent.getName());

        assertCommandFailure(addAssessmentCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        AddAssessmentCommand addAssessmentCommand = new AddAssessmentCommand(outOfBoundIndex, FINALS);

        assertCommandFailure(addAssessmentCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidStudentIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getCsBook().getStudentList().size());

        AddAssessmentCommand addAssessmentCommand = new AddAssessmentCommand(outOfBoundIndex, FINALS);

        assertCommandFailure(addAssessmentCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AddAssessmentCommand standardCommand = new AddAssessmentCommand(INDEX_FIRST_STUDENT, MIDTERMS);

        // same values -> returns true
        AddAssessmentCommand commandWithSameValues = new AddAssessmentCommand(INDEX_FIRST_STUDENT, MIDTERMS);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddAssessmentCommand(INDEX_SECOND_STUDENT, MIDTERMS)));

        // different assessment -> returns false
        assertFalse(standardCommand.equals(new AddAssessmentCommand(INDEX_FIRST_STUDENT, FINALS)));
    }
}
