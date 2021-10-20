package safeforhall.testutil;

import safeforhall.model.event.Capacity;
import safeforhall.model.event.Event;
import safeforhall.model.event.EventDate;
import safeforhall.model.event.EventName;
import safeforhall.model.event.Venue;


public class EventBuilder {

    public static final String DEFAULT_NAME = "Football Training";
    public static final String DEFAULT_DATE = "20-10-2021";
    public static final String DEFAULT_VENUE = "Field";
    public static final String DEFAULT_CAPACITY = "20";

    private EventName name;
    private EventDate date;
    private Venue venue;
    private Capacity capacity;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        name = new EventName(DEFAULT_NAME);
        date = new EventDate(DEFAULT_DATE);
        venue = new Venue(DEFAULT_VENUE);
        capacity = new Capacity(DEFAULT_CAPACITY);
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        name = eventToCopy.getEventName();
        date = eventToCopy.getEventDate();
        venue = eventToCopy.getVenue();
        capacity = eventToCopy.getCapacity();
    }

    /**
     * Sets the {@code Name} of the {@code Event} that we are building.
     */
    public EventBuilder withName(String name) {
        this.name = new EventName(name);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Event} that we are building.
     */
    public EventBuilder withDate(String date) {
        this.date = new EventDate(date);
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
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public EventBuilder withCapacity(String capacity) {
        this.capacity = new Capacity(capacity);
        return this;
    }

    public Event build() {
        return new Event(name, date, venue, capacity);
    }

}
