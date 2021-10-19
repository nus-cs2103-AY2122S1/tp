package seedu.academydirectory.versioncontrol.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.text.ParseException;

import seedu.academydirectory.logic.commands.CommandResult;
import seedu.academydirectory.versioncontrol.OptionalVersion;
import seedu.academydirectory.versioncontrol.Version;
import seedu.academydirectory.versioncontrol.objects.Commit;

public class RevertCommand extends VcCommand {
    public static final String COMMAND_WORD = "revert";

    public static final String HELP_MESSAGE = "### Revert to a history : `revert`\n"
            + "\n"
            + "Reverts storage to an old history in the academy directory.\n"
            + "\n"
            + "Format: `revert KEYWORD`";

    public static final String REVERT_SUCCESSFUL_ACKNOWLEDGEMENT = "Successfully reverted Academy"
            + " Directory as requested ... Restart the app to see changes!";

    public static final String REVERT_REQUEST_REJECTED = "Unable to revert Academy Directory as requested ...";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Reverts academy directory to the stage given by "
            + "the five character hash\n"
            + "Parameters: KEYWORD ...\n"
            + "Example: " + COMMAND_WORD + " 6dfdx";

    private final String fiveDigitHash;
    public RevertCommand(String hash) {
        this.fiveDigitHash = hash;
    }

    @Override
    public CommandResult execute(OptionalVersion<Version> version) throws IOException, ParseException {
        requireNonNull(version);
        Commit relevantCommit = version.revert(this.fiveDigitHash);
        if (relevantCommit.equals(Commit.NULL)) {
            return new CommandResult(REVERT_REQUEST_REJECTED, true, false);
        }
        return new CommandResult(REVERT_SUCCESSFUL_ACKNOWLEDGEMENT, false, false);
    }
}
