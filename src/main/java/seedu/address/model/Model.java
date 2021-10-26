package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupWithDetails;
import seedu.address.model.id.UniqueIdMapper;
import seedu.address.model.lesson.LessonWithAttendees;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonWithDetails;
import seedu.address.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Group> PREDICATE_SHOW_ALL_GROUPS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns true if a task with the same identity as {@code task} exists in the address book.
     */
    boolean hasTask(Task task);

    /**
     * Deletes the given task.
     * The task must exist in the address book.
     */
    void deleteTask(Task target);

    /**
     * Adds the given task.
     * {@code task} must not already exist in the address book.
     */
    void addTask(Task task);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the address book.
     * The task identity of {@code editedTask} must not be the same as another existing task in the address book.
     */
    void setTask(Task target, Task editedTask);

    /** Returns an unmodifiable view of the filtered task list */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    /**
     * Returns true if a group with the same identity as {@code group} exists in the address book.
     */
    boolean hasGroup(Group group);

    /**
     * Deletes the given group.
     * The group must exist in the address book.
     */
    void deleteGroup(Group group);

    /**
     * Adds the given group.
     * {@code group} must not already exist in the address book.
     */
    void addGroup(Group group);

    /**
     * Replaces the given group {@code target} with {@code editedGroup}.
     * {@code target} must exist in the address book.
     * The group identity of {@code editedGroup} must not be the same as another existing group in the address book.
     */
    void setGroup(Group group, Group editedGroup);

    /** Returns an unmodifiable view of the filtered group list */
    ObservableList<Group> getFilteredGroupList();

    /**
     * Updates the filter of the filtered group list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredGroupList(Predicate<Group> predicate);

    /**
     * Gets the sorted lessons with its corresponding attendees
     * @return
     */
    ObservableList<LessonWithAttendees> getSortedLessonsWithAttendees();

    /**
     * Forces an update of the lesson with attendees list
     */
    void updateLessonWithAttendeesList();

    /**
     * Gets the current viewing type of the model.
     * Viewing type is used to determine what the user is viewing in the panel.
     * @return
     */
    ObservableValue<ViewingType> getViewingType();

    /**
     * Sets the viewing type of the model.
     * @param type to set to.
     */
    void setViewingType(ViewingType type);

    /**
     * Gets the current person to be viewed by the user.
     * @return PersonWithDetails of the person to be viewed.
     */
    ObservableValue<PersonWithDetails> getViewingPersonWithDetails();

    /**
     * Gets the current group to be viewed by the user.
     * @return GroupWithDetails of the group to be viewed.
     */
    ObservableValue<GroupWithDetails> getViewingGroupWithDetails();

    /**
     * Sets the current viewing of the Person.
     * It is the model's responsibility to figure out the person's details.
     *
     * @param person to set the PersonWithDetails
     */
    void setPersonToView(Person person);

    /**
     * Sets the current viewing of the Group.
     * It is the model's responsibility to figure out the groups's details.
     *
     * @param group to set the GroupWithDetails
     */
    void setGroupToView(Group group);

    /**
     * Returns a mapper that can convert unique Ids to persons
     * @return the UniqueIdMapper
     */
    UniqueIdMapper<Person> getPersonMapper();

    /**
     * Returns a mapper that can convert unique Ids to groups
     * @return the UniqueIdMapper
     */
    UniqueIdMapper<Group> getGroupMapper();
}
