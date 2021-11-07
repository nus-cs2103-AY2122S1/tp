package seedu.programmer.logic.commands;

public class DownloadCommandResult extends CommandResult {

    /**
     * Creates a DownloadCommandResult.
     *
     * @param feedbackToUser message to show user
     * @param model current model
     */
    public DownloadCommandResult(String feedbackToUser) {
        super(feedbackToUser);
    }
}
