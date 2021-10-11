package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.parser.ParserUtil;

/**
 * Represents a Person's visit in the address book.
 * Guarantees: immutable; is always valid
 */
public class Visit {
    public static final String MESSAGE_CONSTRAINTS = "Visit date should be of the format yyyy-MM-dd HH:mm";

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
        return DateTimeUtil.matchDateTimeRegex(test);
    }

    /**
     * Determines whether the visit is empty based on value
     * @return whether the visit is empty
     */
    public boolean hasVisit() {
        return !(this.value == null || this.value.isEmpty());
    }

    /**
     * Returns formatted last visit date.
     */
    public String getFormatted() {
        if (!hasVisit()) {
            return value;
        }

        return ParserUtil.parseDisplayedDatetime(value);
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
