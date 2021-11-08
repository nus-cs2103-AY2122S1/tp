package safeforhall.model.event;

import static java.util.Objects.requireNonNull;
import static safeforhall.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTime implements Comparable<EventTime> {
    public static final String MESSAGE_CONSTRAINTS = "EventTime inputted has to be in HHmm format";
    public static final String DESC = "Time: ";

    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

    public final String eventTime;

    /**
     * Constructs a {@code EventTime}.
     *
     * @param time A valid time.
     */
    public EventTime(String time) {
        requireNonNull(time);
        checkArgument(isValidEventTime(time), MESSAGE_CONSTRAINTS);
        this.eventTime = time;
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidEventTime(String time) {
        try {
            LocalTime.parse(time, timeFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Converts the given {@code EventTime} to a {@code Localtime}.
     */
    public LocalTime toLocalTime() {
        return LocalTime.parse(eventTime, timeFormatter);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventTime // instanceof handles nulls
                && eventTime.equals(((EventTime) other).eventTime)); // state check
    }

    @Override
    public String toString() {
        return eventTime;
    }

    @Override
    public int compareTo(EventTime time) {
        return this.toLocalTime().compareTo(time.toLocalTime());
    }
}
