package seedu.academydirectory.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.academydirectory.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.academydirectory.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.academydirectory.testutil.Assert.assertThrows;
import static seedu.academydirectory.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.academydirectory.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.academydirectory.testutil.TypicalStudents.getTypicalAcademyDirectory;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.commons.core.Messages;
import seedu.academydirectory.commons.core.index.Index;
import seedu.academydirectory.model.AcademyDirectory;
import seedu.academydirectory.model.Model;
import seedu.academydirectory.model.ModelManager;
import seedu.academydirectory.model.UserPrefs;
import seedu.academydirectory.model.student.Assessment;
import seedu.academydirectory.model.student.Student;
import seedu.academydirectory.testutil.StudentBuilder;
import seedu.academydirectory.testutil.TypicalStudents;

class GradeCommandTest {

    private Model model = new ModelManager(getTypicalAcademyDirectory(), new UserPrefs());
    private final String validAssessmentName1 = "RA1";
    private final String validAssessmentName2 = "MIDTERM";
    private final Integer validGrade1 = 10;
    private final Integer validGrade2 = 20;

    @Test
    public void constructor_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GradeCommand(null, null, null));
    }

    @Test
    void execute_addGrade_success() {
        Student firstStudent = model.getFilteredStudentList().get(0);
        Student editedStudent = new StudentBuilder(firstStudent).withAssessment().build();
        GradeCommand addGradeCommand =
                new GradeCommand(INDEX_FIRST_STUDENT, validAssessmentName1, validGrade1);
        String expectedMessage =
                String.format(GradeCommand.MESSAGE_SUCCESS, editedStudent.getName(), validAssessmentName1);
        Model expectedModel = new ModelManager(new AcademyDirectory(model.getAcademyDirectory()), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);
        assertCommandSuccess(addGradeCommand, model, expectedMessage, expectedModel);
        model.getAcademyDirectory().getStudentList().get(0).setAssessment(new Assessment());
        TypicalStudents.getTypicalStudents().get(0).setAssessment(new Assessment());
        TypicalStudents.getTypicalAcademyDirectory().getStudentList().get(0).setAssessment(new Assessment());
        model.getFilteredStudentList().get(0).setAssessment(new Assessment());
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        GradeCommand gradeCommand = new GradeCommand(outOfBoundIndex, validAssessmentName1, validGrade1);

        assertCommandFailure(gradeCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final GradeCommand standardCommand =
                new GradeCommand(INDEX_FIRST_STUDENT, validAssessmentName1, validGrade1);

        // same values -> returns true
        GradeCommand commandWithSameValues =
                new GradeCommand(INDEX_FIRST_STUDENT, validAssessmentName1, validGrade1);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(
                new GradeCommand(INDEX_SECOND_STUDENT, validAssessmentName1, validGrade1)));

        // different assessment -> returns false
        assertFalse(standardCommand.equals(
                new GradeCommand(INDEX_FIRST_STUDENT, validAssessmentName2, validGrade1)));

        // different grade -> returns false
        assertFalse(standardCommand.equals(
                new GradeCommand(INDEX_FIRST_STUDENT, validAssessmentName1, validGrade2)));
    }

}
