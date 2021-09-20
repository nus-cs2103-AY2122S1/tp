package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This is an EventTime class that represents the Time of an Event.
 */
public class EventTime {

    public static final String MESSAGE_CONSTRAINTS = "Time should be in 2359 format.";
    public static final String TIME_FORMAT = "HHmm";

    public final LocalTime time;

    /**
     * Constructors an {@code EventTime}
     *
     * @param time    A valid time.
     */
    public EventTime(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        this.time = LocalTime.parse(time, DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

    public EventTime() {
        this.time = LocalTime.MIN;
    }

    /**
     * Returns true if a given string is a valid time.
     *
     * @param test A String to be tested if it is a valid time.
     * @return A boolean indicating if the string is a valid time.
     */
    public static boolean isValidTime(String test) {
        boolean isValid;
        try {
            LocalTime.parse(test, DateTimeFormatter.ofPattern(TIME_FORMAT));
            isValid = true;
        } catch (DateTimeParseException e) {
            isValid = false;
        }
        return isValid;
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
