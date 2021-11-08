package safeforhall.model.event;

import static java.util.Objects.requireNonNull;
import static safeforhall.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class EventDate implements Comparable<EventDate> {
    public static final String MESSAGE_CONSTRAINTS = "Date inputted has to be a valid date in the format of:\n1. "
            + "dd-mm-yyyy\n2. dd.mm.yyyy\n3. dd/mm/yyyy\n"
            + "Some common errors include having an invalid day (35-01-2021),"
            + " an invalid month (31-13-2021), an invalid date (29-02-2021) or an invalid formatting (01012021)";
    public static final String DESC = "Date: ";
    public static final String FIELD = "d";

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter
            .ofPattern("[dd-MM-uuuu][dd.MM.uuuu][dd/MM/uuuu]")
            .withResolverStyle(ResolverStyle.STRICT);

    public final String eventDate;

    /**
     * Constructs a {@code EventDate}.
     *
     * @param date A valid date.
     */
    public EventDate(String date) {
        requireNonNull(date);
        checkArgument(isValidEventDate(date), MESSAGE_CONSTRAINTS);
        String[] dayMonthYear = date.split("[-./]");
        assert dayMonthYear.length == 3;
        this.eventDate = dayMonthYear[0] + "-" + dayMonthYear[1] + "-" + dayMonthYear[2];
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

    /**
     * Returns true if the current eventDate is in the past.
     */
    public boolean isPast() {
        return toLocalDate().isBefore(LocalDate.now());
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

    @Override
    public int compareTo(EventDate date) {
        return this.toLocalDate().compareTo(date.toLocalDate());
    }
}
