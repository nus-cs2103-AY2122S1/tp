package seedu.fast.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static seedu.fast.commons.util.AppUtil.checkArgument;

public class AppointmentDate {

    public static final String MESSAGE_CONSTRAINTS = "Appointment date should be of the format yyyy-MM-dd";

    public static final String VALIDATION_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";

    public final LocalDate date;

    /**
     * Constructs an {@code AppointmentDate}.
     *
     * @param dateString A valid appointment date of type String.
     */
    public AppointmentDate(String dateString) {
        checkArgument(isValidAppointmentDate(dateString), MESSAGE_CONSTRAINTS);
        date = LocalDate.parse(dateString);
    }

    /**
     * Returns true if a given string is a valid appointment date.
     */
    public static boolean isValidAppointmentDate(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }

        try {
            LocalDate.parse(test);
            return true;
        } catch (DateTimeParseException e){
            return false;
        }
    }

    @Override
    public String toString() {
        return this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentDate // instanceof handles nulls
                && date.equals(((AppointmentDate) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
