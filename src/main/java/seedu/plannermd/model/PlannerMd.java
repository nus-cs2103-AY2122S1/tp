package seedu.plannermd.model;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.plannermd.model.appointment.Appointment;
import seedu.plannermd.model.appointment.UniqueAppointmentList;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.person.Person;
import seedu.plannermd.model.person.UniquePersonList;

/**
 * Wraps all data at the Plannermd level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class PlannerMd implements ReadOnlyPlannerMd {

    private final UniquePersonList<Patient> patients;
    private final UniquePersonList<Doctor> doctors;
    private final UniqueAppointmentList appointments;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        patients = new UniquePersonList<>();
        doctors = new UniquePersonList<>();
        appointments = new UniqueAppointmentList();
    }

    public PlannerMd() {
    }

    /**
     * Creates an PlannerMd using the Persons in the {@code toBeCopied}
     */
    public PlannerMd(ReadOnlyPlannerMd toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Resets the existing data of this {@code PlannerMd} with {@code newData}.
     */
    public void resetData(ReadOnlyPlannerMd newData) {
        requireNonNull(newData);

        setPatients(newData.getPatientList());
        setDoctors(newData.getDoctorList());
        setAppointments(newData.getAppointmentList());
    }

    /**
     * Replaces the contents of the patients list with {@code patients}.
     * {@code patients} must not contain duplicate patients.
     */
    public void setPatients(List<Patient> patients) {
        this.patients.setPersons(patients);
    }

    /**
     * Replaces the contents of the doctors list with {@code doctors}.
     * {@code doctors} must not contain duplicate doctors.
     */
    public void setDoctors(List<Doctor> doctors) {
        this.doctors.setPersons(doctors);
    }

    /**
     * Replaces the contents of the appointments list with {@code appointments}.
     * {@code appointments} must not contain duplicate appointments.
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments.setAppointments(appointments);
    }

    //// person-level operations

    //Patients

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the PlannerMD.
     */
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return patients.contains(patient);
    }

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the PlannerMD.
     */
    public Optional<Patient> getExactPatient(Patient patient) {
        requireNonNull(patient);
        return patients.getExactPerson(patient);
    }

    /**
     * Adds a patient to the PlannerMD.
     * The patient must not already exist in the PlannerMD.
     */
    public void addPatient(Patient p) {
        patients.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPatient}.
     * {@code target} must exist in the PlannerMD.
     * The person identity of {@code editedPatient} must not be the same as another existing patient in the PlannerMD.
     */
    public void setPatient(Patient target, Patient editedPatient) {
        requireNonNull(editedPatient);

        patients.setPerson(target, editedPatient);
    }

    /**
     * Removes {@code key} from this {@code PlannerMd}.
     * {@code key} must exist in the PlannerMD.
     */
    public void removePatient(Patient key) {
        patients.remove(key);
    }

    //Doctors

    /**
     * Returns true if a doctor with the same identity as {@code patient} exists in the PlannerMD.
     */
    public boolean hasDoctor(Doctor doctor) {
        requireNonNull(doctor);
        return doctors.contains(doctor);
    }

    /**
     * Returns true if a doctor with the same equality as {@code patient} exists in the PlannerMD.
     */
    public Optional<Doctor> getExactDoctor(Doctor doctor) {
        requireNonNull(doctor);
        return doctors.getExactPerson(doctor);
    }

    /**
     * Adds a doctor to the PlannerMD.
     * The doctor must not already exist in the PlannerMD.
     */
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    /**
     * Replaces the given doctor {@code target} in the list with {@code editedDoctor}.
     * {@code target} must exist in the PlannerMD.
     * The doctor identity of {@code editedDoctor} must not be the same as another existing doctor in the PlannerMD.
     */
    public void setDoctor(Doctor target, Doctor editedDoctor) {
        requireNonNull(editedDoctor);
        doctors.setPerson(target, editedDoctor);
    }

    /**
     * Removes {@code key} from this {@code PlannerMd}.
     * {@code key} must exist in the PlannerMD.
     */
    public void removeDoctor(Doctor key) {
        doctors.remove(key);
    }

    //Appointments

    /**
     * Returns true if an appointment with the same identity as {@code appointment} exists in the PlannerMD.
     */
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.contains(appointment);
    }

    /**
     * Returns true if an existing appointment clashes with {@code appointment} in the PlannerMD.
     */
    public boolean isClashAppointment(Appointment appointment) {
        requireNonNull(appointment);
        for (Appointment existingAppointment : appointments) {
            if (appointment.isClash(existingAppointment)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if an existing appointment clashes with the edited appointment in the PlannerMD.
     *
     * @param editedAppointment The appointment that is edited.
     * @param oldAppointment    The appointment before applying the changes.
     */
    public boolean isClashAppointmentForEdited(Appointment editedAppointment, Appointment oldAppointment) {
        requireAllNonNull(editedAppointment, oldAppointment);
        for (Appointment existingAppointment : appointments) {
            if (oldAppointment.isSameAppointment(existingAppointment)) {
                // skip comparing, otherwise the edited appointment will almost always
                // clash with itself before the changes
                continue;
            }
            if (editedAppointment.isClash(existingAppointment)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an appointment to the PlannerMD.
     * The appointment must not already exist in the PlannerMD.
     */
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    /**
     * Replaces the given doctor {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the PlannerMD.
     * The appointment identity of {@code editedAppointment} must not be the
     * same as another existing appointment in the PlannerMD.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireNonNull(editedAppointment);
        appointments.setAppointment(target, editedAppointment);
    }

    /**
     * Removes {@code key} from this {@code PlannerMd}.
     * {@code key} must exist in the PlannerMD.
     */
    public void removeAppointment(Appointment key) {
        appointments.remove(key);
    }

    /**
     * Deletes appointments with {@code person} from the appointment list
     *
     * @param person person whose appointments should be deleted
     * @param <T>    Subtype of Person
     */
    public <T extends Person> void deleteAppointmentsWithPerson(T person) {
        appointments.deleteAppointmentsWithPerson(person);
    }

    /**
     * Updates appointments with {@code person} to {@code editedPerson} from the appointment list
     *
     * @param person       person whose appointments should be updated
     * @param editedPerson the person to replace {@code person} in existing appointments
     * @param <T>          Subtype of Person
     */
    public <T extends Person> void editAppointmentsWithPerson(T person, T editedPerson) {
        appointments.editAppointmentsWithPerson(person, editedPerson);
    }

    //// util methods

    @Override
    public String toString() {
        return patients.asUnmodifiableObservableList().size() + " patients\n"
                + doctors.asUnmodifiableObservableList().size() + " doctors\n"
                + appointments.asUnmodifiableObservableList().size() + " appointments";
    }

    @Override
    public ObservableList<Patient> getPatientList() {
        return patients.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Doctor> getDoctorList() {
        return doctors.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Appointment> getAppointmentList() {
        return appointments.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PlannerMd // instanceof handles nulls
                && doctors.equals(((PlannerMd) other).doctors)
                && patients.equals(((PlannerMd) other).patients))
                && appointments.equals(((PlannerMd) other).appointments);

    }

    @Override
    public int hashCode() {
        return Objects.hash(patients, doctors);
    }
}
