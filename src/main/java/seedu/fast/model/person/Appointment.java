package seedu.fast.model.person;

import static java.util.Objects.requireNonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import seedu.fast.commons.util.CollectionUtil;
import seedu.fast.commons.util.DateUtil;

/**
 * Represents an appointment with the Person in the address book.
 * Guarantees: immutable; is always valid.
 */
public class Appointment {
    public static final String NO_APPOINTMENT = "No Appointment Scheduled Yet";
    public static final String NO_TIME = "";
    public static final String NO_VENUE = "";

    private String date;
    private String time;
    private String venue;

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
     * Converts the date from a String to a Date object.
     *
     * @return Date object from the string.
     */
    public Date convertDate() {
        Date temp = DateUtil.MAX_DATE;
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
