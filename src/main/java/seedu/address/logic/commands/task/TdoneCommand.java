package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_ID;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.task.Task;

/**
 * Marks one or multiple task(s) as done for a certain selected member.
 */
public class TdoneCommand extends Command {

    public static final String COMMAND_WORD = "tdone";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks one or multiple task(s) as done "
            + "the current selected member "
            + "by the index numbers used in the displayed task list of the task identified\n"
            + "Parameters: " + PREFIX_TASK_ID + "TASK_ID... (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK_ID + "1 "
            + PREFIX_TASK_ID + "2";

    public static final String MESSAGE_DONE_TASK_SUCCESS = "Marked task: %1$s as done\n";
    public static final String MESSAGE_TASK_HAS_DONE = "The task: %1$s has been done before.";
    public static final String MESSAGE_TASK_NOT_FOUND = "This task does not exist in the task list of the member";

    private final Set<Index> indexList;

    /**
     * Creates a TdoneCommand to mark the task(s) identified by {@code indexList}.
     */
    public TdoneCommand(Set<Index> indexList) {
        requireNonNull(indexList);
        this.indexList = indexList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownTaskList = model.getFilteredTaskList();
        StringBuilder resultMessage = new StringBuilder();

        for (Index targetIndex : indexList) {
            if (targetIndex.getZeroBased() >= lastShownTaskList.size()) {
                throw new CommandException(MESSAGE_TASK_NOT_FOUND);
            }

            Task taskToEdit = lastShownTaskList.get(targetIndex.getZeroBased());
            if (taskToEdit.isDone()) {
                throw new CommandException(String.format(MESSAGE_TASK_HAS_DONE, taskToEdit));
            }

            Task editedTask = new Task(taskToEdit.getName(), true, taskToEdit.getTaskDeadline());
            model.setTask(targetIndex.getZeroBased(), editedTask);
            resultMessage.append(String.format(MESSAGE_DONE_TASK_SUCCESS, editedTask));
        }

        return new CommandResult(resultMessage.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TdoneCommand that = (TdoneCommand) o;
        return Objects.equals(indexList, that.indexList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indexList);
    }
}
