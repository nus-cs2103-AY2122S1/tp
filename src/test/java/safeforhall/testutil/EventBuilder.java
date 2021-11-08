package safeforhall.testutil;

import safeforhall.model.event.Capacity;
import safeforhall.model.event.Event;
import safeforhall.model.event.EventDate;
import safeforhall.model.event.EventName;
import safeforhall.model.event.EventTime;
import safeforhall.model.event.ResidentList;
import safeforhall.model.event.Venue;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {
    public static final String DEFAULT_EVENT_NAME = "Football Training";
    public static final String DEFAULT_EVENT_DATE = "20-10-2021";
    public static final String DEFAULT_EVENT_TIME = "0800";
    public static final String DEFAULT_VENUE = "Field";
    public static final String DEFAULT_CAPACITY = "20";
    public static final String DEFAULT_RESIDENT_LIST = "None";

    private EventName eventName;
    private EventDate eventDate;
    private EventTime eventTime;
    private Venue venue;
    private Capacity capacity;
    private ResidentList residents;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        eventName = new EventName(DEFAULT_EVENT_NAME);
        eventDate = new EventDate(DEFAULT_EVENT_DATE);
        eventTime = new EventTime(DEFAULT_EVENT_TIME);
        venue = new Venue(DEFAULT_VENUE);
        capacity = new Capacity(DEFAULT_CAPACITY);
        residents = new ResidentList(DEFAULT_RESIDENT_LIST);
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        eventName = eventToCopy.getEventName();
        eventDate = eventToCopy.getEventDate();
        eventTime = eventToCopy.getEventTime();
        venue = eventToCopy.getVenue();
        capacity = eventToCopy.getCapacity();
        residents = eventToCopy.getResidentList();
    }

    /**
     * Sets the {@code eventName} of the {@code Event} that we are building.
     */
    public EventBuilder withEventName(String eventName) {
        this.eventName = new EventName(eventName);
        return this;
    }

    /**
     * Sets the {@code EventDate} of the {@code Event} that we are building.
     */
    public EventBuilder withEventDate(String eventDate) {
        this.eventDate = new EventDate(eventDate);
        return this;
    }

    /**
     * Sets the {@code EventTime} of the {@code Event} that we are building.
     */
    public EventBuilder withEventTime(String eventTime) {
        this.eventTime = new EventTime(eventTime);
        return this;
    }

    /**
     * Sets the {@code Venue} of the {@code Event} that we are building.
     */
    public EventBuilder withVenue(String venue) {
        this.venue = new Venue(venue);
        return this;
    }

    /**
     * Sets the {@code Capacity} of the {@code Event} that we are building.
     */
    public EventBuilder withCapacity(String capacity) {
        this.capacity = new Capacity(capacity);
        return this;
    }

    /**
     * Sets the {@code ResidentList} of the {@code Event} that we are building.
     */
    public EventBuilder withResidentList(String residentsDisplay, String residentStorage) {
        this.residents = new ResidentList(residentsDisplay, residentStorage);
        return this;
    }

    public Event build() {
        return new Event(eventName, eventDate, eventTime, venue, capacity, residents);
    }
}
