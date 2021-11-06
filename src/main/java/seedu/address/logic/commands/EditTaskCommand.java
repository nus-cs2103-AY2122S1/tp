package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_VENUE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDate;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TaskTime;
import seedu.address.model.task.Venue;

/**
 * Edits the details of an existing task.
 */
public class EditTaskCommand extends Command {

    public static final String COMMAND_WORD = "edittask";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
            + "by the index number used in the displayed person list and task number. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer less than or equal to " + Integer.MAX_VALUE + ")\n"
            + PREFIX_TASK_INDEX + " TASK_INDEX (must be a positive integer less than or equal to "
            + Integer.MAX_VALUE + ")\n"
            + "[" + PREFIX_TASK_DESCRIPTION + " TASK_NAME] "
            + "[" + PREFIX_TASK_DATE + " TASK_DATE] "
            + "[" + PREFIX_TASK_TIME + " TASK_TIME] "
            + "[" + PREFIX_TASK_VENUE + " TASK_ADDRESS] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TASK_INDEX + " 2 "
            + PREFIX_TASK_DESCRIPTION + "Assignment Discussion"
            + PREFIX_IMPORTANCE + "false";

    public static final String DESCRIPTION = "Edits the details of the task identified";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s ";
    public static final String MESSAGE_TASK_NOT_EDITED = "At least one field of task to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "Task already exists.";
    public static final String MESSAGE_INVALID_TASK = "The size of %1$s's task list is not that big";

    private final Index targetPersonIndex;
    private final Index targetTaskIndex;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * Constructor for EditTaskCommand to edit a person's task.
     *
     * @param targetPersonIndex of the person in the filtered person list
     * @param targetTaskIndex of the task to edit
     */
    public EditTaskCommand(Index targetPersonIndex, Index targetTaskIndex, EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(targetPersonIndex);
        requireNonNull(targetTaskIndex);
        requireNonNull(editTaskDescriptor);

        this.targetPersonIndex = targetPersonIndex;
        this.targetTaskIndex = targetTaskIndex;
        this.editTaskDescriptor = editTaskDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = getLastShownList(model);

        if (targetPersonIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = model.getFilteredPersonList().get(targetPersonIndex.getZeroBased());
        checkPersonToEditTasksDisplayed(model, personToEdit);

        List<Task> tasks = new ArrayList<>(personToEdit.getTasks());
        List<Task> taskListToModify = getTaskListToModify(model);

        if (targetTaskIndex.getZeroBased() >= taskListToModify.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_TASK, personToEdit.getName()));
        }

