package seedu.fast.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.fast.commons.core.GuiSettings;
import seedu.fast.model.person.Person;

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
     * Returns the user prefs' address book file path.
     */
    Path getFastFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setFastFilePath(Path addressBookFilePath);

    /**
     * Replaces FAST data with the data in {@code FAST}.
     */
    void setFast(ReadOnlyFast fast);

    /** Returns FAST */
    ReadOnlyFast getFast();

    /**
     * Returns true if a person with the same identity as {@code person} exists in FAST.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in FAST.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in FAST.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in FAST.
     * The person identity of {@code editedPerson} must not be the same as another existing person in FAST.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Sorts the list of persons by the given comparator
     */
    void sortPerson(Comparator<Person> comparator);
}
