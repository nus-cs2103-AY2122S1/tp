package seedu.fast.model.person;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static seedu.fast.commons.util.AppUtil.checkArgument;

public class AppointmentTime {

    public static final String MESSAGE_CONSTRAINTS = "Appointment time should be of the format HH:mm";

    public static final String VALIDATION_REGEX = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    public final LocalTime time;

    /**
     * Constructs an {@code AppointmentTime}.
     *
     * @param timeString A valid appointment time of type String.
     */
    public AppointmentTime(String timeString) {
        checkArgument(isValidAppointmentTime(timeString), MESSAGE_CONSTRAINTS);
        time = LocalTime.parse(timeString);
    }

    /**
     * Returns true if a given string is a valid appointment date.
     */
    public static boolean isValidAppointmentTime(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }

        try {
            LocalTime.parse(test);
            return true;
        } catch (DateTimeParseException e){
            return false;
        }
    }

    @Override
    public String toString() {
        return this.time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentTime // instanceof handles nulls
                && time.equals(((AppointmentTime) other).time)); // state check
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }
}
