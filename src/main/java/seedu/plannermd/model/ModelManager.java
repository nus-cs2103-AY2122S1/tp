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
import seedu.plannermd.model.PlannerMd.State;
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
    private final State state;
    private PersonTabSwitcher personTabSwitcher;

    /**
     * Initializes a ModelManager with the given plannerMd and userPrefs.
     */
    public ModelManager(ReadOnlyPlannerMd plannerMd, ReadOnlyUserPrefs userPrefs, State state) {
        super();
        requireAllNonNull(plannerMd, userPrefs);

        logger.fine("Initializing with plannermd: " + plannerMd + " and user prefs " + userPrefs);

        this.plannerMd = new PlannerMd(plannerMd);
        this.userPrefs = new UserPrefs(userPrefs);
        this.state = state;
        filteredPatients = new FilteredList<>(this.plannerMd.getPatientList());
        filteredDoctors = new FilteredList<>(this.plannerMd.getDoctorList());
    }

    public ModelManager() {
        this(new PlannerMd(), new UserPrefs(), State.PATIENT);
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
        updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
    }

    @Override
    public void setPatient(Patient target, Patient editedPatient) {
        requireAllNonNull(target, editedPatient);

        plannerMd.setPatient(target, editedPatient);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
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
                && filteredPatients.equals(other.filteredPatients);
    }

}
