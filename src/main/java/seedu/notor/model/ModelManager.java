package seedu.notor.model;

import static java.util.Objects.requireNonNull;
import static seedu.notor.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.notor.commons.core.GuiSettings;
import seedu.notor.commons.core.LogsCenter;
import seedu.notor.model.group.SubGroup;
import seedu.notor.model.group.SuperGroup;
import seedu.notor.model.person.Person;

/**
 * Represents the in-memory model of Notor's data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Notor notor;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given Notor and userPrefs.
     */
    public ModelManager(ReadOnlyNotor notor, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(notor, userPrefs);

        logger.fine("Initializing with: " + notor + " and user prefs " + userPrefs);

        this.notor = new Notor(notor);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.notor.getPersonList());
    }

    public ModelManager() {
        this(new Notor(), new UserPrefs());
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
    public Path getNotorFilePath() {
        return userPrefs.getNotorFilePath();
    }

    @Override
    public void setNotorFilePath(Path notorFilePath) {
        requireNonNull(notorFilePath);
        userPrefs.setNotorFilePath(notorFilePath);
    }

    //=========== Notor =====================================================================================

    @Override
    public void setNotor(ReadOnlyNotor notor) {
        this.notor.resetData(notor);
    }

    @Override
    public ReadOnlyNotor getNotor() {
        return notor;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return notor.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        notor.removePerson(target);
    }

    @Override
    public void createPerson(Person person) {
        notor.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public Person findPerson(String name) {
        return notor.findPerson(name);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        notor.setPerson(target, editedPerson);
    }

    @Override
    public boolean hasSuperGroup(SuperGroup superGroup) {
        requireNonNull(superGroup);
        return notor.hasSuperGroup(superGroup);
    }

    @Override
    public void addSuperGroup(SuperGroup superGroup) {
        notor.addSuperGroup(superGroup);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void deleteSuperGroup(SuperGroup superGroup) {
        notor.deleteSuperGroup(superGroup);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public SuperGroup findSuperGroup(String name) {
        return notor.findSuperGroup(name);
    }

    @Override
    public SubGroup findSubGroup(String name) {
        return notor.findSubGroup(name);
    }

    @Override
    public void addSubGroup(SubGroup subGroup) {
        notor.addSubGroup(subGroup);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void deleteSubGroup(SubGroup subGroup) {
        notor.deleteSubGroup(subGroup);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
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
        return notor.equals(other.notor)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
