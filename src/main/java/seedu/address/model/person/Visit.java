package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

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
     */
    public boolean hasVisit() {
        return !(this.value == null || this.value.isEmpty());
    }

    /**
     * Returns if the visit exists and is overdue.
     * Compared against today's date.
     */
    public boolean isOverdue() {
        if (!hasVisit()) {
            return false;
        }

        LocalDateTime visitTime;
        try {
            visitTime = LocalDateTime.parse(value, DateTimeUtil.FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }

        return DateTimeUtil.isPast(visitTime);
    }

    /**
     * Returns true if the visit date is within the coming week.
     */
    public boolean isNextSevenDays() {
        if (value.isEmpty()) {
            return false;
        }
        return DateTimeUtil.isNextSevenDays(LocalDateTime.parse(value, DateTimeUtil.FORMATTER));
    }

    /**
     * Returns true if the visit date is within the current month.
     */
    public boolean isNextThirtyDays() {
        if (value.isEmpty()) {
            return false;
        }
        return DateTimeUtil.isNextThirtyDays(LocalDateTime.parse(value, DateTimeUtil.FORMATTER));
    }

    /**
     * Returns formatted last visit date.
     */
    public String getFormatted() {
        if (!hasVisit()) {
            return "-";
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

    /**
     * Get the local date time of visit.
     * @return the LocalDateTime of this visit.
     */
    public LocalDateTime getDateTime() {
        LocalDateTime visitTime;
        try {
            visitTime = LocalDateTime.parse(value, DateTimeUtil.FORMATTER);
        } catch (DateTimeParseException e) {
            return LocalDateTime.MAX;
        }
        return visitTime;
    }
}
