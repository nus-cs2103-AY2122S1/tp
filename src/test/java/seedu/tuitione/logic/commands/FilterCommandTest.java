package seedu.tuitione.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tuitione.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tuitione.testutil.TypicalTuition.BENSON;
import static seedu.tuitione.testutil.TypicalTuition.MATH_S2;
import static seedu.tuitione.testutil.TypicalTuition.PHYSICS_S2;
import static seedu.tuitione.testutil.TypicalTuition.SCIENCE_P2;
import static seedu.tuitione.testutil.TypicalTuition.getTypicalTuitione;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.tuitione.commons.core.Messages;
import seedu.tuitione.model.Model;
import seedu.tuitione.model.ModelManager;
import seedu.tuitione.model.UserPrefs;
import seedu.tuitione.model.lesson.LessonIsOfSpecifiedGrade;
import seedu.tuitione.model.lesson.LessonIsOfSpecifiedGradeAndSubject;
import seedu.tuitione.model.lesson.LessonIsOfSpecifiedSubject;
import seedu.tuitione.model.lesson.Subject;
import seedu.tuitione.model.student.Grade;
import seedu.tuitione.model.student.StudentIsOfSpecifiedGrade;

public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalTuitione(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTuitione(), new UserPrefs());

    @Test
    public void equals() {
        Grade firstGrade = new Grade("S1");
        Grade secondGrade = new Grade("S2");
        Subject engSubject = new Subject("English");
        Subject mathSubject = new Subject("Math");

        FilterCommand filterFirstCommand = new FilterCommand(firstGrade, engSubject);
        FilterCommand filterFirstCommandWithoutSubject = new FilterCommand(firstGrade, null);
        FilterCommand filterFirstCommandWithoutGrade = new FilterCommand(null, engSubject);
        FilterCommand filterSecondCommand = new FilterCommand(secondGrade, mathSubject);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same object, one field is null -> returns true
        assertTrue(filterFirstCommandWithoutGrade.equals(filterFirstCommandWithoutGrade));
        assertTrue(filterFirstCommandWithoutSubject.equals(filterFirstCommandWithoutSubject));

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterCommand(firstGrade, engSubject);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // same values, one field is null -> returns true
        FilterCommand filterFirstCommandWithoutSubjectCopy = new FilterCommand(firstGrade, null);
        FilterCommand filterFirstCommandWithoutGradeCopy = new FilterCommand(null, engSubject);
        assertTrue(filterFirstCommandWithoutSubject.equals(filterFirstCommandWithoutSubjectCopy));
        assertTrue(filterFirstCommandWithoutGrade.equals(filterFirstCommandWithoutGradeCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // both fields different values -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));

        // one field same value -> returns false
        assertFalse(filterFirstCommand.equals(filterFirstCommandWithoutSubject));
        assertFalse(filterFirstCommand.equals(filterFirstCommandWithoutGrade));
    }

    @Test
    public void execute_gradeNotFoundInTuitione_noStudentFoundnoLessonFound() {
        String expectedMessage = String.format(Messages.MESSAGE_STUDENTS_FOUND_OVERVIEW, 0)
                + "\n"
                + String.format(Messages.MESSAGE_LESSON_FOUND_OVERVIEW, 0);
        Grade grade = new Grade("S3");
        FilterCommand command = new FilterCommand(grade, null);
        expectedModel.updateFilteredStudentList(new StudentIsOfSpecifiedGrade(grade));
        expectedModel.updateFilteredLessonList(new LessonIsOfSpecifiedGrade(grade));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
        assertEquals(Collections.emptyList(), model.getFilteredLessonList());
    }

    @Test
    public void execute_subjectNotFoundInTuitione_noLessonFound() {
        String expectedMessage = String.format(Messages.MESSAGE_LESSON_FOUND_OVERVIEW, 0);
        Subject subject = new Subject("Chemistry");
        FilterCommand command = new FilterCommand(null, subject);
        expectedModel.updateFilteredLessonList(new LessonIsOfSpecifiedSubject(subject));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredLessonList());
    }

    @Test
    public void execute_gradeFoundInTuitione_studentFoundLessonFound() {
        String expectedMessage = String.format(Messages.MESSAGE_STUDENTS_FOUND_OVERVIEW, 1)
                + "\n"
                + String.format(Messages.MESSAGE_LESSON_FOUND_OVERVIEW, 2);
        Grade grade = new Grade("S2");
        MATH_S2.enrollStudent(BENSON);
        FilterCommand command = new FilterCommand(grade, null);
        expectedModel.updateFilteredStudentList(new StudentIsOfSpecifiedGrade(grade));
        expectedModel.updateFilteredLessonList(new LessonIsOfSpecifiedGrade(grade));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        assertEquals(Arrays.asList(BENSON), model.getFilteredStudentList());
        assertEquals(Arrays.asList(MATH_S2, PHYSICS_S2), model.getFilteredLessonList());
        MATH_S2.unenrollStudent(BENSON);
    }

    @Test
    public void execute_subjectFoundInTuitione_lessonFound() {
        String expectedMessage = String.format(Messages.MESSAGE_LESSON_FOUND_OVERVIEW, 1);
        Subject subject = new Subject("Science");
        FilterCommand command = new FilterCommand(null, subject);
        expectedModel.updateFilteredLessonList(new LessonIsOfSpecifiedSubject(subject));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(SCIENCE_P2), model.getFilteredLessonList());
    }

    @Test
    public void execute_gradeFoundSubjectFoundInTuitione_studentFoundLessonFound() {
        String expectedMessage = String.format(Messages.MESSAGE_STUDENTS_FOUND_OVERVIEW, 1)
                + "\n"
                + String.format(Messages.MESSAGE_LESSON_FOUND_OVERVIEW, 1);
        Grade grade = new Grade("S2");
        Subject subject = new Subject("Mathematics");
        MATH_S2.enrollStudent(BENSON);
        FilterCommand command = new FilterCommand(grade, subject);
        expectedModel.updateFilteredStudentList(new StudentIsOfSpecifiedGrade(grade));
        expectedModel.updateFilteredLessonList(new LessonIsOfSpecifiedGradeAndSubject(grade, subject));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        assertEquals(Arrays.asList(BENSON), model.getFilteredStudentList());
        assertEquals(Arrays.asList(MATH_S2), model.getFilteredLessonList());
        MATH_S2.unenrollStudent(BENSON);
    }
}
