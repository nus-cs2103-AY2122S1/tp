package seedu.fast.model.person;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static java.util.Objects.requireNonNull;
import static seedu.fast.commons.util.AppUtil.checkArgument;

/**
 * Unmodifiable by the user.
 * Manipulated by the application program.
 */
public class AppointmentCount {
    public static final String INVALID_COUNT_INPUT = "Appointment Count has be a non-negative integer";

    private int count;

    /**
     * Constructor for {@Code AppointmentCount}
     *
     * @param count A String representing the number of appointment with a contact.
     */
    public AppointmentCount(String count) {
        requireNonNull(count);
        checkArgument(isValidCount(count), INVALID_COUNT_INPUT);
        this.count = Integer.parseInt(count);
    }

    /**
     * Increases the appointment count by 1.
     *
     * @return The updated AppointmentCount
     */
    public AppointmentCount incrementAppointmentCount() {
        count++;
        return this;
    }

    /**
     * Check if the input count is a non-negative integer.
     *
     * @param test The input count string.
     * @return A boolean indicating if the count is valid.
     */
    public static boolean isValidCount(String test) {
        try {
            int n = Integer.parseInt(test);
            return n >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    @Override
    public String toString() {
        return String.valueOf(count);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentCount // instanceof handles nulls
                && count == ((AppointmentCount) other).count); // state check
    }

    @Override
    public int hashCode() {
        return count;
    }
}
