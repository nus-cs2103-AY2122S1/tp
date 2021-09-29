package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a Lesson's time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class Time implements Comparable<Time> {

    public static final String MESSAGE_CONSTRAINTS = "Time should be of the format HH:mm "
            + "and adhere to the following constraints:\n"
            + "1. Both HH and mm are numerical characters.\n"
            + "2. HH < 24 and mm < 60";

    /*
    Specifies that the first 2 digits must be < 24
    and the second last digit must be < 6.
     */
    public static final String VALIDATION_REGEX = "^(([0-1]\\d)|(2[0-3])):([0-5]\\d)$";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME
        .withResolverStyle(ResolverStyle.STRICT);

    public final String value;

    /**
     * Constructs an {@code Time}.
     *
     * @param time A valid Time.
     */
    public Time(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        value = time;
    }

    /**
     * Returns if a given string is a valid time.
     *
     * @param test The string to be tested.
     * @return True is test is valid.
     */
    public static boolean isValidTime(String test) {
        boolean isValid = true;
        try {
            LocalTime.parse(test, FORMATTER);
        } catch (DateTimeParseException e) {
            isValid = false;
        } finally {
            return test.matches(VALIDATION_REGEX) && isValid;
        }
    }

    /**
     * Get the LocalTime representation of the time string.
     *
     * @return {@code LocalTime} of the time string.
     */
    public LocalTime getLocalTime() {
        return LocalTime.parse(value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && value.equals(((Time) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Compares this Time object with the other time object.
     *
     * @param other The Time object to compare with.
     * @return 1, if this is later than other;0 if equal; -1 if this is earlier.
     */
    @Override
    public int compareTo(Time other) {
        return getLocalTime().compareTo(other.getLocalTime());
    }
}

