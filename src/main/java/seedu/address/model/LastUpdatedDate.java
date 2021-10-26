package seedu.address.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;
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

    private static final String VALIDATION_REGEX_DATE = "^[0-9]{4}-(0[1-9]|1[0-2])-[0-9]{2}";
    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("uuuu-MM-dd")
            .toFormatter(Locale.ENGLISH)
            .withResolverStyle(ResolverStyle.STRICT);

    public final String value;
    public final LocalDate date;

    /**
     * The constructor to update the LastUpdatedDate.
     */
    public LastUpdatedDate() {
        date = LocalDate.now();
        value = date.toString();
    }

    /**
     * Constructs an {@code LastAddedDate}.
     */
    public LastUpdatedDate(String lastUpdated) {
        date = LocalDate.parse(lastUpdated, FORMATTER);
        value = lastUpdated;
    }

    public LastUpdatedDate getLastUpdatedDate() {
        return this;
    }

    /**
     * Returns if a given string is a valid last added date.
     *
     * @param test The string to be tested.
     */
    public static boolean isValidLastUpdatedDate(String test) {
        boolean isValid = true;
        LocalDate testDate = null;
        try {
            testDate = LocalDate.parse(test, FORMATTER);
        } catch (DateTimeParseException e) {
            isValid = false;
        } finally {
            return test.matches(VALIDATION_REGEX_DATE)
                    && isValid //check would short circuit here and will not throw NullPointerException
                    && testDate.isAfter(LocalDate.now());
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
        return Objects.hash(value, date);
    }
}
