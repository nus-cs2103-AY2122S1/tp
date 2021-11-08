package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.TodoTask;

public class AddTodoTaskCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a todo task to the tApp task list. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Tutorial Prep "
            + PREFIX_DESCRIPTION + "Prof Aaron's mod "
            + PREFIX_TAG + "W1404";

    //TODO todo task description
    public static final String MESSAGE_SUCCESS = "New todo task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the tApp task list.";

    public final TodoTask toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Student}
     */
    public AddTodoTaskCommand(TodoTask task) {
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
                || (other instanceof AddTodoTaskCommand // instanceof handles nulls
                && toAdd.equals(((AddTodoTaskCommand) other).toAdd));
    }
}
