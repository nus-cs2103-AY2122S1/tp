package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.util.Objects;

/**
 * Represents a timeslot, for example 10pm - 12pm
 */
public class Timeslot {

    public static final String MESSAGE_CONSTRAINTS =
            "Timings must be in the 24H format HH:MM and the start time must be before the end time";

    public static final String VALIDATION_REGEX = "(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])";

    // timings
    private final LocalTime startTime;
    private final LocalTime endTime;

    /**
     * Constructs a {@code Timeslot}
     *
     * @param startTimeStr valid String representation of the start time
     * @param endTimeStr valid String representation of the end time
     */
    public Timeslot(String startTimeStr, String endTimeStr) {
        requireNonNull(startTimeStr);
        requireNonNull(endTimeStr);
        checkArgument(isValidTimeslot(startTimeStr, endTimeStr), MESSAGE_CONSTRAINTS);
        startTime = LocalTime.parse(startTimeStr);
        endTime = LocalTime.parse((endTimeStr));
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Checks if string representation is valid
     *
     * @param startTimeStr HH:MM start time
     * @param endTimeStr HH:MM end time
     * @return true if both string representations are valid and the start time is before end time
     */
    public static boolean isValidTimeslot(String startTimeStr, String endTimeStr) {
        if (!startTimeStr.matches(VALIDATION_REGEX) || !endTimeStr.matches(VALIDATION_REGEX)) {
            return false;
        }

        LocalTime startTime = LocalTime.parse(startTimeStr);
        LocalTime endTime = LocalTime.parse((endTimeStr));
        return startTime.isBefore(endTime);
    }

    /**
     * Checks if timings overlap, for example one timeslot starts while another one is ongoing
     * @param other other timeslot to check against
     * @return true if timings overlap
     */
    public boolean doTimingsOverlap(Timeslot other) {
        return startTime.isBefore(other.endTime)
                && other.startTime.isBefore(endTime);
    }

    @Override
    public String toString() {
        return String.format("%s-%s", startTime, endTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Timeslot // instanceof handles nulls
                && startTime.equals(((Timeslot) other).startTime)
                && endTime.equals(((Timeslot) other).endTime)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }
}
