package seedu.address.logic.commands.modulelesson;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.modulelesson.ModuleLesson;
import seedu.address.testutil.TypicalModuleLessons;

public class DeleteModuleLessonCommandTest {

    private Model model = new ModelManager(TypicalModuleLessons.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        //Deletes 1 module lesson
        ModuleLesson lessonToDelete = model.getFilteredModuleLessonList().get(INDEX_FIRST.getZeroBased());
        DeleteModuleLessonCommand deleteModuleLessonCommand = new DeleteModuleLessonCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteModuleLessonCommand.MESSAGE_DELETE_LESSON_SUCCESS, lessonToDelete);
        expectedMessage = String.format(DeleteModuleLessonCommand.MESSAGE_NUMBER_DELETED_LESSONS, 1) + expectedMessage;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.deleteLesson(lessonToDelete);

        assertCommandSuccess(deleteModuleLessonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validRangeUnfilteredList_success() {
        //Deletes 2 persons
       /* Person personToDelete1 = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        Person personToDelete2 = model.getFilteredPersonList().get(INDEX_SECOND.getZeroBased());
        DeletePersonCommand deletePersonCommand1 = new DeletePersonCommand(INDEX_FIRST, INDEX_SECOND);

        String expectedMessage1 = String.format(DeletePersonCommand.MESSAGE_NUMBER_DELETED_PERSON, 2)
                + String.format(DeletePersonCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete1)
                + String.format(DeletePersonCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete2);

        ModelManager expectedModel1 = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel1.deletePerson(personToDelete1);
        expectedModel1.deletePerson(personToDelete2);

        assertCommandSuccess(deletePersonCommand1, model, expectedMessage1, expectedModel1);*/
    }
}
