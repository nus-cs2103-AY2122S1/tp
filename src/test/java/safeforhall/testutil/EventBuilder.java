package safeforhall.testutil;

import safeforhall.model.event.Capacity;
import safeforhall.model.event.Event;
import safeforhall.model.event.EventDate;
import safeforhall.model.event.EventName;
<<<<<<< HEAD
import safeforhall.model.event.ResidentList;
import safeforhall.model.event.Venue;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {
    public static final String DEFAULT_EVENT_NAME = "training";
    public static final String DEFAULT_EVENT_DATE = "18-10-2021";
    public static final String DEFAULT_VENUE = "gym";
    public static final String DEFAULT_CAPACITY = "5";
    public static final String DEFAULT_RESIDENT_LIST = "T";

    private EventName eventName;
    private EventDate eventDate;
    private Venue venue;
    private Capacity capacity;
    private ResidentList residents;
=======
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
>>>>>>> 41e174c566bec184c75e1c819c7655acd34d11ea

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
<<<<<<< HEAD
        eventName = new EventName(DEFAULT_EVENT_NAME);
        eventDate = new EventDate(DEFAULT_EVENT_DATE);
        venue = new Venue(DEFAULT_VENUE);
        capacity = new Capacity(DEFAULT_CAPACITY);
        residents = new ResidentList(DEFAULT_RESIDENT_LIST);
=======
        name = new EventName(DEFAULT_NAME);
        date = new EventDate(DEFAULT_DATE);
        venue = new Venue(DEFAULT_VENUE);
        capacity = new Capacity(DEFAULT_CAPACITY);
>>>>>>> 41e174c566bec184c75e1c819c7655acd34d11ea
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
<<<<<<< HEAD
        eventName = eventToCopy.getEventName();
        eventDate = eventToCopy.getEventDate();
        venue = eventToCopy.getVenue();
        capacity = eventToCopy.getCapacity();
        residents = eventToCopy.getResidents();
    }

    /**
     * Sets the {@code eventName} of the {@code Event} that we are building.
     */
    public EventBuilder withEventName(String eventName) {
        this.eventName = new EventName(eventName);
=======
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
>>>>>>> 41e174c566bec184c75e1c819c7655acd34d11ea
        return this;
    }

    /**
<<<<<<< HEAD
     * Sets the {@code EventDate} of the {@code Event} that we are building.
     */
    public EventBuilder withEventDate(String eventDate) {
        this.eventDate = new EventDate(eventDate);
=======
     * Sets the {@code Date} of the {@code Event} that we are building.
     */
    public EventBuilder withDate(String date) {
        this.date = new EventDate(date);
>>>>>>> 41e174c566bec184c75e1c819c7655acd34d11ea
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
<<<<<<< HEAD
     * Sets the {@code Capacity} of the {@code Event} that we are building.
=======
     * Sets the {@code Email} of the {@code Person} that we are building.
>>>>>>> 41e174c566bec184c75e1c819c7655acd34d11ea
     */
    public EventBuilder withCapacity(String capacity) {
        this.capacity = new Capacity(capacity);
        return this;
    }

<<<<<<< HEAD
    /**
     * Sets the {@code ResidentList} of the {@code Event} that we are building.
     */
    public EventBuilder withResidentList(String residents) {
        this.residents = new ResidentList(residents);
        return this;
    }

    public Event build() {
        return new Event(eventName, eventDate, venue, capacity, residents);
    }
=======
    public Event build() {
        return new Event(name, date, venue, capacity);
    }

>>>>>>> 41e174c566bec184c75e1c819c7655acd34d11ea
}
