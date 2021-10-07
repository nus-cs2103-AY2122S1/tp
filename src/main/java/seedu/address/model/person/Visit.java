package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a Person's visit in the address book.
 * Guarantees: immutable; is always valid
 */
public class Visit {
    public static final String MESSAGE_CONSTRAINTS = "Visit date should be of the format yyyy-MM-dd";
    public static final String YEAR_REGEX = "\\d{4}";
    public static final String MONTH_REGEX = "(0[1-9]|1[0-2])";
    public static final String DAY_REGEX = "(0[1-9]|[12][0-9]|3[01])";
    public static final String VALIDATION_REGEX = "^" + YEAR_REGEX + "-" + MONTH_REGEX + "-" + DAY_REGEX + "$";

    public final String value;

    /**
     * Constructs an {@code Visit}.
     *
     * @param visit A valid visit.
     */
    public Visit(String visit) {
        requireNonNull(visit);
        value = visit;
    }

    /**
     * Returns true if a given string is a valid visit.
     */
    public static boolean isValidVisit(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns formatted visit date.
     */
    public String toFormatted() throws ParseException {
        if (value.isEmpty()) {
            return value;
        }

        String visit;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate date = LocalDate.parse(value, formatter);
            visit = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(date);
        } catch (DateTimeParseException ive) {
            throw new ParseException(ParserUtil.MESSAGE_INVALID_DATE);
        }
        return visit;
    }

    /**
     * Determines whether the visit is empty based on value
     * @return whether the visit is empty
     */
    public boolean hasVisit() {
        return !(this.value == null || this.value.isEmpty());
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Visit // instanceof handles nulls
                && value.equals(((Visit) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
