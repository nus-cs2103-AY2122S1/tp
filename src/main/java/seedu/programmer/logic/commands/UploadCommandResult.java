package seedu.programmer.logic.commands;

/**
 * Represents a CommandResult that requires handling in the UI to upload a csv file to ProgrammerError.
 */
public class UploadCommandResult extends CommandResult {

    /**
     * Class constructor for {@code UploadCommandResult}.
     *
     * @param feedbackToUser Message to show user.
     */
    public UploadCommandResult(String feedbackToUser) {
        super(feedbackToUser);
    }
}
