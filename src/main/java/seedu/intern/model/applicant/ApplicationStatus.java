package seedu.intern.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.intern.commons.util.AppUtil.checkArgument;

/**
 * Represents an applicant's application status in Intern Watcher.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String)}
 */
public class ApplicationStatus {
    public enum Status {
        APPLIED("#980000"), RECEIVED("#984200"), SCHEDULED("#986900"),
        INTERVIEWED("#a77f03"), OFFERED("#0c8900"), ACCEPTED("#004b83"), REJECTED("#c4c4c4");

        private final String colour;

        private Status(String colour) {
            this.colour = colour;
        }

        private Status() {
            this.colour = "#000000";
        }
    }

    public static final Status DEFAULT_STATUS = Status.APPLIED;

    public static final String MESSAGE_CONSTRAINTS = "Application Status only supports the current statuses:"
            + Status.APPLIED.name() + ", " + Status.RECEIVED.name() + ", " + Status.SCHEDULED.name() + ", "
            + Status.INTERVIEWED.name() + ", " + Status.OFFERED.name() + ", " + Status.ACCEPTED.name() + ", "
            + Status.REJECTED.name();
    public static final String VALIDATION_REGEX = Status.APPLIED.name() + "|" + Status.RECEIVED.name() + "|"
            + Status.SCHEDULED.name() + "|" + Status.INTERVIEWED.name() + "|" + Status.OFFERED.name() + "|"
            + Status.ACCEPTED.name() + "|" + Status.REJECTED.name();
    public final Status value;

    /**
     * Constructs a {@code ApplicationStatus}.
     *
     * @param status A valid status string.
     */
    public ApplicationStatus(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        value = Status.valueOf(status);
    }

    /**
     * Constructs a {@code ApplicationStatus}.
     *
     * @param status A valid status.
     */
    public ApplicationStatus(Status status) {
        requireNonNull(status);
        value = status;
    }

    /**
     * Constructs a {@code ApplicationStatus}.
     */
    public ApplicationStatus() {
        value = DEFAULT_STATUS;
    }

    /**
     * Returns true if a given string is a valid status.
     */
    public static boolean isValidStatus(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the colour each application status should be set to.
     */
    public String getColour() {
        return value.colour;
    }

    @Override
    public String toString() {
        return value.name();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ApplicationStatus // instanceof handles nulls
                && value == ((ApplicationStatus) other).value); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
