package nustracker.model.event;

import static java.util.Objects.requireNonNull;
import static nustracker.model.student.StudentId.isValidStudentId;

import nustracker.commons.util.AppUtil;
import nustracker.model.student.StudentId;

/**
 * Represents an Event's participant. Participants are stored by their student ID.
 */
public class Participant {

    public static final String MESSAGE_CONSTRAINTS =
            "Participants are identified by student ID."
                    + "Student ID should start with 'e', should be followed by 7 digits, and it should not be blank!";

    public final String studentId;

    /**
     * Constructs a {@code Id}.
     *
     * @param studentId A valid student ID.
     */
    public Participant(String studentId) {
        requireNonNull(studentId);
        AppUtil.checkArgument(isValidParticipant(studentId), MESSAGE_CONSTRAINTS);
        this.studentId = studentId;
    }

    /**
     * Returns true if a given string is a valid participant.
     */
    public static boolean isValidParticipant(String test) {
        return isValidStudentId(test);
    }

    /**
     * Gets the participant's student ID.
     * @return the StudentId object.
     */
    public StudentId getStudentId() {
        return new StudentId(studentId);
    }

    @Override
    public String toString() {
        return studentId;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same
                || (other instanceof Participant // instanceof handles nulls
                && studentId.equals(((Participant) other).studentId)); //Check internal attributes
    }

    @Override
    public int hashCode() {
        return studentId.hashCode();
    }
}
