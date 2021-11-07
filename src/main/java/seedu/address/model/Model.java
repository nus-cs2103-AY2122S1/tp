package seedu.address.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventNameEqualKeywordPredicate;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.person.NameEqualKeywordPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Event> PREDICATE_SHOW_ALL_EVENTS = unused -> true;

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
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns a list of persons matching the person predicate passed.
     *
     * @param personPredicate The predicate used to match persons retrieved.
     * @return List of person matching the predicate.
     */
    List<Person> getPersons(Predicate<Person> personPredicate);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns true if an event with the same identity as {@code event} exists.
     *
     * @param event The event to check.
     * @return true if event exists.
     */
    boolean hasEvent(Event event);

    /**
     * Deletes the given event.
     * The event must exist in the address book.
     *
     * @param target The event to be deleted.
     */
    void deleteEvent(Event target);

    /**
     * Clears all events from the address book.
     */
    void clearAllEvent();

    /**
     * Adds the given event.
     * {@code event} must not already exist in the address book.
     *
     * @param event The event to be added.
     */
    void addEvent(Event event);

    /**
     * Replaces the given {@code target} with {@code editedEvent}.
     * {@code target} must exist in the address book.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the address book.
     *
     * @param target The target event.
     * @param editedEvent The edited event.
     */
    void setEvent(Event target, Event editedEvent);

    /**
     * Returns a list of events matching the event predicate passed.
     *
     * @param eventPredicate The predicate used to match events retrieved.
     * @return List of events matching the predicate.
     */
    List<Event> getEvents(Predicate<Event> eventPredicate);

    /**
     * Returns an unmodifiable view of the filtered event list
     *
     * @return Filtered event list.
     */
    ObservableList<Event> getFilteredEventList();

    /**
     * Updates the filter of the filtered event list to filter by the given {@code predicate}.
     *
     * @param predicate The predicate to filter with.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<Event> predicate);

    /**
     * Adds a person to a specified event. Since event name and name are unique, there should only be 1 event and
     * name matched.
     *
     * @param personPredicate Person predicate to match Person.
     * @param eventPredicate Event predicate to match event.
     * @throws PersonNotFoundException if {@code Person} does not exist in address book.
     * @throws EventNotFoundException if {@code Event} does not exist in address book.
     * @throws DuplicatePersonException if {@code Person} already exists in event.
     */
    void addPersonToEvent(NameEqualKeywordPredicate personPredicate, EventNameEqualKeywordPredicate eventPredicate);
}
