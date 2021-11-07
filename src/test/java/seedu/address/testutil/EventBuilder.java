package seedu.address.testutil;

import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

public class EventBuilder {

    public static final String DEFAULT_EVENT_NAME = "CS2103 Party";

    private EventName eventName;
    private UniquePersonList attendees;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        eventName = new EventName(DEFAULT_EVENT_NAME);
        attendees = new UniquePersonList();
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        eventName = eventToCopy.getEventName();
        attendees = eventToCopy.getAttendees();
    }

    /**
     * Sets the {@code EventName} of the {@code Event} that we are building.
     */
    public EventBuilder withEventName(String eventName) {
        this.eventName = new EventName(eventName);
        return this;
    }

    /**
     * Adds a {@code Person} to the {@code UniquePersonList} of the {@code Event} that we are building.
     */
    public EventBuilder withPerson(Person person) {
        this.attendees.add(person);
        return this;
    }

    public Event build() {
        return new Event(eventName, attendees);
    }

}
