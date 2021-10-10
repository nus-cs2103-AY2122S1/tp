package seedu.fast.model.person;

import seedu.fast.commons.util.DateUtil;
import seedu.fast.logic.commands.AppointmentCommand;

import static java.util.Objects.requireNonNull;
import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Represents an appointment with the Person in the address book.
 * Guarantees: immutable; is always valid.
 */
public class Appointment {
    public static final String NO_APPOINTMENT = "No Appointment Scheduled Yet";

    private final String date;

    /**
     * Constructs an {@code Appointment}.
     *
     * @param date A valid date.
     */
    public Appointment(String date) {
        requireNonNull(date);
        this.date = date;
    }

    /**
     * Returns a string in the format 'dd MMM yyyy' of the appointment.
     *
     * @return A string representing the date of the appointment.
     */
    public String getDate() {
        return this.date;
    }

    public Date convertDate() {
        Date temp = new Date(Long.MAX_VALUE);
        if (date.equals(NO_APPOINTMENT)) {
            return temp;
        }
        try {
            temp = new SimpleDateFormat("dd MMM yyyy").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return temp;
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppt = (Appointment) other;
        return otherAppt.getDate().equals(getDate());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(date);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append("Appointment: ")
                .append(this.getDate());

        return builder.toString();
    }
}
