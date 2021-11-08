package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.DeadlineTask;
import seedu.address.model.task.Task;

public class AddDeadlineTaskCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a deadline task to the tApp task list. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_DEADLINE + "DEADLINE "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Tutorial Prep "
            + PREFIX_DEADLINE + "2021-09-12 "
            + PREFIX_DESCRIPTION + "Prof Aaron's mod "
            + PREFIX_TAG + "W1404";

    public static final String MESSAGE_SUCCESS = "New deadline task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the tApp task list.";

    public final Task toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Student}
     */
    public AddDeadlineTaskCommand(DeadlineTask task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDeadlineTaskCommand // instanceof handles nulls
                && toAdd.equals(((AddDeadlineTaskCommand) other).toAdd));
    }
}
