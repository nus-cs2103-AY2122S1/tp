package safeforhall.model.event;

import static safeforhall.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents an Event in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event {

    // Identity fields
    private final EventName eventName;
    private final EventDate eventDate;
    private final Venue venue;
    private final Capacity capacity;
    // test

    /**
     * Every field must be present
     */
    public Event(EventName eventName, EventDate eventDate, Venue venue, Capacity capacity) {
        requireAllNonNull(eventName, eventDate, venue, capacity);
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.venue = venue;
        this.capacity = capacity;
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

    /**
     * Returns true if both events have the same name, date and venue.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && otherEvent.getEventName().equals(getEventName())
                && otherEvent.getEventDate().equals(getEventDate())
                && otherEvent.getVenue().equals(getVenue());
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
