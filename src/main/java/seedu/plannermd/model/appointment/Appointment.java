package seedu.plannermd.model.appointment;

import static seedu.plannermd.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.person.Remark;

/**
 * Represents an Appointment in the plannermd. Guarantees: details are present and
 * not null, field values are validated, immutable.
 */
public class Appointment {

    private final Patient patient;
    private final Doctor doctor;
    private final AppointmentDateTime startDateTime;
    private final AppointmentDateTime endDateTime;
    private final Remark remark;

    /**
     * Constructs an Appointment.
     * Every field must be present and not null.
     */
    public Appointment(Patient patient, Doctor doctor, AppointmentDateTime startDateTime,
                       AppointmentDateTime endDateTime, Remark remark) {
        requireAllNonNull(patient, doctor, startDateTime, endDateTime, remark);
        this.patient = patient;
        this.doctor = doctor;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.remark = remark;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public AppointmentDateTime getStartDateTime() {
        return startDateTime;
    }

    public AppointmentDateTime getEndDateTime() {
        return endDateTime;
    }

    public Remark getRemark() {
        return remark;
    }

    /**
     * Checks if this Appointment clashes with the given Appointment.
     * There is a clash if both Appointments have the same Patient or Doctor with an overlapping date/time.
     */
    public boolean isClash(Appointment otherAppointment) {
        boolean isSamePatient = getPatient().isSamePerson(otherAppointment.getPatient());
        boolean isSameDoctor = getDoctor().isSamePerson(otherAppointment.getDoctor());
        boolean isDateTimeClash = AppointmentDateTime.isClash(
                getStartDateTime(), getEndDateTime(),
                otherAppointment.getStartDateTime(), otherAppointment.getEndDateTime());

        return (isSamePatient || isSameDoctor) && isDateTimeClash;
    }

    /**
     * Returns true if both appointments have the same patient, doctor, starting date and time, ending date and time.
     * This defines a weaker notion of equality between two appointments.
     */
    public boolean isSameAppointment(Appointment otherAppointment) {
        if (otherAppointment == this) {
            return true;
        }

        return otherAppointment != null
                && otherAppointment.getPatient().equals(getPatient())
                && otherAppointment.getDoctor().equals(getDoctor())
                && otherAppointment.getStartDateTime().equals(getStartDateTime())
                && otherAppointment.getEndDateTime().equals(getEndDateTime());
    }

    /**
     * Returns true if both appointments have the same fields.
     * This defines a stronger notion of equality between two appointments.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        return otherAppointment.getPatient().equals(getPatient())
                && otherAppointment.getDoctor().equals(getDoctor())
                && otherAppointment.getStartDateTime().equals(getStartDateTime())
                && otherAppointment.getEndDateTime().equals(getEndDateTime())
                && otherAppointment.getRemark().equals(getRemark());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(patient, doctor, startDateTime, endDateTime, remark);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Patient: ").append(getPatient().getName())
                .append("; Doctor: ").append(getDoctor().getName())
                .append("; Start: ").append(getStartDateTime())
                .append("; End: ").append(getEndDateTime());

        if (!remark.isEmpty()) {
            sb.append("; Remark: ").append(getRemark());
        }

        return sb.toString();
    }
}
