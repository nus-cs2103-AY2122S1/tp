package seedu.address.testutil;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.ViewingType;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupWithDetails;
import seedu.address.model.id.UniqueIdMapper;
import seedu.address.model.lesson.LessonWithAttendees;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonWithDetails;
import seedu.address.model.task.Task;

/**
 * A default model stub that have all the methods failing.
 */
public class ModelStub implements Model {
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
    public void setAddressBook(ReadOnlyAddressBook newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPerson(Person person) {
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

    @Override
    public void addTask(Task task) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasTask(Task task) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteTask(Task target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addGroup(Group group) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasGroup(Group group) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteGroup(Group target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGroup(Group target, Group editedGroup) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Group> getFilteredGroupList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredGroupList(Predicate<Group> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<LessonWithAttendees> getSortedLessonsWithAttendees() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateLessonWithAttendeesList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableValue<ViewingType> getViewingType() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setViewingType(ViewingType type) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableValue<PersonWithDetails> getViewingPersonWithDetails() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableValue<GroupWithDetails> getViewingGroupWithDetails() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPersonToView(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGroupToView(Group group) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public UniqueIdMapper<Person> getPersonMapper() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public UniqueIdMapper<Group> getGroupMapper() {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * A Model stub that contains a single person.
     */
    public static class ModelStubWithPerson extends ModelStub {
        private final Person person;

        /**
         * Constructs a {@code ModelStubWithPerson} with the specified person
         * @param person person to add to this model stub
         */
        public ModelStubWithPerson(Person person) {
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
    public static class ModelStubAcceptingPersonAdded extends ModelStub {
        public final ArrayList<Person> personsAdded = new ArrayList<>();
        private Person toView = null;
        private ViewingType viewingType = null;

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
        public void setPersonToView(Person person) {
            toView = person;
        }

        @Override
        public void setViewingType(ViewingType type) {
            viewingType = type;
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
