package seedu.address.logic.commands.tasks;

import java.util.Map;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.id.UniqueId;

public abstract class AssignTaskCommand extends ManageTaskAssignmentCommand {

    public static final String MESSAGE_USAGE = "%1$s: Assigns a task to the %1$s"
            + ManageTaskAssignmentCommand.MESSAGE_USAGE;

    public static final String OVERLAPPING_TASK = "Task is already assigned to this %s!";
    public static final String MESSAGE_SUCCESS = "Task %1$s assigned to %3$s %2$s";

    /**
     * Constructs a {@code AssignTaskCommand}
     *
     * @param assigneeIndex the index of the {@code TaskAssignable} to be assigned a task
     * @param taskIndex the index of the task in the filtered task list to assign
     */
    public AssignTaskCommand(Index assigneeIndex, Index taskIndex) {
        super(assigneeIndex, taskIndex);
    }

    protected void handleTaskDuplicationOrNotFound(Set<UniqueId> taskSet, UniqueId taskId, String assigneeType)
            throws CommandException {
        if (taskSet.contains(taskId)) {
            throw new CommandException(String.format(OVERLAPPING_TASK, assigneeType));
        }
    }

    protected void manageTaskAssignment(Set<UniqueId> newTaskSet, Map<UniqueId, Boolean> newTasksCompletion,
                                        UniqueId taskId) {
        newTaskSet.add(taskId);
        newTasksCompletion.put(taskId, false);
    }
}
