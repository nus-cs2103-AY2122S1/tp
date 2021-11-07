package seedu.notor.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.notor.commons.core.GuiSettings;
import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.parser.exceptions.ParseException;
import seedu.notor.model.group.Group;
import seedu.notor.model.group.SubGroup;
import seedu.notor.model.group.SuperGroup;
import seedu.notor.model.person.Person;
import seedu.notor.ui.listpanel.PersonListPanel;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    void setup(PersonListPanel personListPanel);

    // TODO: List
    // Predicate<Group> PREDICATE_SHOW_ALL_GROUPS = unused -> true;

    //=========== UserPrefs ==================================================================================

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
     * Returns the user prefs' address book file path.
     */
    Path getNotorFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setNotorFilePath(Path notorFilePath);

    //=========== Notor ==================================================================

    /**
     * Replaces Notor data (of the list) with the data in {@code notor}.
     */
    void setNotor(ReadOnlyNotor notor);

    void clearNotorNote();

    /**
     * Returns the Notor data (lists and the like)
     */
    ReadOnlyNotor getNotor();

    /**
     * Returns true if a person with the same identity as {@code person} exists in Notor.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if a person with the same identity as {@code person} exists in Notor archive.
     */
    boolean hasArchive(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the Notor.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in Notor.
     */
    void createPerson(Person person);

    /**
     * Archives the given person.
     * {@code person} must exist in Notor.
     */
    void archivePerson(Person person);

    /**
     * Archives all currently displayed persons in Notor.
     */
    void archiveAllPersons();

    /**
     * Unarchives the given person.
     * {@code person} must exist in Notor archive.
     */
    void unarchivePerson(Person person);

    /**
     * Finds the given person.
     */
    Person findPerson(String name);

    /**
     * Returns true if the SuperGroup exist.
     */
    boolean hasSuperGroup(SuperGroup superGroup);

    void addSuperGroup(SuperGroup superGroup);

    void addSuperGroup(String superGroup) throws ParseException;

    void deleteSuperGroup(SuperGroup superGroup);

    Group findGroup(String name);

    void deleteSubGroup(SubGroup subGroup);

    void addSubGroup(Index index, SubGroup subGroup);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in Notor.
     * The person identity of {@code editedPerson} must not be the same as another existing person in Notor.
     */
    void setPerson(Person target, Person editedPerson);

    //=========== Filtered Person List Accessors =========================================

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns an unmodifiable view of the filtered group list.
     */
    ObservableList<? extends Group> getFilteredGroupList();

    /**
     * Updates the list to all SuperGroups.
     */
    void listSuperGroup();

    /**
     * Updates the list to all Subgroups.
     */
    void listSubGroup(Index i);

    /**
     * Updates the filter to  {@code predicate}.
     *
     */
    void updateFilteredGroupList(Predicate<Group> predicate);

    //=========== View Change ============================================================
    /**
     * Updates Notor to display Persons.
     */
    void displayPersons();


    /**
     * Updates Notor to display Person archive.
     */
    void displayPersonArchive();

    //=========== View Check =============================================================
    boolean isPersonList();

    boolean isSuperGroupList();

    boolean isArchiveList();
}
