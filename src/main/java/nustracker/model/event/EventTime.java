package nustracker.model.event;

import static java.util.Objects.requireNonNull;
import static nustracker.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event's Timing.
 */
public class EventTime {
    public static final String MESSAGE_CONSTRAINTS =
            "EventTimes should be in the format HHmm. E.g. 1600.";

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("hh:mm a");

    public final LocalTime eventTime;

    /**
     * Constructs an {@code EventTime}.
     *
     * @param time A valid timing.
     */
    public EventTime(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        eventTime = LocalTime.parse(time, INPUT_FORMATTER);
    }

    /**
     * Check if time input is valid.
     *
     * @param time Time input
     * @return True if valid; false if invalid.
     */
    public static boolean isValidTime(String time) {
        try {
            LocalTime.parse(time, INPUT_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Return the event time in the Input format.
     *
     * @return time in Input format
     */
    public String getEventTime() {
        return eventTime.format(INPUT_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventTime // instanceof handles nulls
                && eventTime.equals(((EventTime) other).eventTime)); // state check
    }

    @Override
    public String toString() {
        return eventTime.format(OUTPUT_FORMATTER);
    }

    @Override
    public int hashCode() {
        return eventTime.hashCode();
    }
}
