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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UploadCommandResult)) {
            return false;
        }

        UploadCommandResult otherCommandResult = (UploadCommandResult) other;
        return super.getFeedbackToUser().equals(otherCommandResult.getFeedbackToUser());
    }
}
