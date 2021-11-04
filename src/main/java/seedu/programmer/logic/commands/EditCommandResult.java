package seedu.programmer.logic.commands;

import java.util.Objects;

import seedu.programmer.model.student.Student;


public class EditCommandResult extends CommandResult {

    /** The application should show student's result. */
    private final Student editedStudent;

    /**
     * Constructs a {@code CommandResult} when there is a result to show.
     */
    public EditCommandResult(String feedbackToUser, Student editedStudent) {
        super(feedbackToUser);
        this.editedStudent = editedStudent;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommandResult)) {
            return false;
        }

        EditCommandResult otherCommandResult = (EditCommandResult) other;
        return getFeedbackToUser().equals(otherCommandResult.getFeedbackToUser())
                && editedStudent == otherCommandResult.editedStudent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFeedbackToUser(), editedStudent);
    }

}
