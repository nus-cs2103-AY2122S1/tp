package nurseybook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import nurseybook.commons.core.Messages;
import nurseybook.commons.core.index.Index;
import nurseybook.logic.commands.exceptions.CommandException;
import nurseybook.model.Model;
import nurseybook.model.task.Task;

/**
 * Marks a task as done identified using its displayed index in the nursey book.
 */
public class DoneTaskCommand extends Command {

    public static final String COMMAND_WORD = "doneTask";
    public static final String[] PARAMETERS = { Index.VALID_INDEX_CRITERIA };

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the index number used in the displayed task list as done.\n"
            + "Parameters: "
            + String.join(" ", PARAMETERS)
            + "\nExample: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_TASK_DONE_SUCCESS = "Marked task as done: %1$s";

    public static final String MESSAGE_TASK_ALREADY_DONE = "Task already marked done.";

    private final Index targetIndex;

    public DoneTaskCommand(Index targetIndex) {
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

        if (taskToMark.isTaskDone()) {
            throw new CommandException(MESSAGE_TASK_ALREADY_DONE);
        }

        model.markTaskAsDone(taskToMark);
        CommandResult result = new CommandResult(String.format(MESSAGE_MARK_TASK_DONE_SUCCESS, taskToMark));
        model.updateTasksAccordingToTime();
        model.commitNurseyBook(result);
        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DoneTaskCommand // instanceof handles nulls
                && targetIndex.equals(((DoneTaskCommand) other).targetIndex)); // state check
    }
}
