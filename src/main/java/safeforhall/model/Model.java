package safeforhall.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import safeforhall.commons.core.GuiSettings;
import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.model.event.Event;
import safeforhall.model.event.EventName;
import safeforhall.model.event.ResidentList;
import safeforhall.model.person.Person;

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
     * Returns true if a person with the exact same identity as {@code person} exists in the address book.
     */
    boolean hasExactPerson(Person person);

    /**
     * Searches and converts the names in the {@code residentList} to a list of person and return the list of person
     * if it exists in the address book.
     */
    ArrayList<Person> toPersonList(ResidentList residentList) throws CommandException;


    ArrayList<Person> getCurrentEventResidents(ResidentList residentList) throws CommandException;

    /**
     * Returns a String of information if {@code Person} does not exist in the address book, return
     * an empty String otherwise.
     */
    String getInvalidResident(Event event) throws CommandException;

    /**
     * Returns true if an event with the same details as {@code event} exists in the address book.
     */
    boolean hasEvent(Event event);

    Event getEvent(EventName eventName) throws CommandException;

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Deletes the given event.
     * The event must exist in the address book.
     */
    void deleteEvent(Event target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Adds the given event.
     * {@code event} must not already exist in the address book.
     */
    void addEvent(Event event);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Replaces the event {@code target} in the list with {@code editedEvent}.
     * {@code target} must exist in the list.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the list.
     */
    void setEvent(Event target, Event editedEvent);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered event list */
    ObservableList<Event> getFilteredEventList();

    /** Adds the selected resident into the filtered resident list */
    void setSinglePerson(Person person);

    /** Set an empty person list */
    void setNoPerson();

    /** Returns an unmodifiable view of the selected resident */
    ObservableList<Person> getSinglePerson();

    /** Adds the selected event into the filtered event list */
    void setSingleEvent(Event event);

    /** Set an empty event list */
    void setNoEvent();

    /** Returns an unmodifiable view of the selected event */
    ObservableList<Event> getSingleEvent();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the filter of the filtered event list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<Event> predicate);

    ObservableList<Person> getSortedPersonList();

    ObservableList<Event> getSortedEventList();

    void updateSortedPersonList(Comparator<Person> comparator);

    void updateSortedEventList(Comparator<Event> comparator);

    /**
     * Returns an array list of events the specified person is in.
     * @param person The person to search for in events
     * @param predicate A predicate to filter given events by
     * @return The array list of events
     */
    ArrayList<Event> getPersonEvents(Person person, Predicate<Event> predicate);
}
