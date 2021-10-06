package seedu.plannermd.model;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.plannermd.commons.core.GuiSettings;
import seedu.plannermd.commons.core.LogsCenter;
import seedu.plannermd.model.Model.State;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.person.Person;
import seedu.plannermd.ui.PersonTabSwitcher;

/**
 * Represents the in-memory model of the plannermd data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final PlannerMd plannerMd;
    private final UserPrefs userPrefs;
    private final FilteredList<Patient> filteredPatients;
    private final FilteredList<Doctor> filteredDoctors;
    private State state;
    private PersonTabSwitcher personTabSwitcher;

    /**
     * Initializes a ModelManager with the given plannerMd and userPrefs.
     * Default state is Patients
     */
    public ModelManager(ReadOnlyPlannerMd plannerMd, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(plannerMd, userPrefs);

        logger.fine("Initializing with plannermd: " + plannerMd + " and user prefs " + userPrefs);

        this.state = State.PATIENT;
        this.plannerMd = new PlannerMd(plannerMd);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPatients = new FilteredList<>(this.plannerMd.getPatientList());
        filteredDoctors = new FilteredList<>(this.plannerMd.getDoctorList());
    }

    public ModelManager() {
        this(new PlannerMd(), new UserPrefs());
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return this.state;
    }

    @Override
    public void toggleState() {
        if (this.state == State.PATIENT) {
            setState(State.DOCTOR);
            personTabSwitcher.switchToDoctorTab();
        } else {
            setState(State.PATIENT);
            personTabSwitcher.switchToPatientTab();
        }
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getPlannerMdFilePath() {
        return userPrefs.getPlannerMdFilePath();
    }

    @Override
    public void setPlannerMdFilePath(Path plannerMdFilePath) {
        requireNonNull(plannerMdFilePath);
        userPrefs.setPlannerMdFilePath(plannerMdFilePath);
    }

    //=========== PlannerMd ================================================================================

    @Override
    public void setPlannerMd(ReadOnlyPlannerMd plannerMd) {
        this.plannerMd.resetData(plannerMd);
    }

    @Override
    public ReadOnlyPlannerMd getPlannerMd() {
        return plannerMd;
    }

    //// patient methods

    @Override
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return plannerMd.hasPatient(patient);
    }

    @Override
    public void deletePatient(Patient target) {
        plannerMd.removePatient(target);
    }

    @Override
    public void addPatient(Patient patient) {
        plannerMd.addPatient(patient);
        updateFilteredPatientList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPatient(Patient target, Patient editedPatient) {
        requireAllNonNull(target, editedPatient);

        plannerMd.setPatient(target, editedPatient);
    }

    //// doctor methods

    @Override
    public boolean hasDoctor(Doctor doctor) {
        requireNonNull(doctor);
        return plannerMd.hasDoctor(doctor);
    }

    @Override
    public void deleteDoctor(Doctor target) {
        plannerMd.removeDoctor(target);
    }

    @Override
    public void addDoctor(Doctor doctor) {
        plannerMd.addDoctor(doctor);
        updateFilteredDoctorList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setDoctor(Doctor target, Doctor editedDoctor) {
        requireAllNonNull(target, editedDoctor);

        plannerMd.setDoctor(target, editedDoctor);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Patient} backed by the internal list of
     * {@code versionedPlannerMd}
     */
    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return filteredPatients;
    }

    @Override
    public void updateFilteredPatientList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPatients.setPredicate(predicate);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Doctor} backed by the internal list of
     * {@code versionedPlannerMd}
     */
    @Override
    public ObservableList<Doctor> getFilteredDoctorList() {
        return filteredDoctors;
    }

    @Override
    public void updateFilteredDoctorList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredDoctors.setPredicate(predicate);
    }

    //=========== Tab state management ======================================================================

    @Override
    public void setPersonTabSwitcher(PersonTabSwitcher personTabSwitcher) {
        requireNonNull(personTabSwitcher);
        this.personTabSwitcher = personTabSwitcher;
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return plannerMd.equals(other.plannerMd)
                && userPrefs.equals(other.userPrefs)
                && state.equals(other.state)
                && filteredPatients.equals(other.filteredPatients)
                && filteredDoctors.equals(other.filteredDoctors);
    }

}
