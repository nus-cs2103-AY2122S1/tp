package seedu.plannermd.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents an Appointment's date in the plannermd.
 * Guarantees: immutable; is valid as declared in {@link #isValidAppointmentDate(String)}
 */
public class AppointmentDate {

    public static final String MESSAGE_CONSTRAINTS = "Dates should be of the format DD/MM/YYYY "
            + "and adhere to the following constraints:\n"
            + "1. Must be a valid date (after now)\n"
            + "2. Day must be between 1-31 (0 in front of single digit is optional)\n"
            + "3. Month must be between 1-12 (0 in front of single digit is optional)\n"
            + "4. Year must be 4 characters.";

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy")
            .withResolverStyle(ResolverStyle.SMART);

    public static final DateTimeFormatter DISPLAYED_DATE_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy");

    public final LocalDate date;

    /**
     * Constructs an {@code AppointmentDate}.
     *
     * @param date A valid date.
     */
    public AppointmentDate(String date) {
        requireNonNull(date);
        checkArgument(isValidAppointmentDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date, DATE_FORMATTER);
    }

    /**
     * Returns if a given string is a valid date.
     */
    public static boolean isValidAppointmentDate(String test) {
        try {
            LocalDate date = LocalDate.parse(test, DATE_FORMATTER);
            return !date.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return date.format(DISPLAYED_DATE_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        }

        if (!(other instanceof AppointmentDate)) { // instanceof handles nulls
            return false;
        }

        AppointmentDate apptDate = (AppointmentDate) other;
        return date.equals(apptDate.date);
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
