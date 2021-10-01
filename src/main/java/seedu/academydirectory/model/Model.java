package seedu.academydirectory.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.academydirectory.commons.core.GuiSettings;
import seedu.academydirectory.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
     * Returns the user prefs' academy directory file path.
     */
    Path getAcademyDirectoryFilePath();

    /**
     * Sets the user prefs' academy directory file path.
     */
    void setAcademyDirectoryFilePath(Path academyDirectoryFilePath);

    /**
     * Replaces academy directory data with the data in {@code academyDirectory}.
     */
    void setAcademyDirectory(ReadOnlyAcademyDirectory academyDirectory);

    /** Returns the AcademyDirectory */
    ReadOnlyAcademyDirectory getAcademyDirectory();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the academy directory.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the academy directory.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the academy directory.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the academy directory.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the academy directory.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);
}
