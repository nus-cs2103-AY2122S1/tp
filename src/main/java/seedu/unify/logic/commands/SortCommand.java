package seedu.unify.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_SORT_ORDER;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.function.BiFunction;

import seedu.unify.logic.commands.exceptions.CommandException;
import seedu.unify.model.Model;
import seedu.unify.model.task.Task;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = "";

    public static final String MESSAGE_SORT_TASK_SUCCESS = COMMAND_WORD + ": Sorts the task list "
        + " by either priority or time.\n"
        + "Parameters: "
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_TYPE + "time " + PREFIX_SORT_ORDER + "ascending order ";

    //private final Type sortType;
    private final BiFunction<Task, Task, Integer> f;

    public SortCommand(BiFunction<Task, Task, Integer> f) {
        this.f = f;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String feedback = "";

        model.sortTasks(f::apply);

        return new CommandResult(MESSAGE_SORT_TASK_SUCCESS);
    }


}
