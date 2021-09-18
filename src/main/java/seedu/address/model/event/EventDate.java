package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EventDate {
    public static final String MESSAGE_CONSTRAINTS = "Dates should be in YYYY-MM-DD format!";

    public final LocalDate date;

    /**
     * Constructs an {@code EventDate}
     *
     * @param date  A valid date.
     */
    public EventDate(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        // There must be a better way to do this.
        try {
            LocalDate.parse(test);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return date.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof EventDate
                && date.equals(((EventDate) other).date));
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

}
