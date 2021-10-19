package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.data.event.Event;
import seedu.address.model.data.member.Member;
import seedu.address.model.task.Task;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Member> filteredMembers;
    private final FilteredList<Event> filteredEvents;
    private final TaskListManager taskListManager;
    private final FilteredList<Task> filteredTasks;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredMembers = new FilteredList<>(this.addressBook.getMemberList());
        filteredEvents = new FilteredList<>(this.addressBook.getEventList());
        this.taskListManager = new TaskListManager();
        filteredTasks = new FilteredList<>(this.taskListManager.getObservableTaskList());
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
    public boolean hasMember(Member member) {
        requireNonNull(member);
        return addressBook.hasMember(member);
    }

    @Override
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return addressBook.hasEvent(event);
    }

    @Override
    public void deleteMember(Member target) {
        addressBook.removeMember(target);
    }

    @Override
    public void deleteEvent(Event target) {
        addressBook.removeEvent(target);
    }

    @Override
    public void addMember(Member member) {
        addressBook.addMember(member);
        updateFilteredMemberList(PREDICATE_SHOW_ALL_MEMBERS);
    }

    @Override
    public void addEvent(Event event) {
        addressBook.addEvent(event);
        updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
    }

    @Override
    public void addEventMembers(Event event, Set<Member> memberSet) {
        addressBook.addEventMembers(event, memberSet);
        updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
    }

    @Override
    public void setMember(Member target, Member editedMember) {
        requireAllNonNull(target, editedMember);

        addressBook.setMember(target, editedMember);
    }

    @Override
    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);

        addressBook.setEvent(target, editedEvent);
    }

    //=========== Filtered Member and Event Lists Accessors ===========================================================

    /**
     * Returns an unmodifiable view of the list of {@code Member} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Member> getFilteredMemberList() {
        return filteredMembers;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Event} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Event> getFilteredEventList() {
        return filteredEvents;
    }

    @Override
    public void updateFilteredMemberList(Predicate<Member> predicate) {
        requireNonNull(predicate);
        filteredMembers.setPredicate(predicate);
    }

    //=========== TaskListManager ============================================================================

    /**
     * Load the given {@code Member} object.
     */
    @Override
    public void loadTaskList(Member member) {
        requireNonNull(member);
        taskListManager.loadTaskList(member.getTaskList());
    }

    /**
     * Returns true if a given {@code task} with the same identity as task
     * exist in the task list of the given {@code member}.
     */
    @Override
    public boolean hasTask(Member member, Task task) {
        loadTaskList(member);
        return taskListManager.hasTask(task);
    }

    /**
     * Adds the given {@code task} to the given {@code member}'s task list.
     * The tasks must not exist in the member's task list.
     */
    @Override
    public void addTask(Member member, Task task) {
        loadTaskList(member);
        taskListManager.addTask(task);
    }

    /**
     * Deletes the given {@code task} from the given {@code member}'s task list.
     * The task must exist in the member's task list.
     */
    @Override
    public void deleteTask(Member member, Task task) {
        loadTaskList(member);
        taskListManager.removeTask(task);
    }

    /**
     * Deletes the task specified by {@code index} from the given {@code member}'s task list.
     * The task must exist in the member's task list.
     */
    @Override
    public void deleteTask(Member member, int index) {
        loadTaskList(member);
        taskListManager.removeTask(index);
    }

    /**
     * Replaces the given task {@code target} with {@code editedTask} in the given {@code member}'s task list.
     * {@code target} must exist in the task list.
     * The member identity of {@code editedTask} must not be the same as another existing task in the task list.
     */
    @Override
    public void setTask(Member member, Task target, Task editedTask) {
        loadTaskList(member);
        taskListManager.setTask(target, editedTask);
    }

    /**
     * Replaces the task specified by {@code index} with {@code editedTask} in the given {@code member}'s task list.
     */
    @Override
    public void setTask(Member member, int index, Task editedTask) {
        loadTaskList(member);
        taskListManager.setTask(index, editedTask);
    }

    /**
     * Returns an unmodifiable view of the filtered task list of the given {@code member}.
     */
    @Override
    public ObservableList<Task> getFilteredTaskList(Member member) {
        loadTaskList(member);
        return taskListManager.getObservableTaskList();
    }

    /**
     * Updates the filter of the filtered task list of the given {@code member}
     * to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    @Override
    public void updateFilteredTaskList(Member member, Predicate<Task> predicate) {
        loadTaskList(member);
        filteredTasks.setPredicate(predicate);
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        requireNonNull(predicate);
        filteredEvents.setPredicate(predicate);
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
                && filteredMembers.equals(other.filteredMembers)
                && filteredEvents.equals(other.filteredEvents);
    }

}
