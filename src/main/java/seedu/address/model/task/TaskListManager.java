package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.chart.PieChart;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Serves as a main database for every {@code Person}'s taskList.
 * Manages which {@code Person}'s taskList to display on the GUI.
 */
public class TaskListManager {
    private static final Logger logger = LogsCenter.getLogger(TaskListManager.class);

    private static final String PIE_CHART_LABEL_FORMAT = "%s\n[%.0f]";

    private String[] pieChartLabelNames = {"Done", "In Progress", "Overdue", "Due soon"};

    /** Stores every Person's taskList reference. */
    private final HashMap<String, List<Task>> taskListArchive;

    /** Name of person whose taskList is chosen to be displayed. */
    private Name nameOfChosenPerson = null;

    /** Flag to indicate if nameOfChosenPerson is initialised */
    private boolean isPersonSelected;

    private ObservableList<Task> observableTaskList;

    /** The taskList to be displayed on the GUI. */
    private FilteredList<Task> filteredTasks;

    /** Task's statistics that will be displayed */
    private ObservableList<PieChart.Data> statList;

    /** Constructor for TaskListManager */
    public TaskListManager() {
        taskListArchive = new HashMap<>();
        isPersonSelected = false;
        observableTaskList = FXCollections.observableArrayList(Task.extractor());
        filteredTasks = new FilteredList<>(observableTaskList);
        statList = FXCollections.observableArrayList();

        for (int i = 0; i < 4; i++) {
            statList.add(new PieChart.Data(
                    String.format(PIE_CHART_LABEL_FORMAT, pieChartLabelNames[i], 0.0), 0));
        }

        updateStatistics();
    }

    /**
     * Sets up the archive with all stored {@code Person}'s taskList upon application startup.
     */
    public void initialiseArchive(ObservableList<Person> observablePersonList) {
        logger.fine("taskListArchive is loaded with storage memory.");

        requireNonNull(observablePersonList);

        for (Person person : observablePersonList) {
            createNewEntry(person);
            ObservableList<Task> tmp = FXCollections.observableArrayList(person.getTasks());
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
            setToDisplayTaskList(name, true);
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
    public void setToDisplayTaskList(Name name, boolean isSet) {
        requireNonNull(name);

        String logMessage = name + ": task list set to be displayed.";
        logger.info(logMessage);

        nameOfChosenPerson = name;

        assert(taskListArchive.containsKey(name.toString())) : "taskListArchive probably"
                + "not initialised properly.";

        List<Task> taskList = taskListArchive.get(name.toString());
        assert(taskList != null);

        observableTaskList.setAll(taskList);
        if (!isSet) {
            filteredTasks.setPredicate(task -> true);
        }
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

    /**
     * Returns an unmodifiable {@code ObservableList} for
     * defensive programming.
     */
    public ObservableList<Task> getFilteredTasks() {
        assert(filteredTasks != null);

        return filteredTasks;
    }

    /**
     * Returns an unmodifiable {@code ObservableList} for
     * defensive programming.
     */
    public ObservableList<PieChart.Data> getStatList() {
        return FXCollections.unmodifiableObservableList(statList);
    }

    /**
     * Sets new predicate for the {@code FilteredList} that is to be displayed
     * on the GUI.
     */
    public void setFilteredTasksPredicate(Predicate<Task> predicate) {
        requireNonNull(predicate);

        filteredTasks.setPredicate(predicate);
    }

    public Name getNameOfChosenPerson() {
        return nameOfChosenPerson;
    }

    /**
     * Updates {@code statList}'s values.
     */
    public void updateStatistics() {
        double[] statValueList = calculateStatistics();

        for (int i = 0; i < 4; i++) {
            statList.get(i).setName(
                    String.format(PIE_CHART_LABEL_FORMAT, pieChartLabelNames[i], statValueList[i]));
            statList.get(i).setPieValue(statValueList[i]);
        }
    }

    /**
     * Iterates through all {@code Task}s and updates their status.
     */
    public void updateAllTaskStatus() {
        taskListArchive.values().stream()
                .flatMap(Collection::stream)
                .forEach(Task::updateDueDate);
    }

    /**
     * Calculates the following statistics:
     * <br>
     * 1. {@code Task}s that are done
     * 2. {@code Task}s that are not done yet (In progress)
     * 3. {@code Task}s that are over due
     * 4. {@code Task}s that are due soon
     */
    public double[] calculateStatistics() {
        double totalTask = 0.0;
        double taskDone = 0.0;
        double taskNotDone = 0.0;
        double taskOverdue = 0.0;
        double taskDueSoon = 0.0;

        for (List<Task> taskList : taskListArchive.values()) {
            for (Task task : taskList) {
                if (task.getIsDueSoon()) {
                    taskDueSoon++;
                } else if (task.getIsOverdue()) {
                    taskOverdue++;
                } else if (task.getDone()) {
                    taskDone++;
                } else {
                    // Task is not done yet.
                    taskNotDone++;
                }
                totalTask++;
            }
        }
        return new double[]{taskDone, taskNotDone, taskOverdue, taskDueSoon, totalTask};
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
