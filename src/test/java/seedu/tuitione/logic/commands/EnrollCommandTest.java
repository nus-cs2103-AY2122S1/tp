package seedu.tuitione.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.tuitione.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.tuitione.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_FIRST_LESSON;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_SECOND_LESSON;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;
import static seedu.tuitione.testutil.TypicalTuition.getTypicalTuitione;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.tuitione.commons.core.Messages;
import seedu.tuitione.commons.core.index.Index;
import seedu.tuitione.model.Model;
import seedu.tuitione.model.ModelManager;
import seedu.tuitione.model.UserPrefs;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.lesson.LessonCode;
import seedu.tuitione.model.student.Student;

public class EnrollCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTuitione(), new UserPrefs());
    }

    @Test
    public void execute_validEnrollment_success() {
        EnrollCommand enrollCommand = new EnrollCommand(INDEX_FIRST_STUDENT, INDEX_FIRST_LESSON);

        Model expectedModel = new ModelManager(getTypicalTuitione(), new UserPrefs());
        Student studentToEnroll = expectedModel.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());

        Lesson testLesson = expectedModel.getFilteredLessonList().get(INDEX_FIRST_LESSON.getZeroBased());
        testLesson.enrollStudent(studentToEnroll);
        String expectedMessage = String.format(EnrollCommand.MESSAGE_SUCCESS, studentToEnroll.getName(), testLesson);

        assertCommandSuccess(enrollCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidEnrollmentNotSameGrade_failure() {
        Lesson testLesson = model.getFilteredLessonList().get(INDEX_SECOND_LESSON.getZeroBased());
        Student alice = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased()); //ALICE
        Student carl = model.getFilteredStudentList().get(INDEX_THIRD_STUDENT.getZeroBased()); //CARL

        String expectedMessageAlice = String.format(EnrollCommand.MESSAGE_UNABLE_TO_ENROLL,
                alice.getName(),
                testLesson.getLessonCode());
        String expectedMessageCarl = String.format(EnrollCommand.MESSAGE_UNABLE_TO_ENROLL,
                carl.getName(),
                testLesson.getLessonCode());

        assertCommandFailure(new EnrollCommand(INDEX_FIRST_STUDENT, INDEX_SECOND_LESSON),
                model,
                expectedMessageAlice);
        assertCommandFailure(new EnrollCommand(INDEX_THIRD_STUDENT, INDEX_SECOND_LESSON),
                model,
                expectedMessageCarl);
    }

    @Test
    public void execute_invalidEnrollmentAlreadyInLesson_failure() {
        Lesson testLesson = model.getFilteredLessonList().get(INDEX_SECOND_LESSON.getZeroBased());
        LessonCode code = testLesson.getLessonCode();
        Student benson = model.getFilteredStudentList().get(INDEX_SECOND_STUDENT.getZeroBased()); //BENSON

        String expectedMessageBenson = String.format(EnrollCommand.MESSAGE_STUDENT_IN_LESSON,
                benson.getName(),
                code);

        assertCommandFailure(new EnrollCommand(INDEX_SECOND_STUDENT, INDEX_SECOND_LESSON),
                model,
                expectedMessageBenson);
    }

    @Test
    public void execute_invalidEnrollmentWrongIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        EnrollCommand enrollCommand = new EnrollCommand(outOfBoundIndex, INDEX_SECOND_LESSON);

        assertCommandFailure(enrollCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        EnrollCommand enrollFirstCommand = new EnrollCommand(INDEX_FIRST_STUDENT, INDEX_FIRST_LESSON);
        EnrollCommand enrollSecondCommand = new EnrollCommand(INDEX_SECOND_STUDENT, INDEX_FIRST_LESSON);

        // same object -> returns true
        assertEquals(enrollFirstCommand, enrollFirstCommand);

        // same values -> returns true
        EnrollCommand enrollFirstCommandCopy = new EnrollCommand(INDEX_FIRST_STUDENT, INDEX_FIRST_LESSON);
        assertEquals(enrollFirstCommand, enrollFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, enrollFirstCommand);

        // null -> returns false
        assertNotEquals(null, enrollFirstCommand);

        // different student -> returns false
        assertNotEquals(enrollFirstCommand, enrollSecondCommand);
    }
}
