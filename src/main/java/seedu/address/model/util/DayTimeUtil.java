package seedu.address.model.util;

import static java.util.Objects.requireNonNull;

import seedu.address.model.friend.exceptions.InvalidDayTimeException;

/**
 * Helper function for managing conversions of day and time
 */
public class DayTimeUtil {

    private static final int SMALLEST_TIME = 0;
    private static final int LARGEST_TIME = 24;

    /**
     * Converts index to time string in 24 hour format
     *
     * @param index Index of time
     * @return Time in 24 hour format
     * @throws InvalidDayTimeException Invalid time input (must be within 0 - 24)
     */
    public static String getTimeFromIndex(int index) throws InvalidDayTimeException {
        if (index < SMALLEST_TIME || index > LARGEST_TIME) {
            throw new InvalidDayTimeException("Invalid time index input");
        }
        if (index < 10) {
            return "0" + index + "00";
        }
        return index + "00";
    }

    /**
     * Converts time string to time index
     *
     * @param time Time string in 24 hour format
     * @return Time index
     * @throws InvalidDayTimeException Invalid time string input
     */
    public static int getIndexFromTime(String time) throws InvalidDayTimeException {
        requireNonNull(time);
        try {
            int hour = Integer.parseInt(time);
            if (hour < SMALLEST_TIME || hour > LARGEST_TIME) {
                throw new InvalidDayTimeException("Invalid hour value, must be between 0 to 24 inclusive");
            }
            return hour;
        } catch (NumberFormatException e) {
            throw new InvalidDayTimeException("Invalid hour value");
        }
    }
}
