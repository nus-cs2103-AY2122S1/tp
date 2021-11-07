package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Represents an Event in ForYourInterest.
 * Guarantees: event name is not null.
 */
public class Event {

    // Identity fields
    private final EventName eventName;

    // Data fields
    private final UniquePersonList attendees;

    /**
     * Creates an event.
     *
     * @param eventName the event name to be used.
     */
    public Event(EventName eventName) {
        requireAllNonNull(eventName);
        this.eventName = eventName;
        this.attendees = new UniquePersonList();
    }

    /**
     * Creates an event.
     *
     * @param eventName the event name to be used.
     * @param attendees the attendees to be used.
     */
    public Event(EventName eventName, UniquePersonList attendees) {
        requireAllNonNull(eventName, attendees);
        this.eventName = eventName;
        this.attendees = attendees;
    }

    /**
     * Returns a copy of the event name.
     *
     * @return A copy of the event name.
     */
    public EventName getEventName() {
        requireAllNonNull(eventName);
        return eventName.copy();
    }

    /**
     * Returns the attendee list as an unmodifiable observer list.
     *
     * @return Unmodifiable observable list.
     */
    public ObservableList<Person> getAttendeesAsUnmodifiableObservableList() {
        requireAllNonNull(attendees);
        return attendees.asUnmodifiableObservableList();
    }

    /**
     * Returns a copy of attendee list.
     *
     * @return A copy of the attendee list.
     */
    public UniquePersonList getAttendees() {
        requireAllNonNull(attendees);
        UniquePersonList newList = new UniquePersonList();
        newList.setPersons(attendees);
        return newList;
    }

    /**
     * Returns true if event contains and equivalent person as the given argument.
     *
     * @param person The person to check.
     * @return true if event contains and equivalent person as the given argument.
     */
    public boolean hasPerson(Person person) {
        requireAllNonNull(person);
        return attendees.contains(person);
    }

    /**
     * Adds a {@code Person} as an attendee to the event.
     *
     * @param person The person to be added.
     */
    public void addPerson(Person person) {
        requireAllNonNull(person);
        attendees.add(person);
    }

    /**
     * Removes a {@code Person} as an attendee to the event.
     *
     * @param person The person to be removed.
     */
    public void removePerson(Person person) {
        requireAllNonNull(person);
        attendees.remove(person);
    }

    /**
     * Returns true if both events have the same event name.
     * This defines a weaker notion of equality between two events.
     *
     * @param otherEvent The other event to compare with.
     * @return true if both events have same event name.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && otherEvent.getEventName().equals(getEventName());
    }

    /**
     * Returns true if both events have the same identity and data fields.
     *
     * @param other The other object to compare with.
     * @return true if both events have the same identity and data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) other;
        return otherEvent.getEventName().equals(getEventName())
                && otherEvent.getAttendees().equals(getAttendees());
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName, attendees);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getEventName());
        if (!attendees.isEmpty()) {
            builder.append("; Attendees: ");
            attendees.forEach(builder::append);
        }
        return builder.toString();
    }
}
