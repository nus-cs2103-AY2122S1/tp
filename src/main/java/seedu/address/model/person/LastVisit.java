package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.parser.ParserUtil;

/**
 * Represents a Person's last visit in the address book.
 * Guarantees: immutable; is always valid
 */
public class LastVisit {
    public static final String MESSAGE_CONSTRAINTS = "Last visit date should be of the format yyyy-MM-dd HH:mm";

    public final String value;

    /**
     * Constructs an {@code Visit}.
     *
     * @param lastVisit A valid last visit.
     */
    public LastVisit(String lastVisit) {
        requireNonNull(lastVisit);
        value = lastVisit;
    }

    /**
     * Returns true if a given string is a valid last visit.
     */
    public static boolean isValidLastVisit(String test) {
        return DateTimeUtil.matchDateTimeRegex(test);
    }

    /**
     * Returns true if the last visit date is within the last 7 days.
     */
    public boolean isLastWeek() {
        if (value.isEmpty()) {
            return false;
        }
        return DateTimeUtil.isLastWeek(LocalDateTime.parse(value, DateTimeUtil.FORMATTER));
    }

    /**
     * Returns true if the last visit date is within the last month.
     */
    public boolean isLastMonth() {
        if (value.isEmpty()) {
            return false;
        }
        return DateTimeUtil.isLastMonth(LocalDateTime.parse(value, DateTimeUtil.FORMATTER));
    }

    /**
     * Returns formatted last visit date.
     */
    public String getFormatted() {
        if (value.isEmpty()) {
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
                   || (other instanceof LastVisit // instanceof handles nulls
                   && value.equals(((LastVisit) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
