package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupWithDetails;
import seedu.address.model.id.UniqueIdMapper;
import seedu.address.model.lesson.LessonWithAttendees;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonWithDetails;
import seedu.address.model.task.Task;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<Group> filteredGroups;

    private final ObservableList<LessonWithAttendees> lessonWithAttendees;
    private final ObjectProperty<PersonWithDetails> personWithDetails;
    private final ObjectProperty<GroupWithDetails> groupWithDetails;
    private final ObjectProperty<ViewingType> viewType;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredTasks = new FilteredList<>(this.addressBook.getTaskList());
        filteredGroups = new FilteredList<>(this.addressBook.getGroupList());
        lessonWithAttendees = FXCollections.observableArrayList(this.addressBook.getSortedLessonsWithAttendees());
        viewType = new SimpleObjectProperty<>(ViewingType.SCHEDULE);
        personWithDetails = new SimpleObjectProperty<>(null);
        groupWithDetails = new SimpleObjectProperty<>(null);
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return addressBook.hasTask(task);
    }

    @Override
    public void deleteTask(Task target) {
        addressBook.removeTask(target);
    }

    @Override
    public void addTask(Task task) {
        addressBook.addTask(task);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        addressBook.setTask(target, editedTask);
    }

    @Override
    public boolean hasGroup(Group group) {
        requireNonNull(group);
        return addressBook.hasGroup(group);
    }

    @Override
    public void deleteGroup(Group target) {
        addressBook.removeGroup(target);
    }

    @Override
    public void addGroup(Group group) {
        addressBook.addGroup(group);
        updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
    }

    @Override
    public void setGroup(Group target, Group editedGroup) {
        requireAllNonNull(target, editedGroup);

        addressBook.setGroup(target, editedGroup);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Filtered Task List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    //=========== Filtered Group List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Group} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Group> getFilteredGroupList() {
        return filteredGroups;
    }

    @Override
    public void updateFilteredGroupList(Predicate<Group> predicate) {
        requireNonNull(predicate);
        filteredGroups.setPredicate(predicate);
    }

    //=========== mapper accessors =========================================================================

    @Override
    public UniqueIdMapper<Person> getPersonMapper() {
        return addressBook.getPersonMapper();
    }

    @Override
    public UniqueIdMapper<Group> getGroupMapper() {
        return addressBook.getGroupMapper();
    }

    @Override
    public ObservableList<LessonWithAttendees> getSortedLessonsWithAttendees() {
        return lessonWithAttendees;
    }

    @Override
    public void updateLessonWithAttendeesList() {
        lessonWithAttendees.setAll(addressBook.getSortedLessonsWithAttendees());
    }

    @Override
    public ObservableValue<ViewingType> getViewingType() {
        return viewType;
    }

    @Override
    public void setViewingType(ViewingType type) {
        viewType.set(type);
    }

    @Override
    public ObservableValue<PersonWithDetails> getViewingPersonWithDetails() {
        return personWithDetails;
    }

    @Override
    public ObservableValue<GroupWithDetails> getViewingGroupWithDetails() {
        return groupWithDetails;
    }

    @Override
    public void setPersonToView(Person person) {
        personWithDetails.set(addressBook.getPersonWithDetails(person));
    }

    @Override
    public void setGroupToView(Group group) {
        groupWithDetails.set(addressBook.getGroupWithDetails(group));
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredTasks.equals(other.filteredTasks);
    }

}
