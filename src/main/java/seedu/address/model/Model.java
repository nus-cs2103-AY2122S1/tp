package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.member.Member;
import seedu.address.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Member> PREDICATE_SHOW_ALL_MEMBERS = unused -> true;

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

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
     * Deletes the given member.
     * The member must exist in the address book.
     */
    void deleteMember(Member target);

    /**
     * Adds the given member.
     * {@code member} must not already exist in the address book.
     */
    void addMember(Member member);

    /**
     * Replaces the given member {@code target} with {@code editedMember}.
     * {@code target} must exist in the address book.
     * The member identity of {@code editedMember} must not be the same as another existing member in the address book.
     */
    void setMember(Member target, Member editedMember);

    /**
     * Returns an unmodifiable view of the filtered member list
     */
    ObservableList<Member> getFilteredMemberList();

    /**
     * Updates the filter of the filtered member list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMemberList(Predicate<Member> predicate);

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
     * The tasks must not exist in the member's task list.
     */
    void addTask(Member member, Task task);

    /**
     * Deletes the given {@code task} from the given {@code member}'s task list.
     * The task must exist in the member's task list.
     */
    void deleteTask(Member member, Task task);

    /**
     * Replaces the given task {@code target} with {@code editedTask} in the given {@code member}'s task list.
     * {@code target} must exist in the task list.
     * The member identity of {@code editedTask} must not be the same as another existing task in the task list.
     */
    void setTask(Member member, Task target, Task editedTask);

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
