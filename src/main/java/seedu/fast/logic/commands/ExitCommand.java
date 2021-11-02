package seedu.fast.logic.commands;

import seedu.fast.commons.core.LogsCenter;
import seedu.fast.model.Model;

import java.util.logging.Logger;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Exits FAST and closes the window \n\n"
            + "Example: \n" + COMMAND_WORD;

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting FAST as requested ...";

    private final Logger logger = LogsCenter.getLogger(getClass());

    @Override
    public CommandResult execute(Model model) {
        logger.info("-----Exit Command: Success-----");
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
