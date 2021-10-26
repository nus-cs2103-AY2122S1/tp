package seedu.unify.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.unify.commons.core.Messages;
import seedu.unify.commons.core.index.Index;
import seedu.unify.logic.commands.exceptions.CommandException;
import seedu.unify.model.Model;
import seedu.unify.model.task.Task;

/**
 * Deletes a task identified using it's displayed index(es) or name from Uni-Fy.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the displayed task list.\n"
            + "Parameters: task_id\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task(s): %1$s";

    private final List<Index> targetIndexes;

    public DeleteCommand(List<Index> targetIndexes) {
        this.targetIndexes = targetIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();
        List<Task> deletedTasks = new ArrayList<>();

        // Deletes from the last item to prevent future deletes operating on wrong indexes
        for (int i = targetIndexes.size() - 1; i >= 0; i--) {
            if (targetIndexes.get(i).getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
            }

            Task taskToDelete = lastShownList.get(targetIndexes.get(i).getZeroBased());
            deletedTasks.add(0, taskToDelete);
            model.deleteTask(taskToDelete);
        }

        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, deletedTasks));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndexes.equals(((DeleteCommand) other).targetIndexes)); // state check
    }
}
