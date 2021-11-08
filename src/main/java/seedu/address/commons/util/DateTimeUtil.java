package seedu.address.commons.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * Helper functions for handling dates and times.
 */
public class DateTimeUtil {

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";

    private static final long ONE_DAY_MILLISECONDS = 24 * 60 * 60 * 1000;
    private static final String TIMESTAMP_STUB = "1609459200000";

    /**
     * Returns current timestamp in string format.
     */
    public static String generateTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * Returns timestamp stub in string format.
     */
    public static String generateTimestampStub() {
        return TIMESTAMP_STUB;
    }

    /**
     * Returns true if the {@code date} contains the {@code timestamp}.
     */
    public static boolean isDateContainsTimestamp(Date date, long timestamp) {
        Timestamp dateToTimestamp = new Timestamp(date.getTime());
        long start = dateToTimestamp.getTime();
        long end = dateToTimestamp.getTime() + ONE_DAY_MILLISECONDS;
        return timestamp >= start && timestamp < end;
    }

    /**
     * Returns date converted by {@code timestamp}.
     */
    public static Date timestampToDate(long timestamp) {
        Timestamp stamp = new Timestamp(timestamp);
        return new Date(stamp.getTime());
    }

    /**
     * Returns String of dateTime to dateTime format.
     *
     * @param dateTime DateTime in String format.
     * @return DateTime format.
     * @throws DateTimeParseException If the dateTime cannot be parsed.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        return LocalDateTime.parse(dateTime, formatter);
    }
}
