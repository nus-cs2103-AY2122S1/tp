package seedu.programmer.logic.commands;

public class ExitCommandResult extends CommandResult {

    /**
     * Constructor for exit command result.
     *
     * @param feedbackToUser Message to be displayed to the user.
     */
    public ExitCommandResult(String feedbackToUser) {
        super(feedbackToUser);
    }
}
