package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Contact;
import seedu.address.model.task.Task;
import seedu.address.model.task.filters.TagTaskFilter;
import seedu.address.model.task.filters.TaskFilter;
import seedu.address.model.task.filters.TaskFilters;
import seedu.address.storage.CommandHistory;
import seedu.address.storage.InputHistory;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final TaskList taskList;
    private final UserPrefs userPrefs;
    private final CommandHistory commandHistory;
    private final InputHistory historyStorage;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Task> filteredTasks;
    private final ObservableList<TaskFilter> availableTaskFilters;
    private final FilteredList<TaskFilter> selectableTaskFilters;
    private final ObservableList<TaskFilter> selectedTaskFilters;

    /**
     * Initializes a ModelManager with the given addressBook, taskList and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyTaskList taskList, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, taskList, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.taskList = new TaskList(taskList);
        this.userPrefs = new UserPrefs(userPrefs);
        this.commandHistory = new CommandHistory(15);
        this.historyStorage = new InputHistory();
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredTasks = new FilteredList<>(this.taskList.getTasks());
        availableTaskFilters = FXCollections.observableArrayList();
        selectableTaskFilters = new FilteredList<>(availableTaskFilters,
            filter -> getSelectedTaskFilters().stream().noneMatch(filter::hasConflictWith));
        selectedTaskFilters = FXCollections.observableArrayList();
    }

    public ModelManager() {
        this(new AddressBook(), new TaskList(), new UserPrefs());
    }

    /**
     * Returns a new ModelManager initialize from the given {@link Model}.
     *
     * @param model The model to initialize the {@link ModelManager} from
     * @return The initialized ModelManager
     */
    public static ModelManager from(Model model) {
        return new ModelManager(
                new AddressBook(model.getAddressBook()), new TaskList(model.getTaskList()), new UserPrefs());
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
        updateAllTasksContacts();
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
        updateAllTasksContacts();
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateAllTasksContacts();
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);

        updateAllTasksContacts();
        if (!target.getName().equals(editedPerson.getName())) {
            changeAllTaskContactNames(target.getName(), editedPerson.getName());
        }
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
    public void updateFilteredPersonList(Predicate<? super Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public Predicate<? super Person> getFilteredPersonPredicate() {
        return Optional.ofNullable(filteredPersons.getPredicate()).orElse(unused -> true);
    }

    //=========== TaskMaster2103 ============================================================================

    @Override
    public ReadOnlyTaskList getTaskList() {
        return taskList;
    }

    @Override
    public void addTask(Task task) {
        Task updatedTask = updateTaskContacts(task);
        taskList.addTask(updatedTask);
        recomputeAvailableTaskFilters();
    }

    @Override
    public int indexOf(Task task) {
        return taskList.indexOf(task);
    }

    @Override
    public void insertTask(Task task, int index) {
        taskList.addTaskAtIndex(task, index);
        recomputeAvailableTaskFilters();
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    private void recomputeAvailableTaskFilters() {
        Set<Tag> allTaskTags = taskList.getTasks().stream()
                .map(Task::getTags)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
        List<TaskFilter> tagFilters = allTaskTags.stream()
                .map(TaskFilters.FILTER_TAG)
                .sorted(Comparator.comparing(TaskFilter::toString))
                .collect(Collectors.toList());

        availableTaskFilters.clear();
        availableTaskFilters.add(TaskFilters.FILTER_DONE);
        availableTaskFilters.add(TaskFilters.FILTER_UNDONE);
        availableTaskFilters.addAll(tagFilters);
    }

    private void refreshSelectableTaskFiltersPredicate() {
        selectableTaskFilters.setPredicate(
            filter -> getSelectedTaskFilters().stream().noneMatch(filter::hasConflictWith));
    }

    @Override
    public ObservableList<TaskFilter> getSelectableTaskFilters() {
        recomputeAvailableTaskFilters();
        return selectableTaskFilters;
    }

    @Override
    public ObservableList<TaskFilter> getSelectedTaskFilters() {
        return selectedTaskFilters;
    }

    @Override
    public void addTaskFilter(TaskFilter taskFilter) {
        selectedTaskFilters.add(taskFilter);
        recalculateFilteredTaskList();
        refreshSelectableTaskFiltersPredicate();
    }

    @Override
    public void removeTaskFilter(TaskFilter taskFilter) {
        selectedTaskFilters.remove(taskFilter);
        recalculateFilteredTaskList();
        refreshSelectableTaskFiltersPredicate();
    }

    @Override
    public void setTaskFilters(List<TaskFilter> taskFilters) {
        selectedTaskFilters.clear();
        selectedTaskFilters.addAll(taskFilters);
        recalculateFilteredTaskList();
        refreshSelectableTaskFiltersPredicate();
    }

    @Override
    public List<TaskFilter> getOldTaskFilters() {
        return this.selectedTaskFilters;
    }

    private void recalculateFilteredTaskList() {
        Predicate<Task> identity = task -> true;
        Predicate<Task> effectivePredicate = selectedTaskFilters.stream().map(TaskFilter::getPredicate)
                .reduce(identity, Predicate::and);
        updateFilteredTaskList(effectivePredicate);
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    @Override
    public Predicate<? super Task> getFilteredTaskPredicate() {
        return Optional.ofNullable(filteredTasks.getPredicate()).orElse(unused -> true);
    }

    @Override
    public void deleteTask(Task deletedTask) {
        taskList.removeTask(deletedTask);
        updateTaskFilters();
    }

    @Override
    public void deleteTaskAtLastIndex() {
        taskList.removeTaskAtLastIndex();
        updateTaskFilters();
    }

    /**
     * Deletes the filtered list and their filters from the task list.
     * This method does not {@code updateTaskFilters} so as to show distinct changes to the task list, if any.
     * Instead, it removes all currently selected task filters from {@code availableTaskFilters}
     */
    @Override
    public void deleteAllInFilteredTaskList() {
        List<Task> tasksToDelete = new ArrayList<>(filteredTasks);
        for (Task task : tasksToDelete) {
            taskList.removeTask(task);
        }

        availableTaskFilters.removeAll(selectedTaskFilters);
    }

    @Override
    public Task setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);
        Task updatedEditedTask = updateTaskContacts(editedTask);
        taskList.setTask(target, updatedEditedTask);
        updateTaskFilters();
        return updatedEditedTask;
    }

    /**
     * Update filters for the task list after any changes to it (CLI or GUI)
     */
    private void updateTaskFilters() {
        recomputeAvailableTaskFilters();

        // If removing or editing the task removed a tag, remove all filters associated with that tag
        if (selectedTaskFilters.removeIf(filter -> filter instanceof TagTaskFilter
                && !availableTaskFilters.contains(filter))) {
            recalculateFilteredTaskList();
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
                && taskList.equals(other.taskList)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

    /**
     * Checks and updates contacts for a given {@code Task}, to see if they appear in AddressBook.
     *
     * @param task Given task to check the AddressBook with.
     * @return A copy of the given Task with the updated AddressBook fields.
     */
    private Task updateTaskContacts(Task task) {
        ObservableList<Person> personList = getAddressBook().getPersonList();
        Set<Contact> updatedContacts = new HashSet<>();
        Set<Contact> currentContactList = task.getContacts();

        // Update contact if AB3 contains name
        for (Contact contact : currentContactList) {
            Name contactName = contact.getName();
            if (containsName(personList, contactName)) {
                updatedContacts.add(new Contact(contactName, true));
            } else {
                updatedContacts.add(new Contact(contactName, false));
            }
        }

        return new Task(task.getTitle(),
                task.getDescription().orElse(null),
                task.getTimestamp().orElse(null),
                task.getTags(),
                task.isDone(),
                updatedContacts);
    }

    /**
     * Determines if a name exists within a given a list of {@code Person}s.
     *
     * @param personList  List of Persons to check through
     * @param nameToCheck Name to check the list with
     * @return Boolean indicating whether the personList contains nameToCheck.
     */
    private boolean containsName(List<Person> personList, Name nameToCheck) {
        return personList.stream()
                .map(Person::getName)
                .anyMatch(name -> name.equals(nameToCheck));
    }

    /**
     * Checks and updates all task contacts, to see if they appear in AddressBook.
     */
    private void updateAllTasksContacts() {
        List<Task> tasks = taskList.getTasks();
        // For every task, check and update their contacts
        for (Task task : tasks) {
            Task updatedTask = updateTaskContacts(task);
            taskList.setTask(task, updatedTask);
        }
    }

    /**
     * Changes the {@code oldName} in the given {@code Task} to the new name.
     * Used when a {@code Person}'s {@code Name} is edited.
     *
     * @param task    Given task to update
     * @param oldName Old name to change
     * @param newName New name to change to
     * @return A new copy of the changed task. If there is no change, the task itself is returned.
     */
    private Task changeTaskContactName(Task task, Name oldName, Name newName) {
        Set<Contact> currentContactList = task.getContacts();
        Set<Contact> updatedContacts = new HashSet<>(currentContactList);

        if (updatedContacts.removeIf(contact -> contact.getName().equals(oldName))) {
            updatedContacts.add(new Contact(newName, true));

        }

        return updatedContacts.equals(currentContactList)
                ? task
                : new Task(task.getTitle(),
                task.getDescription().orElse(null),
                task.getTimestamp().orElse(null),
                task.getTags(),
                task.isDone(),
                updatedContacts);
    }

    /**
     * Changes all {@code oldName}s in all task's contacts to the new name.
     * Used when a {@code Person}'s {@code Name} is edited.
     *
     * @param oldName Old name to change
     * @param newName New name to change to
     */
    private void changeAllTaskContactNames(Name oldName, Name newName) {
        List<Task> tasks = taskList.getTasks();
        // For every task, check and update their contacts
        for (Task task : tasks) {
            Task updatedTask = changeTaskContactName(task, oldName, newName);
            taskList.setTask(task, updatedTask);
        }
    }


    //=========== Undo Stack ============================================================================

    public CommandHistory getCommandHistory() {
        return commandHistory;
    }

    //========== Command History Stack ==================================================================


    @Override
    public String getHistoryCommand(boolean isNext, String currentString) {
        return this.historyStorage.getHistoryString(isNext, currentString).orElse("");
    }

    @Override
    public void addCommandToHistory(String command) {
        this.historyStorage.pushCommand(command);
    }

    @Override
    public void resetHistoryPosition() {
        this.historyStorage.resetHistoryPosition();
    }
}
