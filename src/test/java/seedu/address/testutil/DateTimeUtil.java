package seedu.address.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A utility class for DateTime.
 */
public class DateTimeUtil {
    public static final DateTimeFormatter FORMATTER = seedu.address.commons.util.DateTimeUtil.FORMATTER;

    /**
     * Returns a formatted string with datetime 7 days from now.
     */
    public static String getInputStringSevenDaysFromNow() {
        LocalDateTime futureDateTime = LocalDateTime.now().plusDays(7);
        return futureDateTime.format(FORMATTER);
    }

    /**
     * Returns a formatted string with datetime 8 days from now.
     */
    public static String getInputStringEightDaysFromNow() {
        LocalDateTime futureDateTime = LocalDateTime.now().plusDays(8);
        return futureDateTime.format(FORMATTER);
    }

    /**
     * Returns a formatted string with datetime 30 days from now.
     */
    public static String getInputStringThirtyDaysFromNow() {
        LocalDateTime futureDateTime = LocalDateTime.now().plusDays(30);
        return futureDateTime.format(FORMATTER);
    }

    /**
     * Returns a formatted string with datetime over 30 days from now.
     */
    public static String getInputStringOverThirtyDaysFromNow() {
        LocalDateTime futureDateTime = LocalDateTime.now().plusDays(30).plusMinutes(1);
        return futureDateTime.format(FORMATTER);
    }

    /**
     * Returns a formatted string with datetime 7 days before now.
     */
    public static String getInputStringSevenDaysBeforeNow() {
        LocalDateTime pastDateTime = LocalDateTime.now().minusDays(7).plusMinutes(1);
        return pastDateTime.format(FORMATTER);
    }

    /**
     * Returns a formatted string with datetime 8 days before now.
     */
    public static String getInputStringEightDaysBeforeNow() {
        LocalDateTime pastDateTime = LocalDateTime.now().minusDays(8);
        return pastDateTime.format(FORMATTER);
    }

    /**
     * Returns a formatted string with datetime 30 days before now.
     */
    public static String getInputStringThirtyDaysBeforeNow() {
        LocalDateTime futureDateTime = LocalDateTime.now().minusDays(30).plusMinutes(1);
        return futureDateTime.format(FORMATTER);
    }

    /**
     * Returns a formatted string with datetime over 30 days before now.
     */
    public static String getInputStringOverThirtyDaysBeforeNow() {
        LocalDateTime futureDateTime = LocalDateTime.now().minusDays(30);
        return futureDateTime.format(FORMATTER);
    }

    /**
     * Returns an invalid visit datetime string with a past date.
     */
    public static String getInvalidVisitString() {
        return getInputStringSevenDaysBeforeNow();
    }

    /**
     * Returns an invalid last visit datetime string with a future date.
     */
    public static String getInvalidLastVisitString() {
        return getInputStringSevenDaysFromNow();
    }
}
