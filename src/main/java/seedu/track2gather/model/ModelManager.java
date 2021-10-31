package seedu.track2gather.model;

import static java.util.Objects.requireNonNull;
import static seedu.track2gather.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.track2gather.commons.core.GuiSettings;
import seedu.track2gather.commons.core.LogsCenter;
import seedu.track2gather.model.person.Person;

/**
 * Represents the in-memory model of the contacts list data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Track2Gather track2Gather;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final SortedList<Person> sortedPersons;

    /**
     * Initializes a ModelManager with the given track2Gather and userPrefs.
     */
    public ModelManager(ReadOnlyTrack2Gather track2Gather, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(track2Gather, userPrefs);

        logger.fine("Initializing with contacts list: " + track2Gather + " and user prefs " + userPrefs);

        this.track2Gather = new Track2Gather(track2Gather);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.track2Gather.getPersonList());
        sortedPersons = filteredPersons.sorted();
    }

    public ModelManager() {
        this(new Track2Gather(), new UserPrefs());
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
    public Path getTrack2GatherFilePath() {
        return userPrefs.getTrack2GatherFilePath();
    }

    @Override
    public void setTrack2GatherFilePath(Path track2GatherFilePath) {
        requireNonNull(track2GatherFilePath);
        userPrefs.setTrack2GatherFilePath(track2GatherFilePath);
    }

    //=========== Track2Gather ================================================================================

    @Override
    public void setTrack2Gather(ReadOnlyTrack2Gather track2Gather) {
        this.track2Gather.resetData(track2Gather);
    }

    @Override
    public ReadOnlyTrack2Gather getTrack2Gather() {
        return track2Gather;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return track2Gather.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        track2Gather.removePerson(target);
    }

    @Override
    public void resetAllCallStatuses() {
        track2Gather.resetAllCallStatuses();
    }

    @Override
    public void addPerson(Person person) {
        track2Gather.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        track2Gather.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedTrack2Gather}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return sortedPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateSortedPersonList(Comparator<Person> comparator) {
        requireNonNull(comparator);
        sortedPersons.setComparator(comparator);
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
        return track2Gather.equals(other.track2Gather)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && sortedPersons.equals(other.sortedPersons);
    }

}
