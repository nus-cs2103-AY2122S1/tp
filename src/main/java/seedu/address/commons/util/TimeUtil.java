package seedu.address.commons.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility methods related to Times
 */
public class TimeUtil {

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static final LocalTime DEFAULT_MORNING_START_TIME = LocalTime.parse("10:00", TIME_FORMATTER);
    public static final LocalTime DEFAULT_MORNING_END_TIME = LocalTime.parse("16:00", TIME_FORMATTER);
    public static final LocalTime DEFAULT_AFTERNOON_START_TIME = LocalTime.parse("16:00", TIME_FORMATTER);
    public static final LocalTime DEFAULT_AFTERNOON_END_TIME = LocalTime.parse("22:00", TIME_FORMATTER);

    /**
     * Tests whether a string can be parsed to a time.
     * @param str The input string.
     * @return Whether a string can be parsed to a time.
     */
    public static boolean isValidTime(String str) {
        try {
            LocalTime.parse(str, TIME_FORMATTER);
        } catch (DateTimeParseException dateTimeParseException) {
            return false;
        }
        return true;
    }
}
