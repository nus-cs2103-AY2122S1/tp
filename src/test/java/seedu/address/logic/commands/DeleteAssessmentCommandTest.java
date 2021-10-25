package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSESSMENT_NAME_LAB5;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSESSMENT_NAME_QUIZ1;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalAssessments.QUIZ1;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalCsBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.AssessmentNameMatchesKeywordPredicate;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteAssessmentCommand}.
 */
public class DeleteAssessmentCommandTest {

    private Model model = new ModelManager(getTypicalCsBook(), new UserPrefs());

    @Test
    public void execute_invalidAssessmentName_throwsCommandException() {
        AssessmentName assessmentNameToDelete = new AssessmentName(VALID_ASSESSMENT_NAME_LAB5);
        DeleteAssessmentCommand deleteAssessmentCommand = new DeleteAssessmentCommand(INDEX_FIRST_STUDENT,
                assessmentNameToDelete);

        assertCommandFailure(deleteAssessmentCommand, model, DeleteAssessmentCommand.MESSAGE_ASSESSMENT_NOT_FOUND);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student student = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(student).build();

        // Delete the assessment from the edited student
        editedStudent.updateFilteredAssessmentList(
                new AssessmentNameMatchesKeywordPredicate(VALID_ASSESSMENT_NAME_QUIZ1));

        // check to ensure the assessment exists
        assertFalse(editedStudent.getFilteredAssessmentList().size() == 0);
        Assessment assessmentToDelete = editedStudent.getFilteredAssessmentList().get(0);
        editedStudent.deleteAssessment(assessmentToDelete);

        AssessmentName assessmentNameToDelete = new AssessmentName(VALID_ASSESSMENT_NAME_QUIZ1);
        DeleteAssessmentCommand deleteAssessmentCommand = new DeleteAssessmentCommand(INDEX_FIRST_STUDENT,
                assessmentNameToDelete);
        String expectedMessage = String.format(DeleteAssessmentCommand.MESSAGE_DELETE_ASSESSMENT_SUCCESS,
                assessmentNameToDelete);

        ModelManager expectedModel = new ModelManager(model.getCsBook(), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);

        assertCommandSuccess(deleteAssessmentCommand, model, expectedMessage, expectedModel);
        student.addAssessment(QUIZ1); // Add back the deleted assessment for future tests
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        DeleteAssessmentCommand deleteAssessmentCommand = new DeleteAssessmentCommand(outOfBoundIndex,
                new AssessmentName(VALID_ASSESSMENT_NAME_LAB5));

        assertCommandFailure(deleteAssessmentCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student student = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(student).build();

        // Delete the assessment from the edited student
        editedStudent.updateFilteredAssessmentList(
                new AssessmentNameMatchesKeywordPredicate(VALID_ASSESSMENT_NAME_QUIZ1));

        // check to ensure the assessment exists
        assertFalse(editedStudent.getFilteredAssessmentList().size() == 0);
        Assessment assessmentToDelete = editedStudent.getFilteredAssessmentList().get(0);
        editedStudent.deleteAssessment(assessmentToDelete);

        AssessmentName assessmentNameToDelete = new AssessmentName(VALID_ASSESSMENT_NAME_QUIZ1);
        DeleteAssessmentCommand deleteAssessmentCommand = new DeleteAssessmentCommand(INDEX_FIRST_STUDENT,
                assessmentNameToDelete);
        String expectedMessage = String.format(DeleteAssessmentCommand.MESSAGE_DELETE_ASSESSMENT_SUCCESS,
                assessmentNameToDelete);

        ModelManager expectedModel = new ModelManager(model.getCsBook(), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);
        showStudentAtIndex(expectedModel, INDEX_FIRST_STUDENT);

        assertCommandSuccess(deleteAssessmentCommand, model, expectedMessage, expectedModel);
        student.addAssessment(QUIZ1); // Add back the deleted assessment for future tests
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getCsBook().getStudentList().size());

        AssessmentName assessmentNameToDelete = new AssessmentName(VALID_ASSESSMENT_NAME_QUIZ1);
        DeleteAssessmentCommand deleteAssessmentCommand = new DeleteAssessmentCommand(outOfBoundIndex,
                assessmentNameToDelete);

        assertCommandFailure(deleteAssessmentCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        AssessmentName firstAssessmentName = new AssessmentName(VALID_ASSESSMENT_NAME_QUIZ1);
        AssessmentName secondAssessmentName = new AssessmentName(VALID_ASSESSMENT_NAME_LAB5);

        DeleteAssessmentCommand deleteAssessmentFirstCommand =
                new DeleteAssessmentCommand(INDEX_FIRST_STUDENT, firstAssessmentName);
        DeleteAssessmentCommand deleteAssessmentSecondCommand =
                new DeleteAssessmentCommand(INDEX_SECOND_STUDENT, firstAssessmentName);
        DeleteAssessmentCommand deleteAssessmentThirdCommand =
                new DeleteAssessmentCommand(INDEX_SECOND_STUDENT, secondAssessmentName);

        // same object -> returns true
        assertTrue(deleteAssessmentFirstCommand.equals(deleteAssessmentFirstCommand));

        // same values -> returns true
        DeleteAssessmentCommand deleteAssessmentFirstCommandCopy = new DeleteAssessmentCommand(INDEX_FIRST_STUDENT,
                firstAssessmentName);
        assertTrue(deleteAssessmentFirstCommand.equals(deleteAssessmentFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteAssessmentFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteAssessmentFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(deleteAssessmentFirstCommand.equals(deleteAssessmentSecondCommand));

        // different assessment -> returns false
        assertFalse(deleteAssessmentSecondCommand.equals(deleteAssessmentThirdCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoStudent(Model model) {
        model.updateFilteredStudentList(p -> false);

        assertTrue(model.getFilteredStudentList().isEmpty());
    }
}
