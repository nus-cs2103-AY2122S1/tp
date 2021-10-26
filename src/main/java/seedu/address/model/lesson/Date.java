package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.util.Set;

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
    Date strings should be formatted as d MMM uuuu, where d and uuuu are digits.
    and MMM are alphabets e.g. Jan, Mar, Nov, etc.
     */
    public static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("d MMM uuuu")
            .toFormatter(Locale.ENGLISH)
            .withResolverStyle(ResolverStyle.STRICT);

    public static final Date MAX_DATE = new Date(LocalDate.MAX.format(Date.FORMATTER));

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
        try {
            LocalDate.parse(test, FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
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
     * Updates the  date to the same day on the most recent week
     * that has yet to be pass.
     *
     * @return newDate The date of the same day on the week that has yet to pass.
     */
    public Date updateDate(Set<Date> datesToSkip) {
        LocalDate updatedDate = LocalDate.now().with(TemporalAdjusters.nextOrSame(getDayOfWeek()));

        while (datesToSkip.contains(new Date(updatedDate.format(FORMATTER)))) { // todo: improve design
            updatedDate = updatedDate.plusWeeks(1);
        }

        Date newDate = new Date(updatedDate.format(FORMATTER));
        return newDate;
    }

    /**
     * Returns the latest date on the given day of week before or equal to the date.
     *
     * @return prevDate The latest passed date on the given day on the week.
     */
    public Date getPreviousDate(DayOfWeek dayOfWeek) {
        LocalDate earlierDate = getLocalDate().isAfter(LocalDate.now()) ? LocalDate.now() : getLocalDate();
        LocalDate updatedDate = earlierDate.with(TemporalAdjusters.previousOrSame(dayOfWeek));
        Date prevDate = new Date(updatedDate.format(FORMATTER));
        return prevDate;
    }

    /**
     * Checks if the date has passed.
     *
     * @return true if date is earlier than now.
     */
    public boolean isOver() {
        return getLocalDate().isBefore(LocalDate.now());
    }

    public boolean isAfter(Date other) {
        return localDate.isAfter(other.localDate);
    }

    public boolean isSameDayOfWeek(Date other) {
        return localDate.getDayOfWeek().equals(other.getDayOfWeek());
    }

    /**
     * Checks is this date clashes with a recurring date.
     *
     * @param recurringStartDate
     * @return
     */
    public boolean isOnRecurringDate(Date recurringStartDate, Date recurringEndDate) {
        if (recurringStartDate.isAfter(this)) {
            return false;
        }
        if (!recurringStartDate.isSameDayOfWeek(this)) {
            return false;
        }

        if (!recurringEndDate.isAfter(this)) {
            return false;
        }

        return true;
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

