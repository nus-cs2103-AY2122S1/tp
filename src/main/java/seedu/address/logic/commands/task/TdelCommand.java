package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_INDEX_DEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Name;
import seedu.address.model.module.task.Task;

/**
 * Deletes a task from the task list of a person.
 */
public class TdelCommand extends Command {
    public static final String COMMAND_WORD = "tdel";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a task from the task list of a person. "
            + "Parameters: "
            + PREFIX_TASK_INDEX + "TASK_INDEX "
            + PREFIX_MEMBER_INDEX_DEL + "MEMBER_INDEX\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK_INDEX + "3 ";

    public static final String MESSAGE_SUCCESS = "This task is successfully deleted for %1$s: %2$s.";
    public static final String MESSAGE_TASK_NOT_FOUND = "Task %1$s does not exist in the task list of the member.\n";
    public final Index targetTaskId;

    /**
     * Creates an TdelCommand to delete the task with specified {@code TaskId}
     * from the member with specified {@code MemberId}.
     */
    public TdelCommand(Index taskId) {
        requireAllNonNull(taskId);
        targetTaskId = taskId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Task> lastShownTaskList = model.getFilteredTaskList();
        if (targetTaskId.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(String.format(MESSAGE_TASK_NOT_FOUND, targetTaskId.getOneBased()));
        }
        Task targetTask = lastShownTaskList.get(targetTaskId.getZeroBased());
        Name deletedTaskName = targetTask.getName();
        model.deleteTask(targetTask);

        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getCurrentMember().get().getName().toString(),
                deletedTaskName.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TdelCommand // instanceof handles nulls
                && targetTaskId.equals(((TdelCommand) other).targetTaskId));
    }
}
