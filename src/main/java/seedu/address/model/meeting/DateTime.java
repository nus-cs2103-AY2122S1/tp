package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a Meeting's datetime in NewAddressBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class DateTime implements Comparable<DateTime> {

    public static final String MESSAGE_CONSTRAINTS =
            "Meeting datetime must be valid and should be of the format YYYY-MM-DD HH:mm";
    public static final String VALIDATION_REGEX = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}";
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm").withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter USER_OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("EEE dd MMM yyyy, hh:mm a");

    public final String value;
    private final LocalDateTime dateTime;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param input A valid datetime.
     */
    public DateTime(String input) {
        requireNonNull(input);
        checkArgument(isValidDateTime(input), MESSAGE_CONSTRAINTS);
        dateTime = LocalDateTime.parse(input, FORMATTER);
        value = input;
    }

    /**
     * Gets the current datetime in user-friendly format
     * @return String representation of current datetime.
     */
    public static String getCurrentDateTime() {
        requireNonNull(USER_OUTPUT_FORMATTER);
        return LocalDateTime.now().format(USER_OUTPUT_FORMATTER);
    }

    /**
     * Returns true if a given string is a valid datetime.
     */
    public static boolean isValidDateTime(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            try {
                LocalDateTime.parse(test, FORMATTER);
            } catch (DateTimeParseException e) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Returns true if a given datetime is in the past.
     *
     * @return true if datetime is in the past, false otherwise.
     */
    public boolean isPastDateTime() {
        return dateTime.isBefore(LocalDateTime.now());
    }

    /**
     *
     * @return datetime in user-friendly format.
     */
    public String getUserFormat() {
        return dateTime.format(USER_OUTPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTime // instanceof handles nulls
                && value.equals(((DateTime) other).value))
                && dateTime.equals(((DateTime) other).dateTime); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(DateTime other) {
        return this.dateTime.compareTo(other.dateTime);
    }
}
