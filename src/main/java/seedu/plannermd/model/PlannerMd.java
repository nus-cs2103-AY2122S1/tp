package seedu.plannermd.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.patient.UniquePatientList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class PlannerMd implements ReadOnlyPlannerMd {

    private final UniquePatientList patients;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        patients = new UniquePatientList();
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
     * Replaces the contents of the patients list with {@code patients}.
     * {@code patients} must not contain duplicate patients.
     */
    public void setPatients(List<Patient> patients) {
        this.patients.setPatients(patients);
    }

    /**
     * Resets the existing data of this {@code PlannerMd} with {@code newData}.
     */
    public void resetData(ReadOnlyPlannerMd newData) {
        requireNonNull(newData);

        setPatients(newData.getPatientList());
    }

    //// person-level operations

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

        patients.setPatient(target, editedPatient);
    }

    /**
     * Removes {@code key} from this {@code PlannerMd}.
     * {@code key} must exist in the PlannerMD.
     */
    public void removePatient(Patient key) {
        patients.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return patients.asUnmodifiableObservableList().size() + " patients";
        // TODO: refine later
    }

    @Override
    public ObservableList<Patient> getPatientList() {
        return patients.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PlannerMd // instanceof handles nulls
                && patients.equals(((PlannerMd) other).patients));
    }

    @Override
    public int hashCode() {
        return patients.hashCode();
    }
}
