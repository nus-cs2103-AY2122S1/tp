package safeforhall.model.event;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventDate {
    public static final String MESSAGE_CONSTRAINTS = "EventDate inputted has to be in dd-mm-yyyy format";
    public static final String DESC = "Date: ";

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static final int LASTDATE_DEADLINE = 1;

    public final String eventDate;

    /**
     * Constructs a {@code EventDate}.
     *
     * @param date A valid date.
     */
    public EventDate(String date) {
        requireNonNull(date);
        this.eventDate = date;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidEventDate(String date) {
        try {
            LocalDate.parse(date, dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public String getEventDate() {
        return eventDate;
    }

    /**
     * Converts the given {@code EventDate} to a {@code LocalDate}.
     */
    public LocalDate toLocalDate() {
        return LocalDate.parse(eventDate, dateFormatter);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventDate // instanceof handles nulls
                && eventDate.equals(((EventDate) other).eventDate)); // state check
    }

    @Override
    public String toString() {
        return eventDate;
    }
}
