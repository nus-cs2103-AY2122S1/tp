package seedu.plannermd.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.plannermd.commons.core.GuiSettings;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.person.Person;
import seedu.plannermd.ui.PersonTabSwitcher;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PATIENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' PlannerMD file path.
     */
    Path getPlannerMdFilePath();

    /**
     * Sets the user prefs' PlannerMD file path.
     */
    void setPlannerMdFilePath(Path plannerMdFilePath);

    /**
     * Replaces PlannerMD data with the data in {@code plannerMd}.
     */
    void setPlannerMd(ReadOnlyPlannerMd plannerMd);

    /** Returns the PlannerMd */
    ReadOnlyPlannerMd getPlannerMd();

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the PlannerMD.
     */
    boolean hasPatient(Patient patient);

    /**
     * Deletes the given patient.
     * The patient must exist in the PlannerMD.
     */
    void deletePatient(Patient target);

    /**
     * Adds the given patient.
     * {@code patient} must not already exist in the PlannerMD.
     */
    void addPatient(Patient patient);

    /**
     * Replaces the given patient {@code target} with {@code editedPatient}.
     * {@code target} must exist in the PlannerMD.
     * The person identity of {@code editedPatient} must not be the same as another existing patient in the PlannerMD.
     */
    void setPatient(Patient target, Patient editedPatient);

    /**
     * Returns an unmodifiable view of the filtered patient list
     */
    ObservableList<Patient> getFilteredPatientList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPatientList(Predicate<Person> predicate);

    /**
     * Sets the PersonTabSwitcher responsible for switching between the Doctor and Patient tab in the UI.
     */
    void setPersonTabSwitcher(PersonTabSwitcher personTabSwitcher);
}
