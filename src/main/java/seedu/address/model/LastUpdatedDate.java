package seedu.address.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * The last time all lesson's outstanding fees were updated and checked.
 * Similar to System update date.
 */
public class LastUpdatedDate {
    public static final String MESSAGE_CONSTRAINTS = "Dates should be of the format uuuu-MM-dd "
            + "and adhere to the following constraints:\n"
            + "1. dd, MM and uuuu are numerical characters.\n"
            + "2. Must be a valid date for the year.\n"
            + "3. Must be a date that has not passed.";

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public final String value;
    public final LocalDateTime dateTime;

    /**
     * The constructor to update the LastUpdatedDate.
     */
    public LastUpdatedDate() {
        dateTime = LocalDateTime.now();
        value = dateTime.toString();
    }

    /**
     * Constructs an {@code LastAddedDate}.
     */
    public LastUpdatedDate(String lastUpdated) {
        dateTime = LocalDateTime.parse(lastUpdated, FORMATTER);
        value = lastUpdated;
    }

    /**
     * Return immutable LastUpdatedDate.
     */
    public LastUpdatedDate getLastUpdatedDate() {
        return new LastUpdatedDate(value);
    }

    /**
     * Returns if a given string is a valid last added date.
     *
     * @param test The string to be tested.
     */
    public static boolean isValidLastUpdatedDateTime(String test) {
        boolean isValid = true;
        LocalDateTime testDate = null;
        try {
            testDate = LocalDateTime.parse(test, FORMATTER);
        } catch (DateTimeParseException e) {
            isValid = false;
        } finally {
            return isValid //check would short circuit here and will not throw NullPointerException
                    && testDate.isBefore(LocalDateTime.now());
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LastUpdatedDate // instanceof handles nulls
                && value.equals(((LastUpdatedDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, dateTime);
    }
}
