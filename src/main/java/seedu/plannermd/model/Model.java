package seedu.plannermd.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.plannermd.commons.core.GuiSettings;
import seedu.plannermd.logic.commands.apptcommand.AppointmentFilters;
import seedu.plannermd.model.appointment.Appointment;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * Type of Person and corresponding List thats currently displayed and interacted with.
     */
    enum State {
        PATIENT, DOCTOR
    }

    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Appointment> PREDICATE_SHOW_TODAY_APPOINTMENT =
            AppointmentFilters.todayAppointmentFilter().collectAllFilters();

    /**
     * Changes current state to  {@code state}.
     */
    void setState(State state);

    /**
     * Returns current state of Model.
     */
    State getState();

    /**
     * Toggle to the other state.
     * If current state is patients, toggle to doctors. Vice Versa
     */
    void toggleState();


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

    //// patient methods
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

    //// doctor methods

    /**
     * Returns true if a doctor with the same identity as {@code doctor} exists in the PlannerMD.
     */
    boolean hasDoctor(Doctor doctor);

    /**
     * Deletes the given doctor.
     * The doctor must exist in the PlannerMD.
     */
    void deleteDoctor(Doctor target);

    /**
     * Adds the given doctor.
     * {@code doctor} must not already exist in the PlannerMD.
     */
    void addDoctor(Doctor doctor);

    /**
     * Replaces the given doctor {@code target} with {@code editedDoctor}.
     * {@code target} must exist in the PlannerMD.
     * The person identity of {@code editedDoctor} must not be the same as another existing doctor in the PlannerMD.
     */
    void setDoctor(Doctor target, Doctor editedDoctor);

    //// appointment methods

    /**
     * Returns true if an appointment with the same identity as {@code appointment} exists in the PlannerMD.
     */
    boolean hasAppointment(Appointment appointment);

    /**
     * Returns true if an existing appointment clashes with {@code appointment} in the PlannerMD.
     */
    boolean isClashAppointment(Appointment appointment);

    /**
     * Returns true if an edited appointment clashes with {@code appointment} in the PlannerMD.
     *
     * @param editedAppointment The appointment that is edited.
     * @param oldAppointment    The appointment before applying the changes.
     */
    boolean isClashAppointmentForEdited(Appointment editedAppointment, Appointment oldAppointment);

    /**
     * Deletes the given appointment.
     * The appointment must exist in the PlannerMD.
     */
    void deleteAppointment(Appointment target);

    /**
     * Adds the given appointment.
     * {@code appointment} must not already exist in the PlannerMD.
     */
    void addAppointment(Appointment appointment);

    /**
     * Replaces the given appointment {@code target} with {@code editedAppointment}.
     * {@code target} must exist in the PlannerMD.
     * The appointment identity of {@code editedAppointment} must not be the
     * same as another existing appointment in the PlannerMD.
     */
    void setAppointment(Appointment target, Appointment editedAppointment);

    /**
     * Returns an unmodifiable view of the filtered patient list
     */
    ObservableList<Patient> getFilteredPatientList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPatientList(Predicate<? super Patient> predicate);

    /**
     * Returns an unmodifiable view of the filtered doctor list
     */
    ObservableList<Doctor> getFilteredDoctorList();

    /**
     * Updates the filter of the filtered doctor list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredDoctorList(Predicate<? super Doctor> predicate);

    /**
     * Returns an unmodifiable view of the filtered appointments list
     */
    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Updates the filter of the filtered appointment list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAppointmentList(Predicate<? super Appointment> predicate);

    /**
     * Deletes appointments with {@code person} from the appointment list
     *
     * @param person person whose appointments should be deleted
     * @param <T> Subtype of Person
     */
    <T extends Person> void deleteAppointmentsWithPerson(T person);

    /**
     * Updates appointments with {@code person} to {@code editedPerson} from the appointment list
     *
     * @param person person whose appointments should be updated
     * @param editedPerson the person to replace {@code person} in existing appointments
     * @param <T> Subtype of Person
     */
    <T extends Person> void editAppointmentsWithPerson(T person, T editedPerson);

}
