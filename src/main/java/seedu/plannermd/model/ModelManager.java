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
import seedu.plannermd.model.person.Person;

/**
 * Represents the in-memory model of the plannermd data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final PlannerMd plannerMd;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given plannerMd and userPrefs.
     */
    public ModelManager(ReadOnlyPlannerMd plannerMd, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(plannerMd, userPrefs);

        logger.fine("Initializing with plannermd: " + plannerMd + " and user prefs " + userPrefs);

        this.plannerMd = new PlannerMd(plannerMd);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.plannerMd.getPersonList());
    }

    public ModelManager() {
        this(new PlannerMd(), new UserPrefs());
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
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return plannerMd.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        plannerMd.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        plannerMd.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        plannerMd.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedPlannerMd}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
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
                && filteredPersons.equals(other.filteredPersons);
    }

}
