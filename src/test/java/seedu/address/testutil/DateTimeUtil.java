package seedu.address.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A utility class for DateTime.
 */
public class DateTimeUtil {
    /**
     * Returns a valid visit datetime string with a future date.
     */
    public static String getValidVisitString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
        LocalDateTime futureDateTime = LocalDateTime.now().plusDays(7);
        return futureDateTime.format(formatter);
    }

    /**
     * Returns a valid visit datetime string with a future date different from getValidVisitString().
     */
    public static String getValidVisitString2() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
        LocalDateTime futureDateTime = LocalDateTime.now().plusDays(8);
        return futureDateTime.format(formatter);
    }

    /**
     * Returns a valid last visit datetime string with a past date.
     */
    public static String getValidLastVisitString() {
        return "2021-10-10 12:00";
    }

    /**
     * Returns an invalid visit datetime string with a past date.
     */
    public static String getInvalidVisitString() {
        return getValidLastVisitString();
    }

    /**
     * Returns an invalid last visit datetime string with a future date.
     */
    public static String getInvalidLastVisitString() {
        return getValidVisitString();
    }
}
