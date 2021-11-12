package seedu.address.model.facility;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Facility's time.
 */
public class Time {

    public static final String MESSAGE_CONSTRAINTS =
            "Time should be a valid time in the 24h format (HHmm).";
    public static final String VALIDATION_REGEX = "^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$";
    public final String time;
    private final DateTimeFormatter twentyFourHourFormatter = DateTimeFormatter.ofPattern("HHmm");
    private final DateTimeFormatter meridiemFormatter = DateTimeFormatter.ofPattern("hh:mm a");

    /**
     * Creates a Time object with the specified time value.
     *
     * @param time A valid time.
     */
    public Time(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
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
        return LocalTime.parse(time, twentyFourHourFormatter).format(meridiemFormatter);
    }
}
