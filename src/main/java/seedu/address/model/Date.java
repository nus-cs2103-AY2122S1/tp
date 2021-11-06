package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import seedu.address.model.util.DatePattern;

public class Date implements Comparable<Date> {
    public static final String MESSAGE_CONSTRAINTS =
            "Please ensure that the date is valid and follows one of the formats below:\n"
            + DatePattern.printPatterns();

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
     * @return LocalDate object corresponding to the parsed date, else null.
     */
    public static LocalDate parseDate(String dateString) {
        for (DatePattern datePattern : DatePattern.values()) {
            // Try to parse
            try {
                String pattern = datePattern.getPattern();

                DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
                builder.parseCaseInsensitive();
                builder.appendPattern(pattern);

                //ResolverStyle.STRICT to ensure that the parser does not correct any dates
                // e.g. 31 Sep 2021 to 30 Sep 2021
                DateTimeFormatter dateFormat = builder.toFormatter().withResolverStyle(ResolverStyle.STRICT);

                LocalDate date = LocalDate.from(dateFormat.parse(dateString));
                return date;
            } catch (DateTimeParseException pe) {
                // Continue loop
            }
        }

        //dateString does not match any format
        return null;
    }

    /**
     * Tests the validity of the date.
     *
     * @param test the date to be tested.
     * @return true if the date is valid, else false.
     */
    public static boolean isValidDate(String test) {
        LocalDate testDate = parseDate(test);
        return testDate != null;
    }

    /**
     * Formats LocalDate date into the PATTERN1 format
     *
     * @param date LocalDate to be formatted.
     * @return String formatted LocalDate.
     */
    public static String getFormattedDate(LocalDate date) {
        String pattern = DatePattern.PATTERN1.getPattern();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }

    @Override
    public String toString() {
        return getFormattedDate(date);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Date
                && date.equals(((Date) other).date));
    }

    @Override
    public int hashCode() {
        return dateString.hashCode();
    }

    @Override
    public int compareTo(Date o) {
        return this.date.compareTo(o.date);
    }
}
