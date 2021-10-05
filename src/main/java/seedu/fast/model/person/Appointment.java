package seedu.fast.model.person;

import seedu.fast.model.AppointmentDate;

import java.util.Objects;

public class Appointment {

    private final AppointmentDate date;
    private final AppointmentTime time;
    private final AppointmentVenue venue;

    public Appointment(AppointmentDate date, AppointmentTime time, AppointmentVenue venue) {
        this.date = date;
        this.time = time;
        this.venue = venue;
    }

    public AppointmentDate getDate() {
        return date;
    }

    public AppointmentTime getTime() {
        return time;
    }

    public AppointmentVenue getVenue() {
        return venue;
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
        builder.append("Appointment on ")
                .append(getDate())
                .append(", ")
                .append(getTime())
                .append(", at ")
                .append(getVenue())
                .append("!");

        return builder.toString();
    }
}
