package dash.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import dash.commons.core.GuiSettings;
import dash.model.person.Person;
import dash.model.task.Task;
import dash.model.task.TaskList;
import javafx.collections.ObservableList;


/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

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

    /**
     * Returns the AddressBook
     */
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

    /**
     * Returns the user prefs' task list file path.
     */
    Path getTaskListFilePath();

    /**
     * Sets the user prefs' task list file path.
     */
    void setTaskListFilePath(Path taskListPath);

    /**
     * Replaces task list data with the data in {@code taskList}.
     */
    void setTaskList(TaskList taskList);

    /**
     * Returns the TaskList
     */
    TaskList getTaskList();

    /**
     * Returns true if a task with the same identity as {@code task} exists in the address book.
     */
    boolean hasTask(Task task);

    /**
     * Deletes the given task.
     * The task must exist in the task list.
     */
    void deleteTask(Task target);

    /**
     * Adds the given task.
     */
    void addTask(Task task);

    /**
     * Replaces the given task at index {@code index} with {@code editedTask}.
     */
    void setTask(int index, Task editedTask);


    /**
     * Clears the task list of completed tasks.
     */
    void deleteDoneTasks();

    /**
     * Returns the UserInputList
     */
    UserInputList getUserInputList();

    /**
     * Returns index of task to edit.
     */
    int getIndexToEdit(int userIndexZeroBase, Task taskToEdit, List<Task> filteredList);

    /**
     * Finds all tasks that contain personToBeReplaced, and replaces that person with newPerson.
     */
    void replacePeopleInTasks(Person personToReplace, Person newPerson);

    /**
     * Deletes personToBeDeleted from all tasks.
     */
    void deletePeopleFromTasks(Person personToDelete);

    /**
     * Adds the given user input string to the start of the {@code UserInputList}
     */
    void addUserInput(String userInput);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns an unmodifiable view of the filtered task list
     */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    /**
     * Sorts task list in chronological order.
     */
    void sortTaskList();

    /**
     * Returns an unmodifiable view of the user input list
     * @return
     */
    ArrayList<String> getInternalUserInputList();
}
