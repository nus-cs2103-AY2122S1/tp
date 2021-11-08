package seedu.tuitione.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.tuitione.commons.core.Messages.HEADER_UPDATE;
import static seedu.tuitione.commons.core.Messages.MESSAGE_LESSON_FOUND_OVERVIEW;
import static seedu.tuitione.commons.core.Messages.MESSAGE_STUDENTS_FOUND_OVERVIEW;
import static seedu.tuitione.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tuitione.testutil.TypicalTuition.BENSON;
import static seedu.tuitione.testutil.TypicalTuition.MATH_S2;
import static seedu.tuitione.testutil.TypicalTuition.PHYSICS_S2;
import static seedu.tuitione.testutil.TypicalTuition.SCIENCE_P2;
import static seedu.tuitione.testutil.TypicalTuition.getTypicalTuitione;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.tuitione.model.Model;
import seedu.tuitione.model.ModelManager;
import seedu.tuitione.model.UserPrefs;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.lesson.LessonIsOfSpecifiedGrade;
import seedu.tuitione.model.lesson.LessonIsOfSpecifiedGradeAndSubject;
import seedu.tuitione.model.lesson.LessonIsOfSpecifiedSubject;
import seedu.tuitione.model.lesson.Subject;
import seedu.tuitione.model.student.Grade;
import seedu.tuitione.model.student.Student;
import seedu.tuitione.model.student.StudentIsOfSpecifiedGrade;
import seedu.tuitione.testutil.LessonBuilder;
import seedu.tuitione.testutil.StudentBuilder;

public class FilterCommandTest {

    private Model model;
    private Model expectedModel;
    private Student localBenson;
    private Lesson localMathS2;

    @BeforeEach
    public void setup() {
        model = new ModelManager(getTypicalTuitione(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalTuitione(), new UserPrefs());
        localBenson = new StudentBuilder(BENSON).build();
        localMathS2 = new LessonBuilder(MATH_S2).build();
        localBenson.enrollForLesson(localMathS2);
    }

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
        assertEquals(filterFirstCommand, filterFirstCommand);

        // same object, one field is null -> returns true
        assertEquals(filterFirstCommandWithoutGrade, filterFirstCommandWithoutGrade);
        assertEquals(filterFirstCommandWithoutSubject, filterFirstCommandWithoutSubject);

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterCommand(firstGrade, engSubject);
        assertEquals(filterFirstCommand, filterFirstCommandCopy);

        // same values, one field is null -> returns true
        FilterCommand filterFirstCommandWithoutSubjectCopy = new FilterCommand(firstGrade, null);
        FilterCommand filterFirstCommandWithoutGradeCopy = new FilterCommand(null, engSubject);
        assertEquals(filterFirstCommandWithoutSubject, filterFirstCommandWithoutSubjectCopy);
        assertEquals(filterFirstCommandWithoutGrade, filterFirstCommandWithoutGradeCopy);

        // different types -> returns false
        assertNotEquals(1, filterFirstCommand);

        // null -> returns false
        assertNotEquals(null, filterFirstCommand);

        // both fields different values -> returns false
        assertNotEquals(filterFirstCommand, filterSecondCommand);

        // one field same value -> returns false
        assertNotEquals(filterFirstCommand, filterFirstCommandWithoutSubject);
        assertNotEquals(filterFirstCommand, filterFirstCommandWithoutGrade);
    }

    @Test
    public void execute_gradeNotFoundInTuitione_noStudentAndLessonFound() {
        String expectedMessage = String.format("ℹ\tUpdate:\n" + MESSAGE_STUDENTS_FOUND_OVERVIEW, 0)
                + "\n"
                + String.format(MESSAGE_LESSON_FOUND_OVERVIEW, 0);
        Grade grade = new Grade("S4");
        FilterCommand command = new FilterCommand(grade, null);
        expectedModel.updateFilteredStudentList(new StudentIsOfSpecifiedGrade(grade));
        expectedModel.updateFilteredLessonList(new LessonIsOfSpecifiedGrade(grade));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList()); // no student found
        assertEquals(Collections.emptyList(), model.getFilteredLessonList()); // no lesson found
    }

    @Test
    public void execute_subjectNotFoundInTuitione_noLessonFound() {
        String expectedMessage = String.format(HEADER_UPDATE + MESSAGE_LESSON_FOUND_OVERVIEW, 0);
        Subject subject = new Subject("Chemistry");
        FilterCommand command = new FilterCommand(null, subject);
        expectedModel.updateFilteredLessonList(new LessonIsOfSpecifiedSubject(subject));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredLessonList()); // no lesson found
    }

    @Test
    public void execute_gradeFoundInTuitione_studentFoundLessonFound() {
        String expectedMessage = String.format(HEADER_UPDATE + MESSAGE_STUDENTS_FOUND_OVERVIEW, 1)
                + "\n"
                + String.format(MESSAGE_LESSON_FOUND_OVERVIEW, 2);
        Grade grade = new Grade("S2");
        FilterCommand command = new FilterCommand(grade, null);
        expectedModel.updateFilteredStudentList(new StudentIsOfSpecifiedGrade(grade));
        expectedModel.updateFilteredLessonList(new LessonIsOfSpecifiedGrade(grade));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        assertEquals(List.of(localBenson), model.getFilteredStudentList()); // student found
        assertEquals(Arrays.asList(localMathS2, PHYSICS_S2), model.getFilteredLessonList()); // lessons found
    }

    @Test
    public void execute_subjectFoundInTuitione_lessonFound() {
        String expectedMessage = String.format(HEADER_UPDATE + MESSAGE_LESSON_FOUND_OVERVIEW, 1);
        Subject subject = new Subject("Science");
        FilterCommand command = new FilterCommand(null, subject);
        expectedModel.updateFilteredLessonList(new LessonIsOfSpecifiedSubject(subject));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(SCIENCE_P2), model.getFilteredLessonList()); // lesson found
    }

    @Test
    public void execute_gradeFoundSubjectFoundInTuitione_studentAndLessonFound() {
        String expectedMessage = String.format(HEADER_UPDATE + MESSAGE_STUDENTS_FOUND_OVERVIEW, 1)
                + "\n"
                + String.format(MESSAGE_LESSON_FOUND_OVERVIEW, 1);
        Grade grade = new Grade("S2");
        Subject subject = new Subject("Mathematics");
        FilterCommand command = new FilterCommand(grade, subject);
        expectedModel.updateFilteredStudentList(new StudentIsOfSpecifiedGrade(grade));
        expectedModel.updateFilteredLessonList(new LessonIsOfSpecifiedGradeAndSubject(grade, subject));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        assertEquals(List.of(localBenson), model.getFilteredStudentList()); // student found
        assertEquals(List.of(localMathS2), model.getFilteredLessonList()); // lesson found
    }
}
