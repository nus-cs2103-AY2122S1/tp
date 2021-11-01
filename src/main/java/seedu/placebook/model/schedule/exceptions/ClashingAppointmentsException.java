package seedu.placebook.model.schedule.exceptions;

import seedu.placebook.model.schedule.Appointment;

/**
 * Signals that the operation will result in an invalid {@code TimePeriod}.
 * The endTime of the time period is before the startTime of the time period.
 */
public class ClashingAppointmentsException extends RuntimeException {
    private final Appointment appointment;

    public ClashingAppointmentsException(Appointment appointment) {
        super("Clashing Appointment with: ");
        this.appointment = appointment;
    }

    public Appointment getClashingAppointment() {
        return this.appointment;
    }
}
