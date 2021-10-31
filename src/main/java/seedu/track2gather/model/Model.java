package seedu.track2gather.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.track2gather.commons.core.GuiSettings;
import seedu.track2gather.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Person> PREDICATE_SHOW_NON_CALLED = person -> !person.getCallStatus().isCalledInCurrentSession();

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
     * Returns the user prefs' contacts list file path.
     */
    Path getTrack2GatherFilePath();

    /**
     * Sets the user prefs' contacts list file path.
     */
    void setTrack2GatherFilePath(Path track2GatherFilePath);

    /**
     * Replaces contacts list data with the data in {@code track2Gather}.
     */
    void setTrack2Gather(ReadOnlyTrack2Gather track2Gather);

    /** Returns the Track2Gather */
    ReadOnlyTrack2Gather getTrack2Gather();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the contacts list.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the contacts list.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the contacts list.
     */
    void addPerson(Person person);

    /**
     * Sets all persons as non-called.
     */
    void resetAllCallStatuses();

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the contacts list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the contacts list.
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
     * Updates the sorting order of the filtered person list to sort by the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void updateSortedPersonList(Comparator<Person> comparator);
}
