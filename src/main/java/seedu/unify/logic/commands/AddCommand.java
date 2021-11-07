package seedu.unify.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.unify.logic.commands.exceptions.CommandException;
import seedu.unify.model.Model;
import seedu.unify.model.task.Task;

/**
 * Adds a task to the Uni-Fy app.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the Uni-Fy app. \n"
            + "Parameters: "
            + PREFIX_NAME + "task_name "
            + PREFIX_DATE + "date "
            + PREFIX_TIME + "time "
            + PREFIX_TAG + "tag "
            + PREFIX_PRIORITY + "priority_level \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Assignment 1 "
            + PREFIX_DATE + "2021-12-12 "
            + PREFIX_TIME + "16:40 "
            + PREFIX_TAG + "graded "
            + PREFIX_PRIORITY + "LOW ";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the Uni-Fy app";

    private final Task toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Task}
     */
    public AddCommand(Task task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        assert toAdd != null;
        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
