package seedu.address.commons.util;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Helper functions for handling days.
 */
public class DayUtil {
    /**
     * Returns the day of the week given the day number.
     *
     * @param dayNumber the index of the day to display.
     * @return String representing the day of the week.
     */
    public static String displayDay(int dayNumber) {
        return DayOfWeek.of(dayNumber).getDisplayName(TextStyle.FULL, Locale.getDefault());
    }

    /**
     * Returns the day of the week given the day number.
     *
     * @param day the day-of-week to display.
     * @return String representing the day of the week.
     */
    public static String displayDay(DayOfWeek day) {
        return day.getDisplayName(TextStyle.FULL, Locale.getDefault());
    }
}
