package seedu.placebook.model.schedule.exceptions;

import java.util.List;

import seedu.placebook.model.schedule.Appointment;

/**
 * Signals that the operation will result in an invalid {@code TimePeriod}.
 * The endTime of the time period is before the startTime of the time period.
 */
public class ClashingAppointmentsException extends RuntimeException {
    public static String MESSAGE_CLASHING_APPOINTMENTS = "Clashing Appointments found.";
    private final List<Appointment> appointments;

    /**
     * Constructs ClashingAppointmentsException.
     * @param appointments The list of appointments in the schedule that have time conflict
     *                      with the appointment to add.
     */
    public ClashingAppointmentsException(List<Appointment> appointments) {
        super(MESSAGE_CLASHING_APPOINTMENTS);
        this.appointments = appointments;
    }

    /**
     * Returns the clashing Appointments.
     * @return the clashing Appointments.
     */
    public List<Appointment> getClashingAppointment() {
        return this.appointments;
    }
}
