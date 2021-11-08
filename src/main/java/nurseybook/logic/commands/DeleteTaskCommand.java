package nurseybook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import nurseybook.commons.core.Messages;
import nurseybook.commons.core.index.Index;
import nurseybook.logic.commands.exceptions.CommandException;
import nurseybook.model.Model;
import nurseybook.model.task.Task;

public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "deleteTask";
    public static final String[] PARAMETERS = { Index.VALID_INDEX_CRITERIA };

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the displayed task list.\n"
            + "Parameters: "
            + String.join(" ", PARAMETERS)
            + "\nExample: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted task: %1$s";

    private final Index targetIndex;

    public DeleteTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTask(taskToDelete);
        CommandResult result = new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete));
        model.updateTasksAccordingToTime();
        model.commitNurseyBook(result);
        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTaskCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTaskCommand) other).targetIndex)); // state check
    }
}
