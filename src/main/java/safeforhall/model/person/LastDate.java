package safeforhall.model.person;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LastDate {
    public static final String MESSAGE_CONSTRAINTS = "Date inputted has to be in dd-mm-yyyy format";

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public final String date;

    /**
     * Constructs a {@code LastDate}.
     *
     * @param date A valid date.
     */
    public LastDate(String date) {
        requireNonNull(date);
        this.date = date;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String date) {
        try {
            LocalDate.parse(date, dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public String getDate() {
        return date;
    }

    public LocalDate getLocalDate() {
        return LocalDate.parse(date, dateFormatter);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LastDate // instanceof handles nulls
                && date.equals(((LastDate) other).date)); // state check
    }
}
