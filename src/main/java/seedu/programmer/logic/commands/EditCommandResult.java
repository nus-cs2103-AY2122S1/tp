package seedu.programmer.logic.commands;

import java.util.Objects;

import seedu.programmer.model.student.Student;


public class EditCommandResult extends CommandResult {

    /** The application should show student's result. */
    private final Student edtitedStudent;

    /**
     * Constructs a {@code CommandResult} when there is a result to show.
     */
    public EditCommandResult(String feedbackToUser, Student edtitedStudent) {
        super(feedbackToUser);
        this.edtitedStudent = edtitedStudent;
    }

    public Student getEditedStudent() {
        return edtitedStudent;
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
                && edtitedStudent == otherCommandResult.edtitedStudent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFeedbackToUser(), edtitedStudent);
    }

}
