package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.chart.PieChart;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.cache.UserCommandCache;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskListManager;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> onlyFilteredPersons;
    private final SortedList<Person> filteredPersons;

    /** ObservableList of {@code Person}s used in viewing all task list. */
    private ObservableList<Person> viewAllTaskListPersons;

    private final UserCommandCache userCommandCache;

    private final TaskListManager taskListManager;

    private Predicate<Task> viewAllTasksFindPred = s -> true;

    /** Whether the task list panel is displaying every task list. */
    private boolean isViewAllTasks;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.taskListManager = new TaskListManager();
        taskListManager.initialiseArchive(this.getAddressBook().getPersonList());
        this.userCommandCache = UserCommandCache.getInstance();
        this.isViewAllTasks = false;

        onlyFilteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredPersons = new SortedList<>(onlyFilteredPersons);
        viewAllTaskListPersons = FXCollections.observableArrayList(addressBook.getPersonList());
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
        taskListManager.deleteEntry(target.getName());
        taskListManager.updateStatistics();
        updateViewAllTaskListPersons();
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        taskListManager.createNewEntry(person);
        taskListManager.updateStatistics();
        updateViewAllTaskListPersons();
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
        taskListManager.updateEntry(target, editedPerson);
        taskListManager.updateStatistics();
        updateViewAllTaskListPersons();
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

        onlyFilteredPersons.setPredicate(predicate);
    }

    @Override
    public ObservableList<Person> getViewAllTaskListPersons() {
        return viewAllTaskListPersons;
    }

    @Override
    public void setViewAllTasksFindPred(Predicate<Task> predicate) {
        this.viewAllTasksFindPred = predicate;
        updateViewAllTaskListPersons();
    }

    @Override
    public void updateViewAllTaskListPersons() {
        List<Person> personList = new ArrayList<>();
        for (Person person : addressBook.getPersonList()) {
            Person personClone = person.makeClone();
            personClone.getModifiableTasks().clear();
            personClone.getModifiableTasks().addAll(person.filterTasks(viewAllTasksFindPred));
            personList.add(personClone);
        }

        viewAllTaskListPersons = FXCollections.observableArrayList(personList);
        //viewAllTaskListPersons = viewAllTaskListPersons.filtered(person -> !person.getTasks().isEmpty());
    }

    @Override
    public void updateSortedPersonList(boolean isReverseOrder) {
        if (isReverseOrder) {
            filteredPersons.setComparator(Comparator.reverseOrder());
        } else {
            filteredPersons.setComparator(Comparator.naturalOrder());
        }
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
                && taskListManager.equals(other.taskListManager);
    }

    //=========== display task List Accessors =============================================================

    @Override
    public TaskListManager getTaskListManager() {
        return taskListManager;
    }

    @Override
    public ObservableList<Task> getDisplayTaskList() {
        return taskListManager.getFilteredTasks();
    }

    @Override
    public void displayPersonTaskList(Person person) {
        taskListManager.setToDisplayTaskList(person.getName(), false);
    }

    @Override
    public void displayFilteredPersonTaskList(Person person, Predicate<Task> predicate) {
        taskListManager.setFilteredTasksPredicate(predicate);
        taskListManager.setToDisplayTaskList(person.getName(), true);
    }

    @Override
    public void displayFilteredTaskList(Predicate<Task> predicate) {
        taskListManager.setFilteredTasksPredicate(predicate);
        taskListManager.setNameOfChosenPerson(null);
        taskListManager.setIsPersonSelected(false);
        viewAllTaskListPersons = FXCollections.emptyObservableList();
        setIsViewAllTasks(false);
    }

    @Override
    public boolean getIsViewAllTasks() {
        return isViewAllTasks;
    }

    @Override
    public void setIsViewAllTasks(boolean isViewAllTasks) {
        this.isViewAllTasks = isViewAllTasks;
    }

    //=========== cache operation =============================================================
    /** Get the next input command in the cache */
    public String getAfter() {
        return userCommandCache.getAfter();
    }

    /** Get the previous input command in the cache */
    public String getBefore() {
        return userCommandCache.getBefore();
    }

    /** Add a command to the cache */
    public void addCommand(String command) {
        userCommandCache.addCommand(command);
    }

    //=========== statistics Assessors =====================================================================

    @Override
    public ObservableList<PieChart.Data> getStatistics() {
        return taskListManager.getStatList();
    }
}
