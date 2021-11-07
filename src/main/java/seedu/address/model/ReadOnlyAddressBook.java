package seedu.address.model;

import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns a list of events matching the predicate given.
     *
     * @param eventPredicate The predicate used to match the events.
     * @return The list of events.
     */
    List<Event> getEvents(Predicate<Event> eventPredicate);

    /**
     * Returns an unmodifiable view of the events list.
     * This list will not contain any duplicate events.
     *
     * @return An observable list of events.
     */
    ObservableList<Event> getEventList();

    /**
     * Returns a list of persons matching the predicate given.
     *
     * @param personPredicate The predicate used to match the persons.
     * @return The list of persons.
     */
    List<Person> getPersons(Predicate<Person> personPredicate);

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
