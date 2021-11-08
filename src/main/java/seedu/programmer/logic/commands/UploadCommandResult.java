package seedu.programmer.logic.commands;

/**
 * A CommandResult that requires handling in the UI.
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
