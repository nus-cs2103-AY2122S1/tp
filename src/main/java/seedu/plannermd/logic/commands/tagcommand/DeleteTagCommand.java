package seedu.plannermd.logic.commands.tagcommand;

/**
 * Represents a DeleteTag command with hidden internal logic and the ability to be executed.
 */
public abstract class DeleteTagCommand extends TagCommand {

    public static final String COMMAND_WORD = "tag -d";

    public static final String MESSAGE_INVALID_TAG = "The tag does not exist.";
}
