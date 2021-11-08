package seedu.programmer.logic.commands;

/**
 * A CommandResult that requires handling in the UI to download the data in ProgrammerError as a csv file.
 */
public class DownloadCommandResult extends CommandResult {

    /**
     * Creates a DownloadCommandResult.
     *
     * @param feedbackToUser Message to show user.
     */
    public DownloadCommandResult(String feedbackToUser) {
        super(feedbackToUser);
    }
}
