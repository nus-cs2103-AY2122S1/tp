package seedu.address.model.lesson;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Lesson's time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class Time {

    public static final String MESSAGE_CONSTRAINTS = "Time should be of the format HH:mm "
            + "and adhere to the following constraints:\n"
            + "1. Both HH and mm are numerical characters.\n"
            + "2. HH < 24 and mm < 60";

    public static final String VALIDATION_REGEX = "^[0-2][0-4]\\s[0-6][0-9]";
    public static final DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_TIME;

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
     * Returns if a given string is a valid date.
     */
    public static boolean isValidTime(String test) {
        boolean isValid = true;
        try {
            LocalDate.parse(test, formatter);
        } catch (DateTimeParseException e) {
            isValid = false;
        } finally {
            return test.matches(VALIDATION_REGEX) && isValid;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.lesson.Time // instanceof handles nulls
                && value.equals(((seedu.address.model.lesson.Time) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

