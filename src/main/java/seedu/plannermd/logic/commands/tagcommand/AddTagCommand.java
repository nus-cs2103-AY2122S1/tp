package seedu.plannermd.logic.commands.tagcommand;

/**
 * Represents an AddTag command with hidden internal logic and the ability to be executed.
 */
public abstract class AddTagCommand extends TagCommand {

    public static final String MESSAGE_NOT_ADDED = "A tag must be provided.";
}
