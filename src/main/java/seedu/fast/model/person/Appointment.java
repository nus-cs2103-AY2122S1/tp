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
    public static final String NO_VENUE = "";

    private final String date;
    private final String time;
    private final String venue;

    /**
     * Constructs an {@code Appointment}.
     *
     * @param date A valid date.
     * @param time A valid time.
     * @param venue A venue.
     */
    public Appointment(String date, String time, String venue) {
        requireNonNull(date);
        this.date = date;
        this.time = time;
        this.venue = venue;
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
        if (this.time.equals(NO_TIME)) {
            return "";
        }

        if (!this.time.contains("hrs")) {
            return this.time + "hrs";
        }

        return this.time;
    }

    /**
     * Returns a string representing the venue of the appointment.
     *
     * @return A string representing the venue of the appointment.
     */
    public String getVenue() {
        if (this.venue.equals(NO_VENUE)) {
            return "";
        }

        return this.venue;
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
                && otherAppt.getTime().equals(getTime())
                && otherAppt.getVenue().equals(getVenue());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(date, time, venue);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append("Appointment: ")
                .append(this.getDate())
                .append(" ")
                .append(this.getTime())
                .append(" ")
                .append(this.getVenue());

        return builder.toString();
    }
}
