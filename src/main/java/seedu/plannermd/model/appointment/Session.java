package seedu.plannermd.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Objects;

/**
 * Represents an Appointment's session in the plannermd.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class Session {

    public static final String MESSAGE_CONSTRAINTS = "Times should be of the format HH:MM "
            + "and adhere to the following constraints:\n"
            + "1. Hour must be between 0-23\n"
            + "2. Minute must be between 0-59.";

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("H:m")
            .withResolverStyle(ResolverStyle.SMART);

    public static final DateTimeFormatter DISPLAYED_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static final String MESSAGE_END_WITHIN_SAME_DAY = "The appointment should end within the same day.";

    public final LocalTime start;
    public final LocalTime end;
    public final Duration duration;

    /**
     * Constructs a {@code Session}.
     *
     * @param startTime A valid starting time.
     * @param duration  Duration of the session in minutes.
     */
    public Session(String startTime, Duration duration) {
        requireNonNull(startTime);
        requireNonNull(duration);

        checkArgument(isValidTime(startTime), MESSAGE_CONSTRAINTS);

        this.start = LocalTime.parse(startTime, TIME_FORMATTER);
        this.duration = duration;
        this.end = calculateEndTime(this.start, this.duration);
    }

    private LocalTime calculateEndTime(LocalTime start, Duration duration) {
        return start.plusMinutes(duration.duration);
    }

    /**
     * Returns if a given string is a valid time.
     *
     * @param test Input string representing a time.
     */
    public static boolean isValidTime(String test) {
        try {
            LocalTime.parse(test, TIME_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns if the starting and ending time are both in the same day.
     */
    public boolean isEndWithinSameDay() {
        return end.isAfter(start);
    }

    /**
     * Checks if the two Sessions are overlapping.
     * There is an overlap if both Sessions have at least one point of time in common.
     */
    public boolean isClash(Session otherSession) {
        LocalTime thisStartTime = this.start;
        LocalTime thisEndTime = this.end;
        LocalTime otherStartTime = otherSession.start;
        LocalTime otherEndTime = otherSession.end;

        boolean isThisStartTimeBetweenOtherSession =
                isTimeBetweenExclusiveEnd(thisStartTime, otherStartTime, otherEndTime);
        boolean isThisEndTimeBetweenOtherSession =
                isTimeBetweenExclusiveStart(thisEndTime, otherStartTime, otherEndTime);
        boolean isOtherStartTimeBetweenThisSession =
                isTimeBetweenExclusiveEnd(otherStartTime, thisStartTime, thisEndTime);
        boolean isOtherEndTimeBetweenThisSession =
                isTimeBetweenExclusiveStart(otherEndTime, thisStartTime, thisEndTime);

        return isThisStartTimeBetweenOtherSession || isThisEndTimeBetweenOtherSession
                || isOtherStartTimeBetweenThisSession || isOtherEndTimeBetweenThisSession;
    }

    /**
     * Compares this session start time to input session start time.
     * The comparison is based on the time-line position of the local times within a day.
     * It is "consistent with equals", as defined by Comparable.
     *
     * @param otherSession Given Session to compare to, not null.
     * @return The comparator value, negative if less, positive if greater, and zero if equal.
     */
    public int compareStartTimeTo(Session otherSession) {
        return start.compareTo(otherSession.start);
    }

    /**
     * Checks if the given time is between the start (exclusive) and the end (inclusive).
     *
     * @param time  Given time to be checked.
     * @param start Start time.
     * @param end   End time.
     * @return True if the given time is between the start and end times, false otherwise.
     */
    private static boolean isTimeBetweenExclusiveStart(LocalTime time, LocalTime start, LocalTime end) {
        return time.isAfter(start) && !time.isAfter(end);
    }

    /**
     * Checks if the given time is between the start (inclusive) and the end (exclusive).
     *
     * @param time  Given time to be checked.
     * @param start Start time.
     * @param end   End time.
     * @return True if the given time is between the start and end times, false otherwise.
     */
    private static boolean isTimeBetweenExclusiveEnd(LocalTime time, LocalTime start, LocalTime end) {
        return !time.isBefore(start) && time.isBefore(end);
    }

    /**
     * Returns the string representation of the formatted start time.
     */
    public String getFormattedStartTime() {
        return start.format(DISPLAYED_TIME_FORMATTER);
    }

    /**
     * Returns the string representation of the formatted end time.
     */
    public String getFormattedEndTime() {
        return end.format(DISPLAYED_TIME_FORMATTER);
    }

    /**
     * Returns the number of minutes in this Session.
     */
    public Integer getMinutes() {
        return duration.getMinutes();
    }

    /**
     * Returns the start time in input format.
     */
    public String toInputStringFormat() {
        return start.format(TIME_FORMATTER);
    }


    @Override
    public String toString() {
        return "Start: " + getFormattedStartTime() + "; End: " + getFormattedEndTime();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        }

        if (!(other instanceof Session)) { // instanceof handles nulls
            return false;
        }

        Session otherSession = (Session) other;
        return start.equals(otherSession.start) && duration.equals(otherSession.duration);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(start, duration);
    }
}
