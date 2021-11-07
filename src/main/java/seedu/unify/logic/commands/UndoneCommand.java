package seedu.unify.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.unify.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;

import seedu.unify.commons.core.Messages;
import seedu.unify.commons.core.index.Index;
import seedu.unify.logic.commands.exceptions.CommandException;
import seedu.unify.model.Model;
import seedu.unify.model.task.State;
import seedu.unify.model.task.Task;

/**
 * Marks a task as pending using it's displayed index from Uni-Fy
 */
public class UndoneCommand extends Command {

    public static final String COMMAND_WORD = "undone";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Mark the task identified by the index number used in the displayed task list as pending.\n"
            + "Parameters: task_id\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNDONE_TASK_SUCCESS = "Undone Task: %1$s";

    private final Index targetIndex;

    public UndoneCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToUnMark = lastShownList.get(targetIndex.getZeroBased());
        model.setTask(taskToUnMark,
                new Task(
                        taskToUnMark.getName(),
                        taskToUnMark.getTime(),
                        taskToUnMark.getDate(),
                        taskToUnMark.getTags(),
                        new State(State.ObjectState.TODO),
                        taskToUnMark.getPriority()));
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_UNDONE_TASK_SUCCESS, taskToUnMark));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UndoneCommand // instanceof handles nulls
                && targetIndex.equals(((UndoneCommand) other).targetIndex)); // state check
    }

}
