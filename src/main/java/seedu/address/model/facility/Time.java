package seedu.address.model.facility;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Facility's time.
 */
public class Time {

    public static final String MESSAGE_CONSTRAINTS =
            "Time should only contain numbers and it should be exactly 4 digits long";
    public static final String VALIDATION_REGEX = "\\d{4}";
    public final String time;

    /**
     * Creates a Time object with the specified time value.
     *
     * @param time A valid time.
     */
    public Time(String time) {
        requireNonNull(time);
        this.time = time;
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj == this)
                || (obj instanceof Time
                && time.equals(((Time) obj).time));
    }

    @Override
    public String toString() {
        return time;
    }
}
