package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.util.SampleDataUtil;

/**
 * Contains integration tests (interaction with the Model) and unit tests for LessonAddCommand.
 */
public class LessonAddCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> prepareLessonAddCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void equals() {
        LessonAddCommand addSampleLessonCommand = prepareLessonAddCommand(INDEX_FIRST_PERSON,
                SampleDataUtil.getSampleLesson());
        LessonAddCommand addSampleLessonCommand2 = prepareLessonAddCommand(INDEX_SECOND_PERSON,
                SampleDataUtil.getSampleLesson());

        // same object -> returns true
        assertTrue(addSampleLessonCommand.equals(addSampleLessonCommand));

        // same values -> returns true
        LessonAddCommand addSampleLessonCommandCopy = prepareLessonAddCommand(INDEX_FIRST_PERSON,
                SampleDataUtil.getSampleLesson());
        assertTrue(addSampleLessonCommand.equals(addSampleLessonCommandCopy));

        // different types -> returns false
        assertFalse(addSampleLessonCommand.equals(1));

        // null -> returns false
        assertFalse(addSampleLessonCommand.equals(null));

        // different person -> returns false
        assertFalse(addSampleLessonCommand.equals(addSampleLessonCommand2));
    }

    /**
     * Generates a {@code LessonAddCommand} with parameters {@code index} and {@code lesson}.
     */
    private LessonAddCommand prepareLessonAddCommand(Index index, Lesson lesson) {
        LessonAddCommand lessonAddCommand = new LessonAddCommand(index, lesson);
        lessonAddCommand.setDependencies(model, new UndoRedoStack());
        return lessonAddCommand;
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPersonAtIndex(Person person, Index index) { throw new AssertionError("This method should not be called."); }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClashingLesson(Lesson lesson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }
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
