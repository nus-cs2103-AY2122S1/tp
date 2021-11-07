package tutoraid.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutoraid.logic.commands.CommandTestUtil.assertCommandFailure;
import static tutoraid.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tutoraid.logic.commands.CommandTestUtil.showLessonAtIndex;
import static tutoraid.logic.commands.ViewLessonCommand.MESSAGE_VIEW_LESSON_SUCCESS;
import static tutoraid.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static tutoraid.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static tutoraid.testutil.TypicalLessons.getTypicalLessonBook;
import static tutoraid.testutil.TypicalStudents.getTypicalStudentBook;

import org.junit.jupiter.api.Test;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.model.Model;
import tutoraid.model.ModelManager;
import tutoraid.model.UserPrefs;
import tutoraid.model.lesson.Lesson;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ViewLessonCommand}.
 */
public class ViewLessonCommandTest {

    private Model model = new ModelManager(getTypicalStudentBook(), getTypicalLessonBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Lesson lessonToView = model.getFilteredLessonList().get(INDEX_FIRST_ITEM.getZeroBased());
        ViewLessonCommand viewLessonCommand = new ViewLessonCommand(INDEX_FIRST_ITEM);

        String expectedMessage = String.format(MESSAGE_VIEW_LESSON_SUCCESS, lessonToView.toNameString());

        ModelManager expectedModel = new ModelManager(model.getStudentBook(), model.getLessonBook(), new UserPrefs());
        expectedModel.viewLesson(lessonToView);

        assertCommandSuccess(viewLessonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredLessonList().size() + 1);
        ViewLessonCommand viewLessonCommand = new ViewLessonCommand(outOfBoundIndex);

        assertCommandFailure(viewLessonCommand, model, Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showLessonAtIndex(model, INDEX_FIRST_ITEM);

        Lesson lessonToView = model.getFilteredLessonList().get(INDEX_FIRST_ITEM.getZeroBased());
        ViewLessonCommand viewLessonCommand = new ViewLessonCommand(INDEX_FIRST_ITEM);

        String expectedMessage = String.format(MESSAGE_VIEW_LESSON_SUCCESS, lessonToView.toNameString());

        Model expectedModel = new ModelManager(model.getStudentBook(), model.getLessonBook(), new UserPrefs());
        expectedModel.viewLesson(lessonToView);

        assertCommandSuccess(viewLessonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showLessonAtIndex(model, INDEX_FIRST_ITEM);

        Index outOfBoundIndex = INDEX_SECOND_ITEM;
        // ensures that outOfBoundIndex is still in bounds of lesson list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLessonBook().getLessonList().size());

        ViewLessonCommand viewLessonCommand = new ViewLessonCommand(outOfBoundIndex);

        assertCommandFailure(viewLessonCommand, model, Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewLessonCommand viewFirstCommand = new ViewLessonCommand(INDEX_FIRST_ITEM);
        ViewLessonCommand viewSecondCommand = new ViewLessonCommand(INDEX_SECOND_ITEM);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewLessonCommand viewFirstCommandCopy = new ViewLessonCommand(INDEX_FIRST_ITEM);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different lesson -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }
}
