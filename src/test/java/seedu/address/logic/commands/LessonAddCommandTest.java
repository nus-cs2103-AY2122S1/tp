package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.testutil.LessonBuilder;
import seedu.address.testutil.ModelStub;

/**
 * Contains integration tests (interaction with the Model) and unit tests for LessonAddCommand.
 */
public class LessonAddCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LessonAddCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void equals() {
        Lesson sampleLesson = new LessonBuilder().build();
        LessonAddCommand addSampleLessonCommand = new LessonAddCommand(INDEX_FIRST_PERSON,
                sampleLesson);
        LessonAddCommand addSampleLessonCommand2 = new LessonAddCommand(INDEX_SECOND_PERSON,
                sampleLesson);

        // same object -> returns true
        assertTrue(addSampleLessonCommand.equals(addSampleLessonCommand));

        // same values -> returns true
        LessonAddCommand addSampleLessonCommandCopy = new LessonAddCommand(INDEX_FIRST_PERSON,
                sampleLesson);
        assertTrue(addSampleLessonCommand.equals(addSampleLessonCommandCopy));

        // different types -> returns false
        assertFalse(addSampleLessonCommand.equals(1));

        // null -> returns false
        assertFalse(addSampleLessonCommand.equals(null));

        // different person -> returns false
        assertFalse(addSampleLessonCommand.equals(addSampleLessonCommand2));
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            UniquePersonList list = new UniquePersonList();
            list.add(this.person);
            return list.asUnmodifiableObservableList();
        }

    }

}
