package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.FilterTaskCriterion;
import seedu.address.model.task.Task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

public class FilterTaskCommand extends Command {
    public static final String COMMAND_WORD = "filterTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": filters tasks based on specified criterion. "
            + "Parameters: " + "FILTER_CRITERION"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUP + "CS2101 ";

    public static final String MESSAGE_SUCCESS = "filtered tasks by %1$s criterion";

    private final FilterTaskCriterion toFilter;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
    public FilterTaskCommand(FilterTaskCriterion filterTaskCriterion) {
        requireNonNull(filterTaskCriterion);
        toFilter = filterTaskCriterion;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.filterTask(toFilter);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toFilter.toString()));
    }
}
