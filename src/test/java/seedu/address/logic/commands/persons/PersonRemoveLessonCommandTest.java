package seedu.address.logic.commands.persons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalLessons.MON_10_12_BIOLOGY;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lesson.NoOverlapLessonList;
import seedu.address.model.person.Person;

class PersonRemoveLessonCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullParams_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PersonRemoveLessonCommand(null, INDEX_FIRST_PERSON));
        assertThrows(NullPointerException.class, () -> new PersonRemoveLessonCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void execute_validInput_success() throws Exception {
        PersonRemoveLessonCommand command = new PersonRemoveLessonCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON);
        Model newModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Person toEdit = newModel.getFilteredPersonList().get(0);
        Person updatedPerson = toEdit.updateLessonsList(new NoOverlapLessonList().addLesson(MON_10_12_BIOLOGY));
        newModel.setPerson(toEdit, updatedPerson);
        assertCommandSuccess(command, newModel,
                String.format(PersonRemoveLessonCommand.MESSAGE_SUCCESS, toEdit), model);
    }

    @Test
    public void execute_invalidPersonIndex_throwsCommandException() {
        PersonRemoveLessonCommand command = new PersonRemoveLessonCommand(Index.fromZeroBased(10000),
                INDEX_FIRST_PERSON);
        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidLesson_success() {
        PersonRemoveLessonCommand command = new PersonRemoveLessonCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON);
        assertCommandFailure(command, model, PersonRemoveLessonCommand.INVALID_LESSON_INDEX);
    }

    @Test
    void testEquals() {
        assertNotEquals(new PersonRemoveLessonCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON),
                new PersonRemoveLessonCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON));

        assertEquals(new PersonRemoveLessonCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON),
                new PersonRemoveLessonCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON));
    }
}
