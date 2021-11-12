package seedu.unify.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.unify.commons.core.Messages.MESSAGE_DUPLICATE_INDEX;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.unify.commons.core.Messages;
import seedu.unify.commons.core.index.Index;
import seedu.unify.logic.commands.exceptions.CommandException;
import seedu.unify.model.Model;
import seedu.unify.model.task.Task;

/**
 * Deletes a task identified using it's displayed index(es) from Uni-Fy.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task(s) identified by the index number(s) used in the displayed task list.\n"
            + "Index numbers must be positive integers.\n"
            + "Parameters: task_id (task_id)...\n"
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

        // Checks for duplicates
        Set<Integer> indexSet = new HashSet<>();
        for (Index index : targetIndexes) {
            indexSet.add(index.getZeroBased());
        }
        if (indexSet.size() < targetIndexes.size()) {
            throw new CommandException(MESSAGE_DUPLICATE_INDEX);
        }

        // Deletes from the last item to prevent future deletes operating on wrong indexes
        for (int i = targetIndexes.size() - 1; i >= 0; i--) {
            if (targetIndexes.get(i).getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
            }

            Task taskToDelete = lastShownList.get(targetIndexes.get(i).getZeroBased());
            deletedTasks.add(0, taskToDelete);
            model.deleteTask(taskToDelete);
        }

        String deletedTasksString = "\n";

        for (Task task : deletedTasks) {
            deletedTasksString += task.toString() + "\n";
        }

        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, deletedTasksString));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndexes.equals(((DeleteCommand) other).targetIndexes)); // state check
    }
}
