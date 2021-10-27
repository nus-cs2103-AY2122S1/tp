package seedu.address.model.util;

import static java.util.Objects.requireNonNull;

import java.util.regex.Pattern;

import seedu.address.model.friend.exceptions.InvalidDayTimeException;

/**
 * Helper function for managing conversions of day and time
 */
public class DayTimeUtil {
    /**
     * Used for validation that String follows the 24 hour format without minutes eg, 0100, 0200, etc
     */
    private static final Pattern TIME_FORMAT = Pattern.compile("^([01][0-9]|2[0-3])(00)$");

    /**
     * Converts index to time string in 24 hour format
     *
     * @param index Index of time
     * @return Time in 24 hour format
     * @throws InvalidDayTimeException Invalid time input (must be within 0 - 23)
     */
    public static String getTimeFromIndex(int index) throws InvalidDayTimeException {
        if (index < 0 || index > 23) {
            throw new InvalidDayTimeException("Invalid time index input");
        }
        if (index < 10) {
            return "0" + index + "00";
        }
        return index + "00";
    }

    /**
     * Converts time string in 24 hour format to time index
     *
     * @param time Time string in 24 hour format
     * @return Time index
     * @throws InvalidDayTimeException Invalid time string input
     */
    public static int getIndexFromTime(String time) throws InvalidDayTimeException {
        requireNonNull(time);
        try {
            int hour = Integer.parseInt(time);
            if (hour < 0 || hour > 24) {
                throw new InvalidDayTimeException("Invalid hour value, must be between 0 to 24 inclusive");
            }
            return hour;
        } catch (NumberFormatException e) {
            throw new InvalidDayTimeException("Invalid hour value");
        }
    }
}
