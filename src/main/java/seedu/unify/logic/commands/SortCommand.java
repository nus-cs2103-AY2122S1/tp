package seedu.unify.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.BiFunction;

import seedu.unify.logic.commands.exceptions.CommandException;
import seedu.unify.model.Model;
import seedu.unify.model.task.Task;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all tasks "
        + "in the weekly view based on either time "
        + "or priority in ascending or descending order.\n"
        + "The default sort type is time and default order is ascending.\n"
        + "Parameters: x/(time or priority) o/(asc or desc)\n"
        + "Example: " + COMMAND_WORD + "x/time o/asc";

    public static final String MESSAGE_SORT_TASK_SUCCESS = "Sorted tasks in the weekly "
        + "view.";

    //private final Type sortType;
    private final BiFunction<Task, Task, Integer> f;

    public SortCommand(BiFunction<Task, Task, Integer> f) {
        this.f = f;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortTasks(f::apply);
        return new CommandResult(MESSAGE_SORT_TASK_SUCCESS);
    }


}
