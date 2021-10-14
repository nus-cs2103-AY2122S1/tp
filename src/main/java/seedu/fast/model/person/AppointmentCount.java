package seedu.fast.model.person;

import seedu.fast.logic.commands.exceptions.CommandException;

import static java.util.Objects.requireNonNull;
import static seedu.fast.commons.util.AppUtil.checkArgument;

public class AppointmentCount {
    private static final String ERROR_MESSAGE = "Unable to update appointment count! Please try again!";

    private int count;

    public AppointmentCount(String count) {
        requireNonNull(count);
        checkArgument(isValidCount(count), ERROR_MESSAGE);
        this.count = Integer.parseInt(count);
    }


    public AppointmentCount incrementAppointmentCount() {
        count++;
        return this;
    }

    /**
     * Returns true if a given count is a valid number.
     */
    public static boolean isValidCount(String test) {
        int i;

        try {
            i = Integer.parseInt(test);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return i >= 0;
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
