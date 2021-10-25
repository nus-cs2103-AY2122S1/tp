package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Date implements Comparable<Date> {
    public static final String MESSAGE_CONSTRAINTS =
            "The date should be specified in the yyyy-mm-dd format";

    public final String dateString;
    public final LocalDate date;

    /**
     * Constructs a {@code Date}
     *
     * @param dateString A valid dateString for a task.
     */
    public Date(String dateString) {
        requireNonNull(dateString);
        checkArgument(isValidDate(dateString), MESSAGE_CONSTRAINTS);
        this.dateString = dateString;
        this.date = parseDate(dateString);
    }

    /**
     * Parses the given dateString String.
     *
     * @return LocalDate object corresponding to the given time.
     * @throws DateTimeParseException if the dateString is incorrectly formatted.
     */
    public static LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString);
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

    /**
     * Formats LocalDate date into the "MMM d yyyy" format
     *
     * @param date LocalDate to be formatted.
     * @return String formatted LocalDate.
     */
    public static String getFormattedDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return getFormattedDate(date);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Date
                && dateString.equals(((Date) other).dateString));
    }

    @Override
    public int hashCode() {
        return dateString.hashCode();
    }

    @Override
    public int compareTo(Date o) {
        return date.compareTo(LocalDate.parse(o.dateString));
    }
}
