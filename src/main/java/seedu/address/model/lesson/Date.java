package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * Represents a Lesson's date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date implements Comparable<Date> {

    public static final String MESSAGE_CONSTRAINTS = "Dates should be of the format dd MMM yyyy "
            + "and adhere to the following constraints:\n"
            + "1. dd and yyyy are numerical characters.\n"
            + "2. MMM are alphabetical characters. e.g. Jan, Feb, ..., Dec\n"
            + "3. Must be a valid date for the year.";

    /*
    Date strings should be formatted as dd MMM uuuu, where dd and uuuu are digits.
    and MMM are alphabets e.g. Jan, Mar, Nov, etc.
     */
    public static final String VALIDATION_REGEX = "^[0-2]?[0-9]\\s[a-zA-Z]{3}\\s[0-9]{4}";
    public static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("d MMM uuuu")
            .toFormatter(Locale.ENGLISH)
            .withResolverStyle(ResolverStyle.STRICT);

    public final String value;

    private final LocalDate localDate;
    /**
     * Constructs an {@code Date}.
     *
     * @param date A valid Date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = date.toUpperCase();
        localDate = LocalDate.parse(value, FORMATTER);
    }

    /**
     * Returns if a given string is a valid date.
     *
     * @param test The string to be tested.
     */
    public static boolean isValidDate(String test) {
        boolean isValid = true;
        try {
            LocalDate.parse(test, FORMATTER);
        } catch (DateTimeParseException e) {
            isValid = false;
        } finally {
            return test.matches(VALIDATION_REGEX) && isValid;
        }
    }

    /**
     * Returns the LocalDate representation of the date.
     *
     * @return LocalDate representation of date.
     */
    public LocalDate getLocalDate() {
        return localDate;
    }

    public DayOfWeek getDayOfWeek() {
        return localDate.getDayOfWeek();
    }

    /**
<<<<<<< HEAD
     * Update the lesson date to the same day on the most recent week
     * that has yet to be pass.
     *
     * @return newDate The date of the same day on the week that has yet to pass.
     */
    public Date updateDate() {
        /*
        Check the number of weeks between this date and the current date.
        Round up the number of weeks between the 2 dates.
         */
        int numberOfDaysInAWeek = 7;
        long numberOfDays = ChronoUnit.DAYS.between(getLocalDate(), LocalDate.now());
        long numberOfWeeks = ChronoUnit.WEEKS.between(getLocalDate(), LocalDate.now())
            + (numberOfDays % numberOfDaysInAWeek > 0 ? 1 : 0);

        if (numberOfWeeks <= 0) { // No need update if week has not passed
            return this;
        }
        Date newDate = new Date(getLocalDate().plusWeeks(numberOfWeeks).format(FORMATTER));
        return newDate;
    }

    /**
=======
>>>>>>> 5cc08fce492ac99d01795d6cbec8a990ace3f6cc
     * Check if the date has passed.
     *
     * @return true if date is earlier than now.
     */
    public boolean isOver() {
        return getLocalDate().isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && value.equals(((Date) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Compares this Date object with the other Date object.
     *
     * @param other The Date object to compare with.
     * @return 1, if this is later than other;0 if equal; -1 if this is earlier.
     */
    @Override
    public int compareTo(Date other) {
        return getLocalDate().compareTo(other.getLocalDate());
    }
}

