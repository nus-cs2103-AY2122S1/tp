package nustracker.model.event;

import static java.util.Objects.requireNonNull;
import static nustracker.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents an Event's Date.
 */
public class EventDate {

    public static final String MESSAGE_CONSTRAINTS =
            "EventDates should be in the format DD-MM-YYYY. E.g. 09-10-2021.\n"
            + "Ensure that the month has that day. E.g. Feb only has 28 days (29 for leap years).";

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu")
            .withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd uuuu");

    public final LocalDate eventDate;

    /**
     * Constructs an {@code EventDate}.
     *
     * @param date A valid date.
     */
    public EventDate(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        eventDate = LocalDate.parse(date, INPUT_FORMATTER);
    }

    /**
     * Check if date input is valid.
     *
     * @param date Date input
     * @return True if valid; false if invalid.
     */
    public static boolean isValidDate(String date) {
        try {
            LocalDate.parse(date, INPUT_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Return the event date in the Input format.
     *
     * @return date in Input format
     */
    public String getEventDate() {
        return eventDate.format(INPUT_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventDate // instanceof handles nulls
                && eventDate.equals(((EventDate) other).eventDate)); // state check
    }

    @Override
    public String toString() {
        return eventDate.format(OUTPUT_FORMATTER);
    }

    @Override
    public int hashCode() {
        return eventDate.hashCode();
    }
}
