package seedu.plannermd.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.commons.util.AppUtil.checkArgument;

/**
 * Represents a Session's duration of an Appointment.
 * Guarantees: immutable; is valid as declared in {@link #isValidDuration(Integer)}
 */
public class Duration {

    public static final String MESSAGE_CONSTRAINTS = "The duration should be an integer between 1-60 minutes.";

    public static final int DEFAULT_DURATION = 5;

    public final Integer duration;

    private Duration() {
        this.duration = DEFAULT_DURATION;
    }

    /**
     * Constructs a {@code Duration}.
     *
     * @param duration Duration in minutes.
     */
    public Duration(Integer duration) {
        requireNonNull(duration);
        checkArgument(isValidDuration(duration), MESSAGE_CONSTRAINTS);
        this.duration = duration;
    }

    /**
     * Returns true if a given integer is a valid duration.
     * A valid duration is between 1-60 minutes.
     */
    public static boolean isValidDuration(Integer duration) {
        return duration >= 1 && duration <= 60;
    }

    /**
     * Factory method to return a default duration.
     */
    public static Duration getDefaultDuration() {
        return new Duration();
    }

    @Override
    public String toString() {
        return duration + " minutes";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Duration // instanceof handles nulls
                && duration.equals(((Duration) other).duration)); // state check
    }

    @Override
    public int hashCode() {
        return duration.hashCode();
    }
}
