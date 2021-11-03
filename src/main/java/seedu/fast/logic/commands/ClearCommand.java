package seedu.fast.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.fast.commons.core.LogsCenter;
import seedu.fast.model.Fast;
import seedu.fast.model.Model;



/**
 * Clears FAST.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears the content of FAST (Deletes all your data!) This command CANNOT be undone!\n\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "FAST has been cleared!";

    private final Logger logger = LogsCenter.getLogger(getClass());


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFast(new Fast());
        logger.info("-----Clear Command: Success-----");

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
