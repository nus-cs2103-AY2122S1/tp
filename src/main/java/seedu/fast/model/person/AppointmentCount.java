package seedu.fast.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Unmodifiable by the user.
 * Manipulated by the application program.
 */
public class AppointmentCount {
    private static final String ERROR_MESSAGE = "Unable to update appointment count! Please try again!";

    private int count;

    /**
     * Constructor for {@Code AppointmentCount}
     *
     * @param count A String representing the number of appointment with a contact.
     */
    public AppointmentCount(String count) {
        requireNonNull(count);
        // will always be correct, since count is initialise to 0 when a contact is added
        // and always incremented by 1.
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
