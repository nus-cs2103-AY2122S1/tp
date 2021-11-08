package seedu.address.logic.commands.tasks;

import java.util.Map;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.id.UniqueId;

public abstract class UnassignTaskCommand extends ManageTaskAssignmentCommand {
    public static final String MESSAGE_USAGE = "%1$s: Unassigns a task of the %1$s "
            + ManageTaskAssignmentCommand.MESSAGE_USAGE;

    public static final String MISSING_TASK = "Specified task is not assigned to this %s!";
    public static final String MESSAGE_SUCCESS = "Task %1$s unassigned from %3$s %2$s";

    /**
     * Constructs a {@code UnassignTaskCommand}
     *
     * @param assigneeIndex the index of the {@code TaskAssignable} to be unassigned a task
     * @param taskIndex the index of the task in the filtered task list to unassign
     */
    public UnassignTaskCommand(Index assigneeIndex, Index taskIndex) {
        super(assigneeIndex, taskIndex);
    }

    protected void handleTaskDuplicationOrNotFound(Set<UniqueId> taskSet, UniqueId taskId, String assigneeType)
            throws CommandException {
        if (!taskSet.contains(taskId)) {
            throw new CommandException(String.format(MISSING_TASK, assigneeType));
        }
    }

    protected void manageTaskAssignment(Set<UniqueId> newTaskSet, Map<UniqueId, Boolean> newTasksCompletion,
                                        UniqueId taskId) {
        newTaskSet.remove(taskId);
        newTasksCompletion.remove(taskId);
    }
}
