package seedu.programmer.logic.commands;

public class UploadCommandResult extends CommandResult {

    /**
     * Creates an UploadCommandResult.
     *
     * @param feedbackToUser message to show user
     * @param model current model
     */
    public UploadCommandResult(String feedbackToUser) {
        super(feedbackToUser);
    }
}
