package seedu.address.logic.commands.modulelesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2040;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2106;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;
import static seedu.address.testutil.TypicalModuleLessons.getTypicalConthacks;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.person.DeletePersonCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.modulelesson.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.modulelesson.ModuleLesson;

public class DeleteModuleLessonCommandTest {

    private Model model = new ModelManager(getTypicalConthacks(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalConthacks(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        //Deletes 1 module lesson
        ModuleLesson lessonToDelete = model.getFilteredModuleLessonList().get(INDEX_FIRST.getZeroBased());
        DeleteModuleLessonCommand deleteModuleLessonCommand = new DeleteModuleLessonCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteModuleLessonCommand.MESSAGE_DELETE_LESSON_SUCCESS, lessonToDelete);
        expectedMessage = String.format(DeleteModuleLessonCommand.MESSAGE_NUMBER_DELETED_LESSONS, 1) + expectedMessage;

        expectedModel.deleteLesson(lessonToDelete);
        assertCommandSuccess(deleteModuleLessonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validRangeUnfilteredList_success() {
        //Deletes 2 persons
        ModuleLesson lessonToDelete1 = model.getFilteredModuleLessonList().get(INDEX_FIRST.getZeroBased());
        ModuleLesson lessonToDelete2 = model.getFilteredModuleLessonList().get(INDEX_SECOND.getZeroBased());
        DeleteModuleLessonCommand deleteModuleLessonCommand = new DeleteModuleLessonCommand(INDEX_FIRST, INDEX_SECOND);

        String expectedMessage = String.format(
                DeleteModuleLessonCommand.MESSAGE_DELETE_LESSON_SUCCESS, lessonToDelete1)
                + String.format(DeleteModuleLessonCommand.MESSAGE_DELETE_LESSON_SUCCESS, lessonToDelete2);
        expectedMessage = String.format(DeleteModuleLessonCommand.MESSAGE_NUMBER_DELETED_LESSONS, 2) + expectedMessage;

        expectedModel.deleteLesson(lessonToDelete1);
        expectedModel.deleteLesson(lessonToDelete2);
        assertCommandSuccess(deleteModuleLessonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validModuleCodeUnfilteredList_success() {
        ModuleCodeContainsKeywordsPredicate predicate =
                new ModuleCodeContainsKeywordsPredicate(Collections.singletonList(VALID_MODULE_CODE_CS2106));
        ModuleLesson lessonToDelete = model.getFilteredModuleLessonList().get(INDEX_THIRD.getZeroBased());
        DeleteModuleLessonCommand deleteModuleLessonCommand = new DeleteModuleLessonCommand(predicate);

        String expectedMessage = String.format(DeleteModuleLessonCommand.MESSAGE_NUMBER_DELETED_LESSONS, 1)
                + String.format(DeleteModuleLessonCommand.MESSAGE_DELETE_LESSON_SUCCESS, lessonToDelete);

        expectedModel.deleteLesson(lessonToDelete);
        assertCommandSuccess(deleteModuleLessonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredModuleLessonList().size() + 1);
        DeleteModuleLessonCommand deleteModuleLessonCommand = new DeleteModuleLessonCommand(outOfBoundIndex);
        String expectedMessage = DeleteModuleLessonCommand.MESSAGE_INVALID_FORMAT
                + DeleteModuleLessonCommand.MESSAGE_USAGE;

        assertCommandFailure(deleteModuleLessonCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidRangeUnfilteredList_throwsCommandException() {
        DeleteModuleLessonCommand deleteModuleLessonCommand = new DeleteModuleLessonCommand(INDEX_SECOND, INDEX_FIRST);
        String expectedMessage = DeleteModuleLessonCommand.MESSAGE_INVALID_FORMAT
                + DeleteModuleLessonCommand.MESSAGE_USAGE;

        assertCommandFailure(deleteModuleLessonCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidEndIndexRangeUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredModuleLessonList().size() + 1);
        DeleteModuleLessonCommand deleteModuleLessonCommand =
                new DeleteModuleLessonCommand(INDEX_SECOND, outOfBoundIndex);

        String expectedMessage = DeleteModuleLessonCommand.MESSAGE_INVALID_FORMAT
                + DeleteModuleLessonCommand.MESSAGE_USAGE;

        assertCommandFailure(deleteModuleLessonCommand, model, expectedMessage);
    }

    @Test
    public void execute_moduleCodeNotInUnfilteredList_throwsCommandException() {
        ModuleCodeContainsKeywordsPredicate predicate = new ModuleCodeContainsKeywordsPredicate(
                Collections.singletonList("CS2030S")
        );
        DeleteModuleLessonCommand deleteModuleLessonCommand = new DeleteModuleLessonCommand(predicate);

        assertCommandFailure(deleteModuleLessonCommand, model, DeletePersonCommand.MESSAGE_NO_SUCH_MODULE_CODE);
    }

    @Test
    public void equals() {
        DeleteModuleLessonCommand deleteFirstCommand = new DeleteModuleLessonCommand(INDEX_FIRST);
        DeleteModuleLessonCommand deleteSecondCommand = new DeleteModuleLessonCommand(INDEX_SECOND);
        DeleteModuleLessonCommand deleteBatchCommand1 = new DeleteModuleLessonCommand(INDEX_FIRST, INDEX_SECOND);
        DeleteModuleLessonCommand deleteBatchCommand2 = new DeleteModuleLessonCommand(INDEX_FIRST, INDEX_THIRD);

        ModuleCodeContainsKeywordsPredicate firstPredicate =
                new ModuleCodeContainsKeywordsPredicate(Collections.singletonList(VALID_MODULE_CODE_CS2106));
        ModuleCodeContainsKeywordsPredicate secondPredicate =
                new ModuleCodeContainsKeywordsPredicate(Collections.singletonList(VALID_MODULE_CODE_CS2040));

        DeleteModuleLessonCommand deleteModuleCommand1 = new DeleteModuleLessonCommand(firstPredicate);
        DeleteModuleLessonCommand deleteModuleCommand2 = new DeleteModuleLessonCommand(secondPredicate);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));
        assertTrue(deleteBatchCommand1.equals(deleteBatchCommand1));
        assertTrue(deleteModuleCommand1.equals(deleteModuleCommand1));

        // same values -> returns true
        DeleteModuleLessonCommand deleteFirstCommandCopy = new DeleteModuleLessonCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        DeleteModuleLessonCommand deleteBatchCommandCopy = new DeleteModuleLessonCommand(INDEX_FIRST, INDEX_SECOND);
        assertTrue(deleteBatchCommand1.equals(deleteBatchCommandCopy));

        DeleteModuleLessonCommand deleteModuleCommandCopy = new DeleteModuleLessonCommand(firstPredicate);
        assertTrue(deleteModuleCommand1.equals(deleteModuleCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));

        assertFalse(deleteBatchCommand1.equals(deleteBatchCommand2));

        assertFalse(deleteModuleCommand1.equals(deleteModuleCommand2));
    }
}
