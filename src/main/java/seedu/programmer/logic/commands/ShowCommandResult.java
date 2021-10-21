package seedu.programmer.logic.commands;

import java.util.Objects;

import seedu.programmer.model.student.Student;


public class ShowCommandResult extends CommandResult {

    /** The application should show student's result. */
    private final Student target;

    /**
     * Constructs a {@code CommandResult} when there is a result to show.
     */
    public ShowCommandResult(String feedbackToUser, Student target) {
        super(feedbackToUser);
        this.target = target;
    }

    public Student getTarget() {
        return target;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ShowCommandResult)) {
            return false;
        }

        ShowCommandResult otherCommandResult = (ShowCommandResult) other;
        return getFeedbackToUser().equals(otherCommandResult.getFeedbackToUser())
                && target == otherCommandResult.target;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFeedbackToUser(), target);
    }

}
