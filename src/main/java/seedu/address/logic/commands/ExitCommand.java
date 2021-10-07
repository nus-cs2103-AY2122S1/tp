package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String DESCRIPTION = "Exits the program.";

    public static final String MESSAGE_USAGE = DESCRIPTION;

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Address Book as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

    public static String getCommand() {
        return COMMAND_WORD;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }
}
