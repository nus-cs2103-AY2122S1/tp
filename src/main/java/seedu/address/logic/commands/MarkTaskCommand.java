package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Marks a task as done, with the task identified using its displayed index from the application
 */
public class MarkTaskCommand extends Command {

    public static final String COMMAND_WORD = "marktask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a task identified by the index number used in the displayed task list as done.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_TASK_SUCCESS = "Marked Task: %1$s";

    public static final String MESSAGE_TASK_ALREADY_MARKED = "Task already marked: %1$s";

    private final Index targetIndex;

    public MarkTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToMark = lastShownList.get(targetIndex.getZeroBased());
        boolean hasChanged = model.markTask(taskToMark);
        if (hasChanged) {
            return new CommandResult(String.format(MESSAGE_MARK_TASK_SUCCESS, taskToMark),
                    CommandResult.DisplayState.TASK);
        } else {
            return new CommandResult(String.format(MESSAGE_TASK_ALREADY_MARKED, taskToMark),
                    CommandResult.DisplayState.TASK);
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkTaskCommand // instanceof handles nulls
                && targetIndex.equals(((MarkTaskCommand) other).targetIndex)); // state check
    }
}
