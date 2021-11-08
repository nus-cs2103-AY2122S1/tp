package seedu.tuitione.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tuitione.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.tuitione.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tuitione.testutil.TypicalGrades.GRADE_S2;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_FIFTH_LESSON;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_FIRST_LESSON;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_FOURTH_LESSON;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_FOURTH_STUDENT;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_SECOND_LESSON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.tuitione.commons.core.Messages;
import seedu.tuitione.commons.core.index.Index;
import seedu.tuitione.model.Model;
import seedu.tuitione.model.ModelManager;
import seedu.tuitione.model.UserPrefs;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.lesson.LessonIsOfSpecifiedGrade;
import seedu.tuitione.testutil.TypicalTuition;

public class DeleteLessonCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalTuition.getTypicalTuitione(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Lesson lessonToDelete = model.getFilteredLessonList().get(INDEX_FIRST_LESSON.getZeroBased());
        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(INDEX_FIRST_LESSON);

        String expectedMessage = String.format(DeleteLessonCommand.MESSAGE_DELETE_LESSON_SUCCESS, lessonToDelete);

        ModelManager expectedModel = new ModelManager(model.getTuitione(), new UserPrefs());
        expectedModel.deleteLesson(lessonToDelete);

        assertCommandSuccess(deleteLessonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredLessonList().size() + 1);
        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(outOfBoundIndex);

        assertCommandFailure(deleteLessonCommand, model, Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        model.updateFilteredLessonList(new LessonIsOfSpecifiedGrade(GRADE_S2));

        Lesson lessonToDelete = model.getFilteredLessonList().get(INDEX_FIRST_LESSON.getZeroBased());
        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(INDEX_FIRST_LESSON);

        String expectedMessage = String.format(DeleteLessonCommand.MESSAGE_DELETE_LESSON_SUCCESS, lessonToDelete);

        ModelManager expectedModel = new ModelManager(model.getTuitione(), new UserPrefs());
        expectedModel.updateFilteredLessonList(new LessonIsOfSpecifiedGrade(GRADE_S2));
        expectedModel.deleteLesson(lessonToDelete);

        assertCommandSuccess(deleteLessonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        model.updateFilteredLessonList(new LessonIsOfSpecifiedGrade(GRADE_S2));

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredLessonList().size() + 1);
        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(outOfBoundIndex);


        assertCommandFailure(deleteLessonCommand, model, Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_lessonWithStudents_success() {
        Lesson lessonToDelete = model.getFilteredLessonList().get(INDEX_FIFTH_LESSON.getZeroBased());
        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(INDEX_FIFTH_LESSON);

        String expectedMessage = String.format(DeleteLessonCommand.MESSAGE_DELETE_LESSON_SUCCESS, lessonToDelete);

        ModelManager expectedModel = new ModelManager(model.getTuitione(), new UserPrefs());
        expectedModel.deleteLesson(lessonToDelete);

        assertCommandSuccess(deleteLessonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_studentEnrolledInMultipleLessons_success() {
        Lesson lessonToDelete = model.getFilteredLessonList().get(INDEX_FOURTH_LESSON.getZeroBased());
        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(INDEX_FOURTH_LESSON);

        String expectedMessage = String.format(DeleteLessonCommand.MESSAGE_DELETE_LESSON_SUCCESS, lessonToDelete);

        ModelManager expectedModel = new ModelManager(model.getTuitione(), new UserPrefs());
        expectedModel.deleteLesson(lessonToDelete);

        assertCommandSuccess(deleteLessonCommand, model, expectedMessage, expectedModel);
        assertEquals(1, model.getFilteredStudentList()
                .get(INDEX_FOURTH_STUDENT.getZeroBased())
                .getLessons().size());
    }

    @Test
    public void equals() {
        DeleteLessonCommand deleteLessonFirstCommand = new DeleteLessonCommand(INDEX_FIRST_LESSON);
        DeleteLessonCommand deleteLessonSecondCommand = new DeleteLessonCommand(INDEX_SECOND_LESSON);

        // same object -> returns true
        assertTrue(deleteLessonFirstCommand.equals(deleteLessonFirstCommand));

        // same values -> returns true
        DeleteLessonCommand deleteLessonFirstCommandCopy = new DeleteLessonCommand(INDEX_FIRST_LESSON);
        assertTrue(deleteLessonFirstCommand.equals(deleteLessonFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteLessonFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteLessonFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(deleteLessonFirstCommand.equals(deleteLessonSecondCommand));
    }
}
