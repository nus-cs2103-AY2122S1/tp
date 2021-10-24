package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import seedu.address.logic.parser.exceptions.ParseException;

public class Date implements Comparable<Date> {
    public static final String MESSAGE_CONSTRAINTS =
            "The date should be specified in the yyyy-mm-dd format";

    public final String parsedDate;

    /**
     * Constructs a {@code Date}
     *
     * @param date A valid date for a task.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        parsedDate = date;
    }

    /**
     * Tests the validity of the date.
     *
     * @param test the date to be tested.
     * @return true if the date is valid, else false.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate testDate = LocalDate.parse(test);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return parsedDate;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Date
                && parsedDate.equals(((Date) other).parsedDate));
    }

    /**
     * Parse the given date.
     *
     * @return LocalDate object corresponding to the given time.
     * @throws DateTimeParseException if the date is incorrectly formatted.
     */
    public static LocalDate parseDate(String date) throws ParseException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new ParseException(".");
        }
    }

    @Override
    public int hashCode() {
        return parsedDate.hashCode();
    }

    @Override
    public int compareTo(Date o) {
        return LocalDate.parse(parsedDate).compareTo(LocalDate.parse(o.parsedDate));
    }
}
