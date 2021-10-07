package seedu.plannermd.model;

import javafx.collections.ObservableList;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.patient.Patient;

/**
 * Unmodifiable view of a PlannerMD
 */
public interface ReadOnlyPlannerMd {

    /**
     * Returns an unmodifiable view of the patient list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Patient> getPatientList();

    /**
     * Returns an unmodifiable view of the doctor list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Doctor> getDoctorList();

}
