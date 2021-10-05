package seedu.address.model.facility;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Facility's time.
 */
public class Time {
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
