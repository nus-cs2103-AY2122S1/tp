package seedu.siasa.model;

import static java.util.Objects.requireNonNull;
import static seedu.siasa.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.siasa.commons.core.GuiSettings;
import seedu.siasa.commons.core.LogsCenter;
import seedu.siasa.model.person.Person;
import seedu.siasa.model.policy.Policy;

/**
 * Represents the in-memory model of the SIASA data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Siasa siasa;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Policy> filteredPolicies;

    /**
     * Initializes a ModelManager with the given SIASA and userPrefs.
     */
    public ModelManager(ReadOnlySiasa siasa, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(siasa, userPrefs);

        logger.fine("Initializing with SIASA: " + siasa + " and user prefs " + userPrefs);

        this.siasa = new Siasa(siasa);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.siasa.getPersonList());
        filteredPolicies = new FilteredList<>(this.siasa.getPolicyList());
    }

    public ModelManager() {
        this(new Siasa(), new UserPrefs());
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
    public Path getSiasaFilePath() {
        return userPrefs.getSiasaFilePath();
    }

    @Override
    public void setSiasaFilePath(Path siasaFilePathPath) {
        requireNonNull(siasaFilePathPath);
        userPrefs.setSiasaFilePath(siasaFilePathPath);
    }

    //=========== Siasa ================================================================================

    @Override
    public void setSiasa(ReadOnlySiasa siasa) {
        this.siasa.resetData(siasa);
    }

    @Override
    public ReadOnlySiasa getSiasa() {
        return siasa;
    }

    //=========== Person CRUD ================================================================================

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return siasa.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        siasa.removePersonAndAssociatedPolicies(target);
    }

    @Override
    public void addPerson(Person person) {
        siasa.addPerson(person);
        removeAllFilters();
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        siasa.setPerson(target, editedPerson);
    }

    //=========== Policy CRUD ================================================================================

    @Override
    public boolean hasPolicy(Policy person) {
        requireNonNull(person);
        return siasa.hasPolicy(person);
    }

    @Override
    public void deletePolicy(Policy target) {
        siasa.removePolicy(target);
    }

    @Override
    public void addPolicy(Policy person) {
        siasa.addPolicy(person);
        removeAllFilters();
    }

    @Override
    public void setPolicy(Policy target, Policy editedPolicy) {
        requireAllNonNull(target, editedPolicy);

        siasa.setPolicy(target, editedPolicy);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedSiasa}
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

    //=========== Filtered Policy List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Policy} backed by the internal list of
     * {@code versionedSiasa}
     */
    @Override
    public ObservableList<Policy> getFilteredPolicyList() {
        return filteredPolicies;
    }

    @Override
    public void updateFilteredPolicyList(Predicate<Policy> predicate) {
        requireNonNull(predicate);
        filteredPolicies.setPredicate(predicate);
    }

    public void removeAllFilters() {
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        updateFilteredPolicyList(PREDICATE_SHOW_ALL_POLICIES);
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
        return siasa.equals(other.siasa)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredPolicies.equals(other.filteredPolicies);
    }

}
