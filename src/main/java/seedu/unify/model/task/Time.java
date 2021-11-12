package seedu.unify.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.unify.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's time in the UniFy.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class Time {

    public static final String MESSAGE_CONSTRAINTS = "Time should only contain numbers in the format of HH:MM, "
            + "where HH is from 00 to 23 and MM is from 00 to 59.";

    private static final String HOUR_REGEX = "([01][0-9]|2[0-3])";
    private static final String MINUTE_REGEX = "([0-5][0-9])";
    public static final String VALIDATION_REGEX = HOUR_REGEX + ":" + MINUTE_REGEX;


    //public final LocalTime time;
    public final String value;

    /**
     * Constructs a {@code Time}.
     *
     * @param time A valid time number.
     */
    public Time(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        value = time;
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && value.equals(((Time) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    private int getValueFromString(String x) {
        int value = 0;

        for (char c : x.toCharArray()) {
            value = value * 10 + (c - '0');
        }

        return value;
    }

    public int getTimeInMinutesFromStartOfDay() {
        return getValueFromString(value.substring(0, 2)) * 60 + getValueFromString(value.substring(3, 5));
    }

    public int subtractAsMinutes(Time time) {
        return this.getTimeInMinutesFromStartOfDay() - time.getTimeInMinutesFromStartOfDay();
    }
}
