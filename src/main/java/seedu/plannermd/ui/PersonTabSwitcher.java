package seedu.plannermd.ui;

/**
 * Represents a model that is responsible for switching the UI tabs between
 * the Doctors and Patient records.
 */
public interface PersonTabSwitcher {

    /** Sets the UI to show the Patients records. */
    void switchToPatientTab();

    /** Sets the UI to show the Doctor records. */
    void switchToDoctorTab();
}
