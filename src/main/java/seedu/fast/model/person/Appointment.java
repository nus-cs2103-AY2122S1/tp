package seedu.fast.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents an appointment with the Person in the address book.
 * Guarantees: immutable; is always valid.
 */
public class Appointment {
    public static final String NO_APPOINTMENT = "No Appointment Scheduled Yet";
    public static final String NO_TIME = "";

    private final String date;
    private final String time;

    /**
     * Constructs an {@code Appointment}.
     *
     * @param date A valid date.
     */
    public Appointment(String date, String time) {
        requireNonNull(date);
        this.date = date;
        this.time = time;
    }

    /**
     * Returns a string in the format 'dd MMM yyyy' of the appointment.
     *
     * @return A string representing the date of the appointment.
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Returns a string in the format 'HHmm' of the appointment.
     *
     * @return A string representing the time of the appointment.
     */
    public String getTime() {
        return this.time;
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
        return otherAppt.getDate().equals(getDate())
                && otherAppt.getTime().equals(getTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(date, time);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append("Appointment: ")
                .append(this.getDate());

        // displays the string of the appointment time if it is not empty
        if (!this.getTime().equals("")) {
                builder.append(" ")
                        .append(this.getTime())
                        .append("hrs");
        }

        return builder.toString();
    }
}
