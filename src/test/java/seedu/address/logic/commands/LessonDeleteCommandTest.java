package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LESSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.PersonBuilder;

public class LessonDeleteCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LessonDeleteCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void execute_oneExistingLesson_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withSampleLesson().build();
        LessonDeleteCommand lessonDeleteCommand = new LessonDeleteCommand(INDEX_FIRST_PERSON, INDEX_FIRST_LESSON);
        ModelStub modelStub = new ModelStubWithPerson(editedPerson);

        Lesson lessonToDelete = editedPerson.getLessons().stream()
            .collect(Collectors.toList())
            .get(INDEX_FIRST_LESSON.getZeroBased());
        String expectedMessage = String.format(
            LessonDeleteCommand.MESSAGE_DELETE_LESSON_SUCCESS, lessonToDelete, editedPerson);
        Model expectedModel = new ModelStubWithPerson(firstPerson);

        assertCommandSuccess(lessonDeleteCommand, modelStub, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        LessonDeleteCommand deleteSampleLessonCommand = new LessonDeleteCommand(INDEX_FIRST_PERSON,
            INDEX_FIRST_LESSON);
        LessonDeleteCommand deleteSampleLessonCommand2 = new LessonDeleteCommand(INDEX_SECOND_PERSON,
            INDEX_FIRST_LESSON);

        // same object -> returns true
        assertTrue(deleteSampleLessonCommand.equals(deleteSampleLessonCommand));

        // same values -> returns true
        LessonDeleteCommand deleteSampleLessonCommandCopy = new LessonDeleteCommand(INDEX_FIRST_PERSON,
            INDEX_FIRST_LESSON);
        assertTrue(deleteSampleLessonCommand.equals(deleteSampleLessonCommandCopy));

        // different types -> returns false
        assertFalse(deleteSampleLessonCommand.equals(1));

        // null -> returns false
        assertFalse(deleteSampleLessonCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteSampleLessonCommand.equals(deleteSampleLessonCommand2));
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private Person person;

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
        public void setPerson(Person target, Person editedPerson) {
            this.person = editedPerson;
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            return;
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            UniquePersonList list = new UniquePersonList();
            list.add(this.person);
            return list.asUnmodifiableObservableList();
        }

        @Override
        public boolean equals(Object obj) {
            // short circuit if same object
            if (obj == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(obj instanceof ModelStubWithPerson)) {
                return false;
            }

            // state check
            return person.equals(((ModelStubWithPerson) obj).person);
        }

    }
}
