package seedu.plannermd.model.appointment.exceptions;

/**
 * Signals that the operation will result in clashing Appointments
 * (Appointments are considered clashing if they have overlapping times).
 * E.g. 12:00-12:15 clashes with 12:10-12:15. 12:00-12:15 does not clash with 12:15-12:30.
 */
public class ClashingAppointmentException extends RuntimeException {

    public ClashingAppointmentException() {
        super("Operation would result in clashing appointments");
    }
}
