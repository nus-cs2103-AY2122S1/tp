package seedu.programmer.logic.commands;

/**
 * Represents a CommandResult that requires handling in the UI to open the HelpWindow.
 */
public class HelpCommandResult extends CommandResult {

    /** Help information should be shown to the user. */
    public HelpCommandResult(String feedbackToUser) {
        super(feedbackToUser);
    }
}
