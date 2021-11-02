package seedu.address.commons.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility methods related to Times
 */
public class TimeUtil {

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static LocalTime defaultMorningStartTime = LocalTime.parse("10:00", TIME_FORMATTER);
    private static LocalTime defaultMorningEndTime = LocalTime.parse("16:00", TIME_FORMATTER);
    private static LocalTime defaultAfternoonStartTime = LocalTime.parse("16:00", TIME_FORMATTER);
    private static LocalTime defaultAfternoonEndTime = LocalTime.parse("22:00", TIME_FORMATTER);

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

    /**
     * Update the default timing fields based on a LocalTime[] of new Timings.
     *
     * @param newTimings a LocalTime[] containing the new timings.
     */
    public static void updateTimings(LocalTime[] newTimings) {
        defaultMorningStartTime = newTimings[0];
        defaultMorningEndTime = newTimings[1];
        defaultAfternoonStartTime = newTimings[2];
        defaultAfternoonEndTime = newTimings[3];
    }

    /**
     * Returns the default morning shift start time.
     * @return default morning shift start time.
     */
    public static LocalTime getDefaultMorningStartTime() {
        return defaultMorningStartTime;
    }

    /**
     * Returns the default morning shift end time.
     * @return default morning shift end time.
     */
    public static LocalTime getDefaultMorningEndTime() {
        return defaultMorningEndTime;
    }

    /**
     * Returns the default afternoon shift start time.
     * @return default afternoon shift start time.
     */
    public static LocalTime getDefaultAfternoonStartTime() {
        return defaultAfternoonStartTime;
    }

    /**
     * Returns the default afternoon shift end time.
     * @return default afternoon shift end time.
     */
    public static LocalTime getDefaultAfternoonEndTime() {
        return defaultAfternoonEndTime;
    }
}
