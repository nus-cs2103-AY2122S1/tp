package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.address.commons.util.StringUtil;

public class LastMet implements OptionalNonStringBasedField, IgnoreNullComparable<LastMet> {
    public static final String MESSAGE_CONSTRAINTS = "LastMet should be in the form of Day-Month-Year, "
            + "where Day, Month and Year should be valid numerical values.";
    public static final String MESSAGE_FUTURE_DATE = "LastMet should not be a future date.";

    public final LocalDate value;
    public final String dateInString;

    /**
     * Constructs an {@code Email}.
     *
     * @param lastMetDate date agent last meets a client
     */
    public LastMet(String lastMetDate) {
        if (!IS_NULL_VALUE_ALLOWED) {
            requireNonNull(lastMetDate);
        }
        if (lastMetDate == null || lastMetDate.isEmpty()) {
            lastMetDate = DEFAULT_VALUE;
        }

        checkArgument(isValidLastMet(lastMetDate), MESSAGE_CONSTRAINTS);
        checkArgument(isNotFutureDate(lastMetDate), MESSAGE_FUTURE_DATE);
        dateInString = lastMetDate;

        if (lastMetDate.isEmpty()) {
            value = null;
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            value = LocalDate.parse(lastMetDate, formatter);
        }
    }

    /**
     * Returns true if last met does not hold any dates.
     */
    public boolean isEmpty() {
        return this.value == null;
    }

    /**
     * Returns if a given string is a valid LastMet string representation.
     */
    public static boolean isValidLastMet(String test) {
        return (IS_NULL_VALUE_ALLOWED && test.isEmpty())
            || StringUtil.isValidDate(test);
    }

    /**
     * Returns if a given string contains a date that is not in the future.
     */
    public static boolean isNotFutureDate(String test) {
        if (test.isEmpty()) {
            return true;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return !LocalDate.parse(test, formatter).isAfter(LocalDate.now());
    }

    /**
     * Returns the more recent LastMet from comparing {@code this} and {@code other}
     */
    public LastMet getLaterLastMet(LastMet other) {
        if (value == null) {
            return other;
        }
        return this.value.isBefore(other.value) ? other : this;
    }

    @Override
    public int hashCode() {
        if (value == null) {
            return 0;
        }
        return value.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof LastMet // instanceof handles nulls
            && dateInString.equals(((LastMet) other).dateInString)); // state check
    }

    @Override
    public String toString() {
        if (value == null) {
            return DEFAULT_VALUE;
        } else {
            return this.dateInString;
        }
    }

    @Override
    public int compareWithDirection(LastMet o, SortDirection sortDirection) {
        if (this.value == null && o.value == null) {
            return 0;
        }

        if (o.value == null) {
            return -1;
        }

        if (this.value == null) {
            return 1;
        }

        int direction = sortDirection.isAscending() ? 1 : -1;
        return this.value.compareTo(o.value) * direction;
    }
}
