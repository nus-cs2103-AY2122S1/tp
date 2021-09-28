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
     * Construst a TimeRange object.
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
     * Checks if the given time is within the time range.
     *
     * @param time The time to be tested.
     */
    public boolean isClashing(Time time) {
        return start.compareTo(time) <= 0 // same or later than start time
            && end.compareTo(time) > 0; // earlier than end time
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
        boolean isStartClashing = isClashing(other.start);
        boolean isEndClashing = isClashing(other.end);
        return isStartClashing || isEndClashing ? 0 : start.compareTo(other.start);
    }
}
