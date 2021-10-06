package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.event.Event;
import seedu.address.model.participant.Participant;

/**
 * The API of the Model component.
 */
public interface Model {

    /** {@code Predicate} that always evaluate to true */
    Predicate<Event> PREDICATE_SHOW_ALL_EVENTS = unused -> true;

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

    /**
     * Returns true if an Event with the same identity as {@code event} exists in Managera.
     */
    boolean hasEvent(Event event);

    /**
     * Adds the given Event {@code event}.
     */
    void addEvent(Event event);

    /**
     * Removes the given Event {@code target}.
     * {@code target} must exist in Managera.
     */
    void removeEvent(Event target);

    /**
     * Marks the given Event {@code target} as done.
     * {@code target} must exist in Managera.
     */
    void markEventAsDone(Event target);

    /** Returns an unmodifiable view of list of Events *///getFilteredEventList
    ObservableList<Event> getFilteredEventList();

    /**
     * Updates the filter of the filtered event list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<Event> predicate);

    /** Sorts the event lists chronologically */
    void sortEvents();
}
