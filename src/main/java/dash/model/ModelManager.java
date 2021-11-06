package dash.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import dash.commons.core.GuiSettings;
import dash.commons.core.LogsCenter;
import dash.commons.util.CollectionUtil;
import dash.model.person.Person;
import dash.model.task.Task;
import dash.model.task.TaskList;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final TaskList taskList;
    private final UserPrefs userPrefs;
    private final UserInputList userInputList;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Task> filteredTasks;

    /**
     * Initializes a ModelManager with the given addressBook, userPrefs and taskList.
     */
    public ModelManager(ReadOnlyAddressBook addressBook,
                        ReadOnlyUserPrefs userPrefs, TaskList taskList, UserInputList userInputList) {
        super();
        CollectionUtil.requireAllNonNull(addressBook, userPrefs, taskList);

        logger.fine("Initializing with address book: " + addressBook + ", user prefs " + userPrefs
                + " and task list" + taskList);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.taskList = new TaskList(taskList);
        this.userInputList = new UserInputList(userInputList);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredTasks = new FilteredList<>(this.taskList.getObservableTaskList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new TaskList(), new UserInputList());
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

    @Override
    public Path getTaskListFilePath() {
        return userPrefs.getTaskListFilePath();
    }

    @Override
    public void setTaskListFilePath(Path taskListFilePath) {
        requireNonNull(taskListFilePath);
        userPrefs.setTaskListFilePath(taskListFilePath);
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
        CollectionUtil.requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    //=========== TaskList ===================================================================================

    @Override
    public void setTaskList(TaskList taskList) {
        this.taskList.resetData(taskList);
    }

    @Override
    public TaskList getTaskList() {
        return taskList;
    }

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return taskList.contains(task);
    }

    @Override
    public void deleteTask(Task target) {
        taskList.remove(target);
    }

    @Override
    public void addTask(Task task) {
        taskList.add(task);
    }

    @Override
    public void setTask(int index, Task editedTask) {
        CollectionUtil.requireAllNonNull(editedTask);
        taskList.setTask(index, editedTask);
    }

    @Override
    public void deleteDoneTasks() {
        taskList.deleteDoneTasks();
    }

    @Override
    public int getIndexToEdit(int userIndexZeroBase, Task taskToEdit, List<Task> filteredList) {
        return taskList.getIndexToEdit(userIndexZeroBase, taskToEdit, filteredList);
    }

    @Override
    public void replacePeopleInTasks(Person personToBeReplaced, Person newPerson) {
        taskList.replacePeople(personToBeReplaced, newPerson);
    }

    @Override
    public void deletePeopleFromTasks(Person personToBeDeleted) {
        taskList.deletePeople(personToBeDeleted);
    }

    //=========== UserInputList ==============================================================================

    @Override
    public void addUserInput(String userInput) {
        userInputList.add(userInput);
    }

    @Override
    public UserInputList getUserInputList() {
        return userInputList;
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
                && taskList.equals(other.taskList)
                && filteredTasks.equals(other.filteredTasks);
    }

    //=========== Filtered Task List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code versionedTaskList}
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

    @Override
    public void sortTaskList() {
        taskList.sortTasks();
    }

    //=========== Observable User Input List Accessor =================================================================

    @Override
    public ArrayList<String> getInternalUserInputList() {
        return userInputList.getInternalUserInputList();
    }

}
