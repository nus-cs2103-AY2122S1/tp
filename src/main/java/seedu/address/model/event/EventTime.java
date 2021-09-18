package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTime {
    public static final String MESSAGE_CONSTRAINTS = "Time should be in 2359 format.";

    public final LocalTime time;

    /**
     * Constructors an {@code EventTime}
     *
     * @param time    A valid time.
     */
    public EventTime(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        this.time = LocalTime.parse(time, DateTimeFormatter.ofPattern("HHmm"));
    }

    public EventTime() {
        this.time = LocalTime.MIN;
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        // There must be a better way to do this.
        try {
            LocalTime.parse(test, DateTimeFormatter.ofPattern("HHmm"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.time.equals(LocalTime.MIN) ? "" : this.time.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof EventTime
                && time.equals(((EventTime) other).time));
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }
}
