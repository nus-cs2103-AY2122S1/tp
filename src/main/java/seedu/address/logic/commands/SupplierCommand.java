package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Switches to a the supplier view in the application.
 */
public class SupplierCommand extends Command {

    public static final String COMMAND_WORD = "supplier";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches to supplier view.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_SWITCH_MESSAGE = "Switched to Supplier View.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_SWITCH_MESSAGE, false, false, false, true);
    }
}

