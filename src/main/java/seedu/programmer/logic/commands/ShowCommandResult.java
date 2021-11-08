package seedu.programmer.logic.commands;

import java.util.Objects;

import seedu.programmer.model.student.Student;

/**
 * Represents a CommandResult that requires handling in the UI to show a student's lab results.
 */
public class ShowCommandResult extends CommandResult {

    /**
     * The application should show student's result.
     */
    private final Student target;

    /**
     * Class constructor for a {@code ShowCommandResult} when there is a student to show.
     */
    public ShowCommandResult(String feedbackToUser, Student target) {
        super(feedbackToUser);
        this.target = target;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ShowCommandResult)) {
            return false;
        }

        ShowCommandResult otherCommandResult = (ShowCommandResult) other;
        return getFeedbackToUser().equals(otherCommandResult.getFeedbackToUser())
                && target.equals(otherCommandResult.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFeedbackToUser(), target);
    }

}
