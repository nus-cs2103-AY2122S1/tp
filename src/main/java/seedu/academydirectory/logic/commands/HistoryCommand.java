package seedu.academydirectory.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.academydirectory.logic.commands.exceptions.CommandException;
import seedu.academydirectory.model.VersionedModel;

/**
 * Lists all commands used in the academy directory to the user.
 */
public class HistoryCommand extends Command {
    public static final String COMMAND_WORD = "history";

    public static final String HELP_MESSAGE = "### Listing all history : `history`\n"
            + "\n"
            + "Shows a list of all command history in the academy directory.\n"
            + "\n"
            + "Format: `history`";

    @Override
    public CommandResult execute(VersionedModel model) throws CommandException {
        requireNonNull(model);
        return new CommandResult(String.join("\n", model.retrieveHistory()));
    }
}
