package seedu.programmer.logic.commands;

public class ExitCommandResult extends CommandResult {

    /** The application should exit. */
    public ExitCommandResult(String feedbackToUser) {
        super(feedbackToUser);
    }
}
