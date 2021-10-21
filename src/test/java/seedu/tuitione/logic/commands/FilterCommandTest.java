package seedu.tuitione.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tuitione.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tuitione.testutil.TypicalTuition.ALICE;
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
import seedu.tuitione.model.student.Grade;
import seedu.tuitione.model.student.StudentIsOfSpecifiedGrade;

public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalTuitione(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTuitione(), new UserPrefs());

    @Test
    public void equals() {
        Grade firstGrade = new Grade("S1");
        Grade secondGrade = new Grade("S2");

        FilterCommand filterFirstCommand = new FilterCommand(firstGrade, null);
        FilterCommand filterSecondCommand = new FilterCommand(secondGrade, null);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterCommand(firstGrade, null);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
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
    public void execute_gradeFoundInTuitioneoneStudentFound_oneLessonFound() {
        String expectedMessage = String.format(Messages.MESSAGE_STUDENTS_FOUND_OVERVIEW, 1)
                + "\n"
                + String.format(Messages.MESSAGE_LESSON_FOUND_OVERVIEW, 1);
        Grade grade = new Grade("P2");
        FilterCommand command = new FilterCommand(grade, null);
        expectedModel.updateFilteredStudentList(new StudentIsOfSpecifiedGrade(grade));
        expectedModel.updateFilteredLessonList(new LessonIsOfSpecifiedGrade(grade));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredStudentList());
        assertEquals(Arrays.asList(SCIENCE_P2), model.getFilteredLessonList());
    }

    @Test
    public void execute_gradeFoundInTuitione_oneStudentFoundmultipleLessonFound() {
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

}
