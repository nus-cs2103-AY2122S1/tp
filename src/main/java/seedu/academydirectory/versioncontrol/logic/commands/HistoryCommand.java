package seedu.academydirectory.versioncontrol.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.academydirectory.logic.commands.CommandResult;
import seedu.academydirectory.logic.commands.exceptions.CommandException;
import seedu.academydirectory.versioncontrol.OptionalVersion;
import seedu.academydirectory.versioncontrol.Version;

/**
 * Lists all commands used in the academy directory to the user.
 */
public class HistoryCommand extends VcCommand {
    public static final String COMMAND_WORD = "history";

    public static final String HELP_MESSAGE = "### Listing all history : `history`\n"
            + "\n"
            + "Shows a list of all command history in the academy directory.\n"
            + "\n"
            + "Format: `history`";

    @Override
    public CommandResult execute(OptionalVersion<Version> version) throws CommandException {
        requireNonNull(version);
        return new CommandResult(String.join("\n", version.retrieveHistory()));
    }
}
