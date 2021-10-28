package seedu.address.logic.commands.tasks;

import seedu.address.logic.commands.Command;

public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "-d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    
}
