package tutoraid.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.model.Model;
import tutoraid.model.ModelManager;
import tutoraid.model.UserPrefs;
import tutoraid.model.lesson.Lesson;
import tutoraid.testutil.TypicalIndexes;
import tutoraid.testutil.TypicalLessons;
import tutoraid.testutil.TypicalStudents;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteLessonCommand}.
 */
public class DeleteLessonCommandTest {

    private Model model = new ModelManager(TypicalStudents.getTypicalStudentBook(),
            TypicalLessons.getTypicalLessonBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Lesson lessonToDelete = model.getFilteredLessonList().get(TypicalIndexes.INDEX_FIRST_ITEM.getZeroBased());
        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(TypicalIndexes.INDEX_FIRST_ITEM);

        String expectedMessage = String.format(DeleteLessonCommand.MESSAGE_DELETE_LESSON_SUCCESS,
                lessonToDelete.toNameString());

        ModelManager expectedModel = new ModelManager(model.getStudentBook(), model.getLessonBook(), new UserPrefs());
        expectedModel.deleteLesson(lessonToDelete);

        CommandTestUtil.assertCommandSuccess(deleteLessonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outofBoundIndex = Index.fromOneBased(model.getFilteredLessonList().size() + 1);
        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(outofBoundIndex);

        CommandTestUtil.assertCommandFailure(deleteLessonCommand, model,
                Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        CommandTestUtil.showLessonAtIndex(model, TypicalIndexes.INDEX_FIRST_ITEM);

        Lesson lessonToDelete = model.getFilteredLessonList().get(TypicalIndexes.INDEX_FIRST_ITEM.getZeroBased());
        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(TypicalIndexes.INDEX_FIRST_ITEM);

        String expectedMessage = String.format(DeleteLessonCommand.MESSAGE_DELETE_LESSON_SUCCESS,
                lessonToDelete.toNameString());

        Model expectedModel = new ModelManager(model.getStudentBook(), model.getLessonBook(), new UserPrefs());
        expectedModel.deleteLesson(lessonToDelete);
        showNoLesson(expectedModel);

        CommandTestUtil.assertCommandSuccess(deleteLessonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        CommandTestUtil.showLessonAtIndex(model, TypicalIndexes.INDEX_FIRST_ITEM);

        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_ITEM;
        // ensures that outOfBoundIndex is still in bounds of lesson book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLessonBook().getLessonList().size());

        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(deleteLessonCommand, model,
                Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteLessonCommand deleteFirstLessonCommand = new DeleteLessonCommand(TypicalIndexes.INDEX_FIRST_ITEM);
        DeleteLessonCommand deleteSecondLessonCommand = new DeleteLessonCommand(TypicalIndexes.INDEX_SECOND_ITEM);

        // same object -> returns true
        assertTrue(deleteFirstLessonCommand.equals(deleteFirstLessonCommand));

        // same values -> returns true
        DeleteLessonCommand deleteFirstLessonCommandCopy = new DeleteLessonCommand(TypicalIndexes.INDEX_FIRST_ITEM);
        assertTrue(deleteFirstLessonCommand.equals(deleteFirstLessonCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstLessonCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstLessonCommand.equals(null));

        // different lesson -> returns false
        assertFalse(deleteFirstLessonCommand.equals(deleteSecondLessonCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoLesson(Model model) {
        model.updateFilteredLessonList(p -> false);
        assertTrue(model.getFilteredLessonList().isEmpty());
    }
}
