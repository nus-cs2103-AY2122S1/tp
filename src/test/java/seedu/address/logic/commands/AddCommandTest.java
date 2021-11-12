package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_STUDENT;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Entry;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.LastUpdatedDate;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = prepareAddCommandForPerson(validPerson, modelStub).execute();

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, MESSAGE_DUPLICATE_STUDENT, () ->
                prepareAddCommandForPerson(validPerson, modelStub).execute());
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * Generates an Add Command with respective dependencies set.
     * UndoRedoStack not tested under Add Command thus UndoRedoStack is created within the method.
     *
     * @param person Person to be added to AddressBook.
     * @param model Model which the command is executing on.
     * @return AddCommand with the dependencies and person to be added.
     */
    private AddCommand prepareAddCommandForPerson(Person person, Model model) {
        AddCommand addCommandForPerson = new AddCommand(person);
        addCommandForPerson.setDependencies(model, new UndoRedoStack());
        return addCommandForPerson;
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        private static final String MESSAGE_UNEXPECTED_METHOD_CALL = "This method should not be called.";

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
        }

        @Override
        public ObservableList<Person> getUnfilteredPersonList() {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
        }

        @Override
        public void addPersonAtIndex(Person person, Index index) {
            throw new AssertionError((MESSAGE_UNEXPECTED_METHOD_CALL));
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
        }

        @Override
        public boolean hasClashingLesson(Lesson lesson) {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
        }

        @Override
        public boolean hasClashingLesson(Lesson lesson, Lesson lessonToIgnore) {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
        }

        @Override
        public Set<String> getClashingLessonsString(Lesson lesson) {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
        }

        @Override
        public Set<String> getClashingLessonsString(Lesson lesson, Lesson lessonToIgnore) {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
        }

        @Override
        public Calendar getCalendar() {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
        }

        @Override
        public ObservableList<Entry<Lesson>> getUpcomingLessons() {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
        }

        @Override
        public void updateUpcomingLessons() {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
        }

        @Override
        public boolean hasPersonFilteredList(Person person) {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
        }

        @Override
        public LastUpdatedDate getLastUpdatedDate() {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
        }

        @Override
        public void setLastUpdatedDate() {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
        }

        @Override
        public ObservableList<Tag> getObservableTagList() {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
        }

        @Override
        public ObservableMap<Tag, Integer> getTagCounter() {
            throw new AssertionError(MESSAGE_UNEXPECTED_METHOD_CALL);
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
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
