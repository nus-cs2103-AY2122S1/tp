package seedu.address.model;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.module.event.Event;
import seedu.address.model.module.member.Member;
import seedu.address.model.module.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Member> PREDICATE_SHOW_ALL_MEMBERS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Event> PREDICATE_SHOW_ALL_EVENTS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

    /** {@code Predicate} that evaluate if task is done */
    Predicate<Task> PREDICATE_SHOW_ALL_COMPLETED_TASKS = Task::isDone;

    /** {@code Predicate} that evaluate if task is not done */
    Predicate<Task> PREDICATE_SHOW_ALL_DUE_TASKS = task -> !task.isDone();

    /** {@code Predicate} that evaluate if task is overdue */
    Predicate<Task> PREDICATE_SHOW_ALL_OVERDUE_TASKS = task -> !task.isDone()
            && task.getTaskDeadlineDate().isBefore(LocalDateTime.now());

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
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /** Returns the user prefs' address book file path. */
    Path getAddressBookFilePath();

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns true if a member with the same identity as {@code member} exists in the address book.
     */
    boolean hasMember(Member member);

    /**
     * Returns true if an event with the same identity as {@code event} exists in the address book.
     */
    boolean hasEvent(Event event);


    /**
     * Deletes the given member.
     * The member must exist in the address book.
     */
    void deleteMember(Member target);

    /**
     * Deletes the given event.
     * The event must exist in the address book.
     */
    void deleteEvent(Event target);

    /**
     * Adds the given member.
     * {@code member} must not already exist in the address book.
     */
    void addMember(Member member);

    /**
     * Adds the given event.
     * {@code event} must not already exist in the address book.
     */
    void addEvent(Event event);

    /**
     * Adds members to the given event.
     * {@code event} must already exist in the address book.
     */
    void addEventMembers(Event event, Set<Member> memberSet);

    /**
     * Replaces the given member {@code target} with {@code editedMember}.
     * {@code target} must exist in the address book.
     * The member identity of {@code editedMember} must not be the same as another existing member in the address book.
     */
    void setMember(Member target, Member editedMember);

    /**
     * Replaces the given event {@code target} with {@code editedEvent}.
     * {@code target} must exist in the address book.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the address book.
     */
    void setEvent(Event target, Event editedEvent);

    /** Returns an unmodifiable view of the filtered member list */
    ObservableList<Member> getFilteredMemberList();

    /** Returns an unmodifiable view of the filtered event list */
    ObservableList<Event> getFilteredEventList();

    /**
     * Updates the filter of the filtered member list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMemberList(Predicate<Member> predicate);

    /**
     * Updates the filter of the filtered event list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<Event> predicate);

    /**
     * Load the given {@code Member} object's task list.
     */
    void loadTaskList(Member member);

    /**
     * Returns ture if a given {@code task} with the same identity as task
     * exist in the task list of the given {@code member}.
     */
    boolean hasTask(Member member, Task task);

    /**
     * Adds the given {@code task} to the given {@code member}'s task list.
     */
    void addTask(Member member, Task task);

    /**
     * Deletes the given {@code task} from the given {@code member}'s task list.
     * The task must exist in the member's task list.
     */
    void deleteTask(Task task);

    /**
     * Replaces the given task {@code target} with {@code editedTask} in the given {@code member}'s task list.
     * {@code target} must exist in the task list.
     */
    void setTask(Task target, Task editedTask);

    /**
     * Returns an unmodifiable view of the filtered task list of the given {@code member}.
     */
    ObservableList<Task> getFilteredTaskList(Member member);

    /**
     * Returns an unmodifiable view of the filtered task list of the previously selected member.
     * If no member has been selected before, return an empty list.
     */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Updates the filter of the filtered task list of the given {@code member}
     * to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Member member, Predicate<Task> predicate);

    /**
     * Updates the filter of the filtered task list of the current selected member
     * to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    /**
     * Returns the currently selected member.
     */
    Optional<Member> getCurrentMember();

    /**
     * Sets the currently selected event.
     */
    void setCurrentEvent(Event currentEvent);

    /**
     * Returns the currently selected event.
     */
    Optional<Event> getCurrentEvent();
}
