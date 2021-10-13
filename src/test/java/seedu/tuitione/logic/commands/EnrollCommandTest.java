package seedu.tuitione.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.tuitione.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.tuitione.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
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
        LessonCode code = new LessonCode("Science-P2-Wed-1230");
        EnrollCommand enrollCommand = new EnrollCommand(INDEX_FIRST_STUDENT, code.value);

        Model expectedModel = new ModelManager(getTypicalTuitione(), new UserPrefs());
        Student studentToEnroll = expectedModel.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());

        Lesson testLesson = expectedModel.searchLessons(code).get();
        testLesson.addStudent(studentToEnroll);
        String expectedMessage = String.format(EnrollCommand.MESSAGE_SUCCESS, studentToEnroll.getName(), testLesson);

        assertCommandSuccess(enrollCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidEnrollmentNotSameGrade_failure() {
        LessonCode code = new LessonCode("Mathematics-S2-Tue-0930");
        Student alice = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased()); //ALICE
        Student carl = model.getFilteredStudentList().get(INDEX_THIRD_STUDENT.getZeroBased()); //CARL

        String expectedMessageAlice = String.format(EnrollCommand.MESSAGE_UNABLE_TO_ENROLL, alice.getName(), code);
        String expectedMessageCarl = String.format(EnrollCommand.MESSAGE_UNABLE_TO_ENROLL, carl.getName(), code);

        assertCommandFailure(new EnrollCommand(INDEX_FIRST_STUDENT, code.value), model, expectedMessageAlice);
        assertCommandFailure(new EnrollCommand(INDEX_THIRD_STUDENT, code.value), model, expectedMessageCarl);
    }

    @Test
    public void execute_invalidEnrollmentAlreadyInLesson_failure() {
        LessonCode code = new LessonCode("Mathematics-S2-Tue-0930");
        Student benson = model.getFilteredStudentList().get(INDEX_SECOND_STUDENT.getZeroBased()); //BENSON

        String expectedMessageBenson = String.format(EnrollCommand.MESSAGE_STUDENT_IN_LESSON, benson.getName(), code);

        assertCommandFailure(new EnrollCommand(INDEX_SECOND_STUDENT, code.value), model, expectedMessageBenson);
    }

    @Test
    public void execute_invalidEnrollmentWrongIndex_failure() {
        LessonCode code = new LessonCode("Mathematics-S2-Tue-0930");
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        EnrollCommand enrollCommand = new EnrollCommand(outOfBoundIndex, code.value);

        assertCommandFailure(enrollCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        EnrollCommand enrollFirstCommand = new EnrollCommand(INDEX_FIRST_STUDENT, "l/Science-P5-Wed-1230");
        EnrollCommand enrollSecondCommand = new EnrollCommand(INDEX_SECOND_STUDENT, "l/Science-P5-Wed-1230");

        // same object -> returns true
        assertEquals(enrollFirstCommand, enrollFirstCommand);

        // same values -> returns true
        EnrollCommand enrollFirstCommandCopy = new EnrollCommand(INDEX_FIRST_STUDENT, "l/Science-P5-Wed-1230");
        assertEquals(enrollFirstCommand, enrollFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, enrollFirstCommand);

        // null -> returns false
        assertNotEquals(null, enrollFirstCommand);

        // different student -> returns false
        assertNotEquals(enrollFirstCommand, enrollSecondCommand);
    }
}
