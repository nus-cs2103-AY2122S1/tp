package seedu.address.model;

import java.nio.file.Path;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.data.event.Event;
import seedu.address.model.data.member.Member;
import seedu.address.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Member> PREDICATE_SHOW_ALL_MEMBERS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Event> PREDICATE_SHOW_ALL_EVENTS = unused -> true;

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
    void hasTask(Member member, Task task);

    /**
     * Adds the given {@code task} to the given {@code member}'s task list.
     */
    void addTask(Member member, Task task);

    /**
     * Deletes the given {@code task} from the given {@code member}'s task list.
     * The task must exist in the member's task list.
     */
    void deleteTask(Member member, Task task);

    /**
     * Deletes the task specified by {@code index} from the given {@code member}'s task list.
     * The task must exist in the member's task list.
     */
    void deleteTask(Member member, int index);

    /**
     * Replaces the given task {@code target} with {@code editedTask} in the given {@code member}'s task list.
     * {@code target} must exist in the task list.
     */
    void setTask(Member member, Task target, Task editedTask);

    /**
     * Replaces the task specified by {@code index} with {@code editedTask} in the given {@code member}'s task list.
     */
    void setTask(Member member, int index, Task editedTask);

    /**
     * Returns an unmodifiable view of the filtered task list of the given {@code member}.
     */
    ObservableList<Task> getFilteredTaskList(Member member);

    /**
     * Updates the filter of the filtered task list of the given {@code member}
     * to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Member member, Predicate<Task> predicate);
}
