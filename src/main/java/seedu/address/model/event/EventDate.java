package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * This is an EventDate class representing the Date of an Event.
 */
public class EventDate {

    public static final String MESSAGE_CONSTRAINTS = "Dates should be in YYYY-MM-DD format!";

    public final LocalDate date;

    /**
     * Constructs an {@code EventDate}
     *
     * @param date of the Event.
     */
    public EventDate(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date);
    }

    /**
     * Returns true if a given string is a valid date.
     *
     * @param test A String representing a date to be tested.
     * @return A boolean to indicate if a string is a valid date.
     */
    public static boolean isValidDate(String test) {
        boolean isValid;
        try {
            LocalDate.parse(test);
            isValid = true;
        } catch (DateTimeParseException e) {
            isValid = false;
        }
        return isValid;
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
