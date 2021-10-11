package seedu.address.commons.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Helper functions for handling datetime
 */
public class DateTimeUtil {
    private static final String YEAR_REGEX = "\\d{4}";
    private static final String MONTH_REGEX = "(0[1-9]|1[0-2])";
    private static final String DAY_REGEX = "(0[1-9]|[12][0-9]|3[01])";
    private static final String HOUR_REGEX = "([01][0-9]|2[0-4])";
    private static final String MINUTE_REGEX = "([0-5][0-9]|60)";
    private static final String VALIDATION_REGEX = "^" + YEAR_REGEX + "-" + MONTH_REGEX + "-" + DAY_REGEX
                                                      + " " + HOUR_REGEX + ":" + MINUTE_REGEX + "$";

    private static final String DATETIME_PARSE_FORMAT = "uuuu-MM-dd HH:mm";

    /**
     * Standardised datetime formatter which rejects invalid date and time.
     */
    public static final DateTimeFormatter FORMATTER =
        DateTimeFormatter.ofPattern(DATETIME_PARSE_FORMAT).withResolverStyle(ResolverStyle.STRICT);

    /**
     * Returns true if {@code test} is in the datetime format yyyy-MM-dd HH:mm.
     */
    public static boolean matchDateTimeRegex(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the datetime {@code test} is in the future.
     */
    public static boolean isFuture(LocalDateTime test) {
        return test.isAfter(LocalDateTime.now());
    }

    /**
     * Returns true if the datetime {@code test} is in the past.
     */
    public static boolean isPast(LocalDateTime test) {
        return test.isBefore(LocalDateTime.now());
    }

}
