package seedu.programmer.logic.commands;

/**
 * Represents a CommandResult that requires handling in the UI to open the HelpWindow.
 */
public class HelpCommandResult extends CommandResult {

    /**
     * Constructor for help command result.
     *
     * @param feedbackToUser Message to be displayed to the user.
     */
    public HelpCommandResult(String feedbackToUser) {
        super(feedbackToUser);
    }
}
