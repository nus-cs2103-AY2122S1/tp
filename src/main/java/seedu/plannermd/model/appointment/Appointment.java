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
    private final AppointmentDate date;
    private final Session session;
    private final Remark remark;

    /**
     * Constructs an Appointment.
     * Every field must be present and not null.
     */
    public Appointment(Patient patient, Doctor doctor, AppointmentDate date,
                       Session session, Remark remark) {
        requireAllNonNull(patient, doctor, date, session, remark);
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.session = session;
        this.remark = remark;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public AppointmentDate getAppointmentDate() {
        return date;
    }

    public Session getSession() {
        return session;
    }

    public Remark getRemark() {
        return remark;
    }

    /**
     * Checks if this Appointment clashes with the given Appointment.
     * There is a clash if both Appointments have the same Patient or Doctor with an overlapping date and time.
     */
    public boolean isClash(Appointment otherAppointment) {
        boolean isSamePatient = getPatient().isSamePerson(otherAppointment.getPatient());
        boolean isSameDoctor = getDoctor().isSamePerson(otherAppointment.getDoctor());
        boolean isDateClash = getAppointmentDate().equals(otherAppointment.getAppointmentDate());
        boolean isSessionClash = getSession().isClash(otherAppointment.getSession());

        return (isSamePatient || isSameDoctor) && isDateClash && isSessionClash;
    }

    /**
     * Returns true if both appointments have the same patient, doctor, date, session.
     * This defines a weaker notion of equality between two appointments.
     */
    public boolean isSameAppointment(Appointment otherAppointment) {
        if (otherAppointment == this) {
            return true;
        }

        return otherAppointment != null
                && otherAppointment.getPatient().equals(getPatient())
                && otherAppointment.getDoctor().equals(getDoctor())
                && otherAppointment.getAppointmentDate().equals(getAppointmentDate())
                && otherAppointment.getSession().equals(getSession());
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
                && otherAppointment.getAppointmentDate().equals(getAppointmentDate())
                && otherAppointment.getSession().equals(getSession())
                && otherAppointment.getRemark().equals(getRemark());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(patient, doctor, date, session, remark);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Patient: ").append(getPatient().getName())
                .append("; Doctor: ").append(getDoctor().getName())
                .append("; Date: ").append(getAppointmentDate())
                .append("; ").append(getSession());

        if (!remark.isEmpty()) {
            sb.append("; Remark: ").append(getRemark());
        }

        return sb.toString();
    }
}
