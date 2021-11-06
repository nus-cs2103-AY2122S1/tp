package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_NO_PARAMS;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_ACTION = "Exit Program";

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Address Book as requested ...";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the application.\n" + MESSAGE_NO_PARAMS;

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, CommandResult.DisplayType.EXIT);
    }

}
