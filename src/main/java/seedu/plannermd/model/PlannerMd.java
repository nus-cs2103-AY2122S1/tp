package seedu.plannermd.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.person.UniquePersonList;

/**
 * Wraps all data at the Plannermd level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class PlannerMd implements ReadOnlyPlannerMd {

    private final UniquePersonList<Patient> patients;
    private final UniquePersonList<Doctor> doctors;

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
    }

    public PlannerMd() {}

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

    //// util methods

    @Override
    public String toString() {
        return patients.asUnmodifiableObservableList().size() + " patients\n"
                + doctors.asUnmodifiableObservableList().size() + " doctors";
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
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PlannerMd // instanceof handles nulls
                && doctors.equals(((PlannerMd) other).doctors)
                && patients.equals(((PlannerMd) other).patients));
    }

    @Override
    public int hashCode() {
        return Objects.hash(patients, doctors);
    }
}
