package seedu.address.commons.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



/**
 * A class for getting current Date time in (E, MMM dd yyyy HH:mm) format.
 */
public class DateUtil {
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm");

    /**
     * Returns the String representation of current date and time.
     *
     * @return String representation of current date and time.
     */
    public static String getCurrentDateTime() {
        return TIME_FORMATTER.format(LocalDateTime.now());
    }

}
