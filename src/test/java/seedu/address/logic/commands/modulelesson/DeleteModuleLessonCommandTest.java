package seedu.address.logic.commands.modulelesson;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalModuleLessons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.modulelesson.ModuleLesson;

public class DeleteModuleLessonCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

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
}
