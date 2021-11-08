package seedu.programmer.logic.commands;


/**
 * Represents a CommandResult that requires handling in the UI to exit ProgrammerError.
 */
public class ExitCommandResult extends CommandResult {

    /**
     * Constructor for exit command result.
     */
    public ExitCommandResult(String feedbackToUser) {
        super(feedbackToUser);
    }
}
