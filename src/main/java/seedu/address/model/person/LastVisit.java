package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

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
     * Determines whether the visit is empty based on value
     */
    public boolean hasLastVisit() {
        return !(this.value == null || this.value.isEmpty());
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
    public boolean isLastSevenDays() {
        if (value.isEmpty()) {
            return false;
        }
        return DateTimeUtil.isLastSevenDays(LocalDateTime.parse(value, DateTimeUtil.FORMATTER));
    }

    /**
     * Returns true if the last visit date is in the future
     */
    public boolean isFuture() {
        if (!hasLastVisit()) {
            return false;
        }

        LocalDateTime visitTime;
        try {
            visitTime = LocalDateTime.parse(value, DateTimeUtil.FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }

        return DateTimeUtil.isFuture(visitTime);
    }

    /**
     * Returns true if the last visit date is within the last month.
     */
    public boolean isLastThirtyDays() {
        if (value.isEmpty()) {
            return false;
        }
        return DateTimeUtil.isLastThirtyDays(LocalDateTime.parse(value, DateTimeUtil.FORMATTER));
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

    /**
     * Get the local date time of last visit.
     * @return the LocalDateTime of this last visit.
     */
    public LocalDateTime getDateTime() {
        LocalDateTime visitTime;
        try {
            visitTime = LocalDateTime.parse(value, DateTimeUtil.FORMATTER);
        } catch (DateTimeParseException e) {
            return LocalDateTime.MIN;
        }
        return visitTime;
    }
}
