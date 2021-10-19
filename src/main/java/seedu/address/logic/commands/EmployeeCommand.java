package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Switches to the employee view in the application.
 */
public class EmployeeCommand extends Command {

    public static final String COMMAND_WORD = "employee";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches to employee view.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_SWITCH_MESSAGE = "Switched to Employee View.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_SWITCH_MESSAGE, false, false, false, true, false, false);
    }
}

