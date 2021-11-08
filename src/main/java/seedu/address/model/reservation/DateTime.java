package seedu.address.model.reservation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import seedu.address.commons.util.DateTimeUtil;

/**
 * Represents a Reservation's dateTime in the ezFoodie.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}.
 */
public class DateTime {

    /**
     * Stands for message constraints of reservation date time.
     */
    public static final String MESSAGE_CONSTRAINTS =
            "Reservations should be valid date time " + DateTimeUtil.DATE_TIME_PATTERN;
    /**
     * Stands for reservation value.
     */
    public final String value;

    /**
     * Constructs a {@code Reservation}.
     *
     * @param dateTime a valid date time.
     */
    public DateTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        value = dateTime;
    }

    /**
     * Returns true if a given string is a valid date time.
     */
    public static boolean isValidDateTime(String test) {
        try {
            DateTimeUtil.parseDateTime(test);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns LocalDateTime value of date time.
     */
    public LocalDateTime getLocalDateTimeValue() {
        return DateTimeUtil.parseDateTime(value);
    }

    /**
     * Overrides the toString method.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Overrides the equals method.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTime // instanceof handles nulls
                && value.equals(((DateTime) other).value)); // state check
    }

    /**
     * Overrides the hashCode method.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
