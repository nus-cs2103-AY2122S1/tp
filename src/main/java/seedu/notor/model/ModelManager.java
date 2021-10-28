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
import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.parser.exceptions.ParseException;
import seedu.notor.model.common.Note;
import seedu.notor.model.group.Group;
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
    private FilteredList<? extends Group> filteredGroups;
    private boolean isPersonView;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyNotor notor, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(notor, userPrefs);

        logger.fine("Initializing with: " + notor + " and user prefs " + userPrefs);

        this.notor = new Notor(notor);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.notor.getPersonList());
        // Person view is first shown.
        isPersonView = true;
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
    public void clearNotorNote() {
        this.notor.setNote(Note.EMPTY_NOTE);
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
    public void addSuperGroup(String superGroup) throws ParseException {
        notor.addSuperGroup(superGroup);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void deleteSuperGroup(SuperGroup superGroup) {
        notor.deleteSuperGroup(superGroup);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public Group findGroup(String name) {
        return notor.findGroup(name);
    }

    @Override
    public void deleteSubGroup(SubGroup subGroup) {
        SuperGroup parent = (SuperGroup) notor.findGroup(subGroup.getParent());
        parent.deleteSubGroup(subGroup);
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
        // TODO: I am using levraging this method for list. Should we consider new method?
        requireNonNull(predicate);
        isPersonView = true;
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public ObservableList<? extends Group> getFilteredGroupList() {
        return filteredGroups;
    }

    @Override
    public void updateFilteredGroupList(Predicate<Group> predicate) {
        requireNonNull(predicate);
        filteredGroups.setPredicate(predicate);
    }

    @Override
    public void listSuperGroup() {
        isPersonView = false;
        filteredGroups = new FilteredList<>(this.notor.getSuperGroups());
    }

    /**
     * Replace the List with SubGroups.
     * @param i the Index i of the SuperGroup.
     */
    public void listSubGroup(Index i) {
        isPersonView = false;
        // TODO: Abstract this. This method is too long.
        filteredGroups = new FilteredList<>(this.notor.getSuperGroups().get(i.getZeroBased()).getSubGroups()
            .asUnmodifiableObservableList());
    }

    //=========== View Check =============================================================
    @Override
    public boolean isPersonView() {
        return isPersonView;
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
