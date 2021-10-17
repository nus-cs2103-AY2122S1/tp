package safeforhall.model.event;

import static safeforhall.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Objects;

import safeforhall.model.person.Person;

/**
 * Represents an Event in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event {
    public static final String RESIDENTS_DESC = "Residents: ";

    // Identity fields
    private final EventName eventName;
    private final EventDate eventDate;
    private final Venue venue;
    private final Capacity capacity;
    private final ResidentList residents;

    /**
     * Every field must be present
     */
    public Event(EventName eventName, EventDate eventDate, Venue venue, Capacity capacity,
                 ResidentList residents) {
        requireAllNonNull(eventName, eventDate, venue, capacity);
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.venue = venue;
        this.capacity = capacity;
        this.residents = residents;
    }

    public EventName getEventName() {
        return eventName;
    }

    public EventDate getEventDate() {
        return eventDate;
    }

    public Venue getVenue() {
        return venue;
    }

    public Capacity getCapacity() {
        return capacity;
    }

    public ResidentList getResidents() {
        return residents;
    }

    /**
     * Returns true if both events have the same name, date, venue and capacity.
     * This defines a weaker notion of equality between two events.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && otherEvent.getEventName().equals(getEventName())
                && otherEvent.getEventDate().equals(getEventDate())
                && otherEvent.getVenue().equals(getVenue())
                && otherEvent.getCapacity().equals(getCapacity());
    }


    public String addResidentsToEvent(ArrayList<Person> current, ArrayList<Person> toAdd) {
        return residents.addResidentList(current, toAdd);
    }

    /**
     * Returns true if the given eventName is same as the eventName of the current instance of Event
     */
    public boolean hasSameEventName(EventName eventName) {
        return getEventName().equals(eventName);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(eventName, eventDate, venue, capacity);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getEventName())
                .append("; Date: ")
                .append(getEventDate())
                .append("; Venue: ")
                .append(getVenue())
                .append("; Capacity: ")
                .append(getCapacity());

        return builder.toString();
    }
}
