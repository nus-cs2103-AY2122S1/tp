package seedu.address.commons.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.model.person.Period;

/**
 * Utility methods related to Times
 */
public class DateTimeUtil {

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static LocalTime defaultMorningStartTime = LocalTime.parse("10:00", TIME_FORMATTER);
    private static LocalTime defaultMorningEndTime = LocalTime.parse("16:00", TIME_FORMATTER);
    private static LocalTime defaultAfternoonStartTime = LocalTime.parse("16:00", TIME_FORMATTER);
    private static LocalTime defaultAfternoonEndTime = LocalTime.parse("22:00", TIME_FORMATTER);
    private static Period displayedPeriod = Period.oneWeekFrom(LocalDate.now());

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
     * Update the Period that represents the week that is displayed on the GUI.
     *
     * @param newPeriod new Period being displayed on the GUI.
     */
    public static void updateDisplayedPeriod(Period newPeriod) {
        displayedPeriod = newPeriod;
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

    /**
     * Creates an array of {@code LocalDate} of size 2, that includes the start and end date
     *  from {@code displayedPeriod}.
     *
     */
    public static LocalDate[] getDisplayedDateArray() {
        return new LocalDate[]{displayedPeriod.getStartDate(), displayedPeriod.getStartDate().plusDays(6)};
    }

    /**
     * Gets the current period being displayed.
     */
    public static Period getDisplayedPeriod() {
        return displayedPeriod;
    }
}
