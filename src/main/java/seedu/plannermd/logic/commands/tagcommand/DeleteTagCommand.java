package seedu.plannermd.logic.commands.tagcommand;

import seedu.plannermd.logic.commands.Command;

/**
 * Represents a DeleteTag command with hidden internal logic and the ability to be executed.
 */
public abstract class DeleteTagCommand extends Command {

    public static final String COMMAND_WORD = "tag -d";
}
