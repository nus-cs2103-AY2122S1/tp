package seedu.address.model.util;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.model.friend.exceptions.InvalidDayTimeException;

/**
 * Helper function for managing conversions of day and time
 */
public class DayTimeUtil {

    /**
     * Map of index of day to day in string form
     */
    private static final Map<Integer, String> INDEX_TO_DAY_MAP = Map.of(
            0, "Monday", 1, "Tuesday", 2, "Wednesday", 3, "Thursday",
            4, "Friday", 5, "Saturday", 6, "Sunday"
    );

    /**
     * Used for validation that String follows the 24 hour format without minutes eg, 0100, 0200, etc
     */
    private static final Pattern TIME_FORMAT = Pattern.compile("^([01][0-9]|2[0-3])(00)$");

    /**
     * Converts index to day in the form of a String
     *
     * @param index Index of day
     * @return Day in String form
     * @throws InvalidDayTimeException Invalid day input (must be within 0 - 6)
     */
    public static String getDayFromIndex(int index) throws InvalidDayTimeException {
        String day = INDEX_TO_DAY_MAP.get(index);
        if (day == null) {
            throw new InvalidDayTimeException("Invalid day index input");
        }
        return day;
    }

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

        final Matcher matcher = TIME_FORMAT.matcher(time.trim());
        if (!matcher.matches()) {
            throw new InvalidDayTimeException("Invalid time string input");
        }
        String indexTime = time.trim().substring(0, 2);
        return Integer.parseInt(indexTime);
    }


}
