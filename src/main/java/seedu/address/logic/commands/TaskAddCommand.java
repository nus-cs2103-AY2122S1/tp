package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Adds a new task to the module.
 */
public class TaskAddCommand extends Command {

    public static final String COMMAND_WORD = "task add";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Hello from task add");
    }
}
