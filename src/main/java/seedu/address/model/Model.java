package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.event.Event;
import seedu.address.model.participant.Participant;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Participant> PREDICATE_SHOW_ALL_PARTICIPANTS = unused -> true;


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
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a Participant with the same identity as {@code participant} exists in Managera.
     */
    boolean hasParticipant(Participant participant);

    /**
     * Deletes the given Participant.
     * The Participant must exist in Managera.
     */
    void deleteParticipant(Participant target);

    /**
     * Adds the given Participant.
     * {@code participant} must not already exist in Managera.
     */
    void addParticipant(Participant participant);

    /**
     * Replaces the given Participant {@code target} with {@code editedParticipant}.
     * {@code target} must exist in Managera.
     * The Participant identity of {@code editedParticipant} must not be the same
     * as another existing Participant in Managera.
     */
    void setParticipant(Participant target, Participant editedParticipant);

    /** Returns an unmodifiable view of the filtered Participant list */
    ObservableList<Participant> getFilteredParticipantList();

    /**
     * Updates the filter of the filtered Participant list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredParticipantList(Predicate<Participant> predicate);

    /** Returns an unmodifiable view of list of Events */
    ObservableList<Event> getEventList();

    /** Sorts the event lists chronologically */
    void sortEvents();
}
