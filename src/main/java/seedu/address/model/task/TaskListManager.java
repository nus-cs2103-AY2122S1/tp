package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Serves as a main data base for every {@code Person}'s taskList.
 * Manages which {@code Person}'s taskList to display on the GUI.
 */
public class TaskListManager {
    private static final Logger logger = LogsCenter.getLogger(TaskListManager.class);

    /** Stores every Person's taskList reference. */
    private final HashMap<String, List<Task>> taskListArchive;

    /** Name of person whose taskList is chosen to be displayed. */
    private Name nameOfChosenPerson;

    /** Flag to indicate if nameOfChosenPerson is initialised */
    private boolean isPersonSelected;

    private ObservableList<Task> observableTaskList;

    /** The taskList to be displayed on the GUI. */
    private FilteredList<Task> filteredTasks;

    /** Constructor for TaskListManager */
    public TaskListManager() {
        taskListArchive = new HashMap<>();
        isPersonSelected = false;
        observableTaskList = FXCollections.observableArrayList();
        filteredTasks = new FilteredList<>(observableTaskList);
    }

    /**
     * Sets up the archive with all stored person's taskList upon application startup.
     */
    public void initialiseArchive(ObservableList<Person> observablePersonList) {
        logger.fine("taskListArchive is loaded with storage memory.");

        requireNonNull(observablePersonList);

        for (Person person : observablePersonList) {
            createNewEntry(person);
        }
    }

    /**
     * Replaces the archive entry of {@code target} to that of {@code editedPerson}.
     *
     * @param target The {@code Person} whose entry is to be replaced.
     * @param editedPerson The {@code Person} who is replacing the entry with its own.
     */
    public void updateEntry(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        deleteEntry(target.getName());
        updateEntryTaskList(editedPerson.getName(), editedPerson.getTasks());
    }

    /**
     * Updates the task list of the entry corresponding to {@code Name} to that of
     * {@code taskList}.
     *
     * @param name The {@code Name} of the {@code Person} whose entry you want to update.
     * @param taskList The {@code List<Task>} that you want to update the entry's task list to.
     */
    public void updateEntryTaskList(Name name, List<Task> taskList) {
        requireAllNonNull(name, taskList);

        taskListArchive.put(name.toString(), taskList);

        if (name.equals(nameOfChosenPerson)) {
            setToDisplayTaskList(name);
        }
    }

    /**
     * Removes the entry corresponding to {@code name} from the {@code taskListArchive}.
     *
     * @param name The {@code Name} of the {@code Person} whose entry you want to delete.
     */
    public void deleteEntry(Name name) {
        requireNonNull(name);

        taskListArchive.remove(name.toString());
    }

    /**
     * Sets the task list corresponding to {@code name} to be displayed on the GUI.
     *
     * @param name The {@code Name} of the {@code Person} whose task list you want to
     *             display on the GUI.
     */
    public void setToDisplayTaskList(Name name) {
        requireNonNull(name);

        String logMessage = name.toString() + ": task list set to be displayed.";
        logger.info(logMessage);

        nameOfChosenPerson = name;

        assert(taskListArchive.containsKey(name.toString())) : "taskListArchive probably"
                + "not initialised properly.";

        List<Task> taskList = taskListArchive.get(name.toString());
        assert(taskList != null);

        observableTaskList.setAll(taskList);
        filteredTasks.setPredicate(task -> true);
    }

    /**
     * Creates a new entry in the taskListStorage for {@code person}.
     *
     * @param person The {@code Person} whom you want to create an entry for.
     */
    public void createNewEntry(Person person) {
        requireNonNull(person);

        updateEntryTaskList(person.getName(), person.getTasks());
    }

    public ObservableList<Task> getFilteredTasks() {
        assert(filteredTasks != null);

        return filteredTasks;
    }

    /**
     * Sets new predicate for the {@code FilteredList} that is to be displayed
     * on the GUI.
     */
    public void setFilteredTasksPredicate(Predicate<Task> predicate) {
        requireNonNull(predicate);

        filteredTasks.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof TaskListManager)) {
            return false;
        }

        // state check
        TaskListManager other = (TaskListManager) obj;

        boolean isEquals = taskListArchive.equals(other.taskListArchive)
                && observableTaskList.equals(other.observableTaskList)
                && filteredTasks.equals(other.filteredTasks);

        if (isPersonSelected) {
            isEquals = isEquals && nameOfChosenPerson.equals(other.nameOfChosenPerson);
        }

        return isEquals;
    }
}
