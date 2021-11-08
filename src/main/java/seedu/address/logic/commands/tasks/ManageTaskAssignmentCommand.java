package seedu.address.logic.commands.tasks;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TaskAssignable;
import seedu.address.model.id.UniqueId;
import seedu.address.model.task.Task;

/**
 * This class serves as the base class for any {@code Command} subclasses related to assigning or unassigning tasks
 * to {@code TaskAssignable}s.
 */
public abstract class ManageTaskAssignmentCommand extends Command {
    /**
     * Subclasses should Complete the beginning of the message by adding suitable detail.
     */
    public static final String MESSAGE_USAGE = " identified by the index numbers used in the displayed %2$s list and"
            + " the displayed task list. "
            + "Parameters: %3$s INDEX (must be a positive integer) "
            + "TASK INDEX (must be a positive integer) ";

    public static final String MESSAGE_DUPLICATE_TASK_ASSIGNABLE = "This %s already exists in the address book";
    public static final String MESSAGE_SUCCESS = "Task %1$s (un)assigned from/to %3$s %2$s";

    private final Index assigneeIndex;
    private final Index taskIndex;

    /**
     * Constructs a {@code ManageTaskAssignmentCommand}
     *
     * @param assigneeIndex the index of the {@code TaskAssignable} to be (un)assigned a task
     * @param taskIndex the index of the task in the filtered task list to (un)assign
     */
    public ManageTaskAssignmentCommand(Index assigneeIndex, Index taskIndex) {
        requireAllNonNull(assigneeIndex, taskIndex);

        this.assigneeIndex = assigneeIndex;
        this.taskIndex = taskIndex;
    }

    protected abstract String invalidDisplayedIndexMessage();

    /**
     * Returns a filtered list of {@code TaskAssignable} from the model.
     *
     * @param model The model where we want to retrieve the list from.
     * @return A filtered list.
     */
    protected abstract List<? extends TaskAssignable> getTaskAssignableListFromModel(Model model);

    /**
     * Update the model after the command is executed.
     *
     * @param model The model to be modified.
     */
    protected abstract void updateModel(Model model, TaskAssignable taskAssignableToEdit, TaskAssignable
            newTaskAssignable);

    protected abstract void handleTaskDuplicationOrNotFound(Set<UniqueId> taskSet, UniqueId taskId, String assigneeType)
            throws CommandException;

    protected abstract void manageTaskAssignment(Set<UniqueId> newTaskSet, Map<UniqueId, Boolean> newTasksCompletion,
            UniqueId taskId);

    /**
     * Executes the command on the given model.
     *
     * @param model The model we want to apply the command on.
     * @param assigneeType The type of the assignee, e.g., student, group, ...
     * @return Feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    public CommandResult executeWithGivenMessage(Model model, String assigneeType) throws CommandException {
        requireNonNull(model);
        List<? extends TaskAssignable> assigneeList = getTaskAssignableListFromModel(model);
        List<Task> taskList = model.getFilteredTaskList();

        if (assigneeIndex.getZeroBased() >= assigneeList.size()) {
            throw new CommandException(invalidDisplayedIndexMessage());
        }

        if (taskIndex.getZeroBased() >= taskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        TaskAssignable taskAssignableToEdit = assigneeList.get(assigneeIndex.getZeroBased());
        Task taskToAssign = taskList.get(taskIndex.getZeroBased());
        Set<UniqueId> previousAssignedTaskSet = taskAssignableToEdit.getAssignedTaskIds();
        Map<UniqueId, Boolean> previousTasksCompletion = taskAssignableToEdit.getTasksCompletion();
        UniqueId taskId = taskToAssign.getId();

        handleTaskDuplicationOrNotFound(previousAssignedTaskSet, taskId, assigneeType);

        Set<UniqueId> newAssignedTaskSet = new HashSet<>(previousAssignedTaskSet);
        Map<UniqueId, Boolean> newTasksCompletion = new HashMap<>(previousTasksCompletion);

        manageTaskAssignment(newAssignedTaskSet, newTasksCompletion, taskId);

        TaskAssignable newTaskAssignable = taskAssignableToEdit.updateAssignedTaskIds(newAssignedTaskSet);
        newTaskAssignable = newTaskAssignable.updateTasksCompletion(newTasksCompletion);

        if (!taskAssignableToEdit.isSameTaskAssignable(newTaskAssignable)
                && newTaskAssignable.isInModel(model)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_TASK_ASSIGNABLE, assigneeType));
        }

        updateModel(model, taskAssignableToEdit, newTaskAssignable);

        return new CommandResult(String.format(MESSAGE_SUCCESS, taskToAssign.getDescription(),
                newTaskAssignable.getNameInString(), assigneeType));
    }
}