        Task taskToEdit = taskListToModify.get(targetTaskIndex.getZeroBased());
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);
        replaceTaskWithEditedVersion(tasks, taskToEdit, editedTask);

        Person editedPerson = createEditedPerson(personToEdit, tasks);
        model.setPerson(personToEdit, editedPerson);

        return generateWriteCommandResult(editedTask);
    }

    /**
     * Checks if the person whose task(s) is selected for modification has their task list displayed
     * on the task list panel.
     */
    private void checkPersonToEditTasksDisplayed(Model model, Person personToEdit) throws CommandException {
        boolean isPersonToEditTaskDisplayed = personToEdit.getName().equals(model.getTaskListManager()
                .getNameOfChosenPerson());
        if (!isPersonToEditTaskDisplayed && !model.getIsViewAllTasks()) {
            throw new CommandException(Messages.MESSAGE_PERSON_TO_EDIT_TASK_NOT_DISPLAYED);
        }
    }

    private void replaceTaskWithEditedVersion(List<Task> tasks, Task taskToEdit, Task editedTask)
            throws CommandException {
        int taskToEditIndex = getTaskToEditIndex(tasks, taskToEdit);

        if (taskToEdit.equals(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }
        tasks.set(taskToEditIndex, editedTask);
    }

    private Person createEditedPerson(Person personToEdit, List<Task> tasks) {
        return new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(), tasks, personToEdit.getDescription(),
                personToEdit.isImportant()
        );
    }

    private CommandResult generateWriteCommandResult(Task editedTask) {
        CommandResult commandResult = new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
        commandResult.setWriteCommand();
        return commandResult;
    }

    /**
     * If every person's task list is being displayed, the person list used to facilitate
     * that is returned. Otherwise the person list used for display on the person list panel
     * is returned.
     */
    private List<Task> getTaskListToModify(Model model) {
        List<Task> taskListToModify;
        if (model.getIsViewAllTasks()) {
            taskListToModify = model.getViewAllTaskListPersons().get(targetPersonIndex.getZeroBased()).getTasks();
        } else {
            taskListToModify = model.getDisplayTaskList();
        }
        return taskListToModify;
    }

    private List<Person> getLastShownList(Model model) {
        List<Person> lastShownList;
        if (model.getIsViewAllTasks()) {
            lastShownList = model.getViewAllTaskListPersons();
        } else {
            lastShownList = model.getFilteredPersonList();
        }
        return lastShownList;
    }

    private static Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor) {
        assert(taskToEdit != null);

        TaskName updatedName = editTaskDescriptor.getTaskName().orElse(taskToEdit.getTaskName());
        TaskDate updatedDate = editTaskDescriptor.getTaskDate().orElse(taskToEdit.getDate());
        TaskTime updatedTime = editTaskDescriptor.getTaskTime().orElse(taskToEdit.getTime());
        Venue updatedVenue = editTaskDescriptor.getTaskVenue().orElse(taskToEdit.getVenue());

        Task updatedTask = new Task(updatedName, updatedDate, updatedTime, updatedVenue);

        return updatedTask;
    }

    private int getTaskToEditIndex(List<Task> tasks, Task taskToEdit) {
        int idx = 0;
        for (Task task : tasks) {
            if (task == taskToEdit) {
                break;
            }
            idx++;
        }
        return idx;
    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {

        private TaskName taskName;
        private TaskDate taskDate;
        private TaskTime taskTime;
        private Venue taskVenue;
        public EditTaskDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            setTaskName(toCopy.taskName);
            setTaskDate(toCopy.taskDate);
            setTaskTime(toCopy.taskTime);
            setTaskVenue(toCopy.taskVenue);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(taskName, taskDate, taskTime, taskVenue);
        }

        public void setTaskName(TaskName taskName) {
            this.taskName = taskName;
        }

        public Optional<TaskName> getTaskName() {
            return Optional.ofNullable(taskName);
        }

        public void setTaskDate(TaskDate taskDate) {
            this.taskDate = taskDate;
        }

        public Optional<TaskDate> getTaskDate() {
            return Optional.ofNullable(taskDate);
        }

        public void setTaskTime(TaskTime taskTime) {
            this.taskTime = taskTime;
        }

        public Optional<TaskTime> getTaskTime() {
            return Optional.ofNullable(taskTime);
        }

        public void setTaskVenue(Venue venue) {
            this.taskVenue = venue;
        }

        public Optional<Venue> getTaskVenue() {
            return Optional.ofNullable(taskVenue);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTaskDescriptor)) {
                return false;
            }

            // state check
            EditTaskDescriptor e = (EditTaskDescriptor) other;

            return getTaskName().equals(e.getTaskName())
                    && getTaskDate().equals(e.getTaskDate())
                    && getTaskTime().equals(e.getTaskTime())
                    && getTaskVenue().equals(e.getTaskVenue());
        }

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTaskCommand)) {
            return false;
        }

        // state check
        EditTaskCommand e = (EditTaskCommand) other;
        return targetPersonIndex.equals(e.targetPersonIndex)
                && targetTaskIndex.equals(e.targetTaskIndex)
                && editTaskDescriptor.equals(e.editTaskDescriptor);
    }

    public String getCommand() {
        return COMMAND_WORD;
    }

    public String getDescription() {
        return DESCRIPTION;
    }
}
