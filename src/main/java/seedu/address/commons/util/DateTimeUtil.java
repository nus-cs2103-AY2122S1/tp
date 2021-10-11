package seedu.address.commons.util;

public class DateTimeUtil {
    public static final String YEAR_REGEX = "\\d{4}";
    public static final String MONTH_REGEX = "(0[1-9]|1[0-2])";
    public static final String DAY_REGEX = "(0[1-9]|[12][0-9]|3[01])";
    public static final String HOUR_REGEX = "([01][0-9]|2[0-4])";
    public static final String MINUTE_REGEX = "([0-5][0-9]|60)";
    public static final String VALIDATION_REGEX = "^" + YEAR_REGEX + "-" + MONTH_REGEX + "-" + DAY_REGEX
                                                      + " " + HOUR_REGEX + ":" + MINUTE_REGEX + "$";

    /**
     * Returns true if {@code test} is in the datetime format yyyy-MM-dd HH:mm.
     */
    public static boolean matchDateTimeRegex(String test) {
        return test.matches(VALIDATION_REGEX);
    }

}
