package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents a Lesson's TimeRange in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTimeRange(Time, Time)}
 */
public class TimeRange implements Comparable<TimeRange> {
    public static final String MESSAGE_CONSTRAINTS = "End time cannot be earlier than start time.";

    private final Time start;
    private final Time end;

    /**
     * Constructs a TimeRange object.
     *
     * @param start Start of the range.
     * @param end End of the range.
     */
    public TimeRange(Time start, Time end) {
        requireNonNull(start);
        requireNonNull(end);
        this.start = start;
        this.end = end;
        checkArgument(isValidTimeRange(start, end), MESSAGE_CONSTRAINTS);
    }

    public Time getStart() {
        return start;
    }

    public Time getEnd() {
        return end;
    }

    /**
     * Checks if the start is earlier than the end.
     *
     * @param start Start of the range.
     * @param end End of the range.
     * @return True if start is earlier than or same time as end.
     */
    public static boolean isValidTimeRange(Time start, Time end) {
        return end.compareTo(start) >= 0;
    }

    /**
     * Checks if the given start time is within the time range.
     *
     * @param time The time to be tested.
     */
    private boolean isStartClashing(Time time) {
        return start.compareTo(time) <= 0 // same or later than start time
            && end.compareTo(time) > 0; // earlier than end time
    }

    /**
     * Checks if the given end time is within the time range.
     *
     * @param time The time to be tested.
     */
    private boolean isEndClashing(Time time) {
        return start.compareTo(time) < 0 // later than start time
            && end.compareTo(time) > 0; // earlier than end time
    }

    /**
     * Checks if the given time range overlaps with this time range.
     *
     * @param other The TimeRange to be tested.
     */
    public boolean isClashing(TimeRange other) {
        return isStartClashing(other.start) || isEndClashing(other.end);
    }

    @Override
    public String toString() {
        return start.toString() + " to " + end.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TimeRange) // instanceof handles nulls
            && start.equals(((TimeRange) other).start)
            && end.equals(((TimeRange) other).end); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    /**
     * Compares this TimeRange object with the other TimeRange object.
     *
     * @param other The TimeRange object to compare with.
     * @return 1, if this is later than other;0 if clashing; -1 if this is earlier.
     */
    @Override
    public int compareTo(TimeRange other) {
        /*
        start.compareTo(other.start) will not return 0 because it will
        be labelled as clashing with this time range.
         */
        return isClashing(other) ? 0 : start.compareTo(other.start);
    }
}
