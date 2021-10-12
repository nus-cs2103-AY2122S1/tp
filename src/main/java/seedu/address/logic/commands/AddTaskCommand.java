package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.ModuleName;
import seedu.address.model.task.Task;

/**
 * Adds a student to the address book.
 */
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "add task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to a module. "
            + "Parameters: "
            + PREFIX_MODULE_NAME + "MODULE NAME"
            + PREFIX_TASK_NAME + "TASK NAME"
            + PREFIX_TASK_DEADLINE + "TASK DEADLINE";

    public static final String MESSAGE_ADD_TASK_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the module";
    public static final String MESSAGE_MODULE_NOT_FOUND= "This module is not found.";

    private final Task toAdd;
    private final ModuleName module;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddTaskCommand(ModuleName moduleName, Task task) {
        requireNonNull(task);
        toAdd = task;
        this.module = moduleName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasModule(module)) {
            throw new CommandException(MESSAGE_MODULE_NOT_FOUND);
        }

        if (model.hasTask(module, toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addTask(module, toAdd);
        return new CommandResult(String.format(MESSAGE_ADD_TASK_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && toAdd.equals(((AddTaskCommand) other).toAdd));
    }
}
