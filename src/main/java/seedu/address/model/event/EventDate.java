package seedu.address.model.event;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Event's date in Ailurus.
 * Guarantees: immutable; is valid as declared in {@link #isValidEventDate(String)}
 */
public class EventDate {

    public static final String DATE_TIME_FORMAT = "dd/MM/yyyy";

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    public static final String MESSAGE_CONSTRAINTS = new StringBuilder()
            .append("Event dates should of format: ")
            .append(DATE_TIME_FORMAT)
            .append(". It should only contain numbers and '/', and it should not be blank.")
            .toString();

    public final LocalDate eventDate;

    /**
     * Constructs a {@code Name}.
     *
     * @param date A valid date.
     */
    public EventDate(String date) {
        requireNonNull(date);
        checkArgument(isValidEventDate(date), MESSAGE_CONSTRAINTS);
        eventDate = LocalDate.parse(date, DATE_TIME_FORMATTER);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidEventDate(String test) {
        try {
            DATE_TIME_FORMATTER.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return eventDate.format(DATE_TIME_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventDate // instanceof handles nulls
                && eventDate.equals(((EventDate) other).eventDate)); // state check
    }

    @Override
    public int hashCode() {
        return eventDate.hashCode();
    }

}
