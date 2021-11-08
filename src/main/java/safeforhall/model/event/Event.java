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
    // Identity fields
    private final EventName eventName;
    private final EventDate eventDate;
    private final EventTime eventTime;
    private final Venue venue;
    private final Capacity capacity;
    private final ResidentList residents;

    /**
     * Every field must be present
     */
    public Event(EventName eventName, EventDate eventDate, EventTime eventTime, Venue venue, Capacity capacity,
                 ResidentList residents) {
        requireAllNonNull(eventName, eventDate, venue, capacity);
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
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

    public ResidentList getResidentList() {
        return residents;
    }

    public EventTime getEventTime() {
        return eventTime;
    }

    public ArrayList<String> getStringResidentList() {
        return residents.getStringResidentList();
    }

    /**
     * Returns true if both events have the same name, date, venue, capacity and time.
     * This defines a notion of equality between two events.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && otherEvent.getEventName().equals(getEventName())
                && otherEvent.getEventDate().equals(getEventDate())
                && otherEvent.getEventTime().equals(getEventTime())
                && otherEvent.getVenue().equals(getVenue());
    }

    public String getCombinedStorageString(ArrayList<Person> toAdd) {
        return residents.getCombinedStorageString(toAdd);
    }

    public String getCombinedDisplayString(ArrayList<Person> toAdd) {
        return residents.getCombinedDisplayString(toAdd);
    }

    public String getRemovedStorageString(ArrayList<Person> toRemove) {
        return residents.getRemovedStorageString(toRemove);
    }

    public String getRemovedDisplayString(ArrayList<Person> toRemove) {
        return residents.getRemovedDisplayString(toRemove);
    }

    /**
     * Checks if residents attending the event are vaccinated
     * @return Returns true if any {@code resident} in the {@code Event} is not vaccinated
     */
    public boolean hasUnvaccinatedResident() {
        return residents.hasUnvaccinatedResident();
    }

    /**
     * Counts the number of unvaccinated residents in the {@code Event}
     * @return Returns the number of unvaccinated residents in the {@code Event}
     */
    public int numOfUnvaccinatedResidents() {
        return residents.numOfUnvaccinatedResidents();
    }

    /**
     * Returns true if the event is over.
     */
    public boolean isOver() {
        return eventDate.isPast();
    }

    /**
     * Returns true if the given eventName is same as the eventName of the current instance of Event
     */
    public boolean hasSameEventName(EventName eventName) {
        return getEventName().equals(eventName);
    }

    /**
     * Returns true if the {@code ResidentList} of the {@code Event} is empty.
     */
    public boolean hasNoResidents() {
        return residents.isEmpty();
    }

    /**
     * Returns the number of residents currently in the event.
     */
    public int getResidentListSize() {
        return this.residents.getResidentListSize();
    }

    /**
     * Returns true if both events have the same identity and data fields.
     * This defines a stronger notion of equality between two events.
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
                && otherEvent.getEventDate().equals(getEventDate())
                && otherEvent.getEventTime().equals(getEventTime())
                && otherEvent.getVenue().equals(getVenue())
                && otherEvent.getCapacity().equals(getCapacity());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(eventName, eventDate, eventTime, venue, capacity);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getEventName())
                .append("; Date: ")
                .append(getEventDate())
                .append("; Time: ")
                .append(getEventTime())
                .append("; Venue: ")
                .append(getVenue())
                .append("; Capacity: ")
                .append(getCapacity())
                .append("; Residents: ")
                .append(getResidentList());
        return builder.toString();
    }
}
