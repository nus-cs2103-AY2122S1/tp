package nustracker.model.student;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import nustracker.model.Model;
import nustracker.model.event.Event;
import nustracker.model.event.EventName;

/**
 * This class represent any event(s) that a person is a part of.
 */
public class EnrolledEvents {

    public static final String MESSAGE_CONSTRAINTS = "Events can take any values, and it should not be blank";

    // Design:
    // We use a HashMap with EventName as keys because events are usually found by its name.
    // If we use a Set, we have to get the event object first in order to search for it.
    // We can do this because all events have unique names.
    // CAUTION: THE EVENT LIST HERE MAY BE OUTDATED, THE MOST UPDATED ONE IS IN MODEL
    // BECAUSE WHEN SETTING NEW EVENT OBJECT THIS DOES NOT GET EDITED
    private HashMap<EventName, Event> eventsEnrolledIn;

    /**
     * Constructs a new {@code EnrolledEvents}.
     */
    public EnrolledEvents() {
        eventsEnrolledIn = new HashMap<>();
    }

    /**
     * Constructs a new {@code EnrolledEvents} with the given events.
     *
     * @param events the events the student is to be enrolled in.
     */
    public EnrolledEvents(Event... events) {
        eventsEnrolledIn = new HashMap<EventName, Event>();
        for (Event event : events) {
            eventsEnrolledIn.put(event.getName(), event);
        }
    }

    /**
     * Gets a new EnrolledEvents with this student's enrolled events as well as the specified {@code Event} added.
     *
     * @param event A valid event to be added.
     * @return new instance of EnrolledEvents that contains the new event.
     */
    public EnrolledEvents enrollIntoEvent(Event event) {
        requireNonNull(event);
        EventName eventName = event.getName();

        HashMap<EventName, Event> updatedEventHashMap = new HashMap<>();
        for (Event currEvent : eventsEnrolledIn.values()) {
            updatedEventHashMap.put(currEvent.getName(), currEvent);
        }

        updatedEventHashMap.put(eventName, event);
        return of(updatedEventHashMap);
    }

    private EnrolledEvents of(HashMap<EventName, Event> eventsEnrolledIn) {
        EnrolledEvents enrolledEvents = new EnrolledEvents();
        enrolledEvents.eventsEnrolledIn = eventsEnrolledIn;

        return enrolledEvents;
    }

    /**
     * Gets a new EnrolledEvents with the specified {@code Event} removed.
     *
     * @param event A valid event to be removed.
     * @return the new instance of EnrolledEvents.
     */
    public EnrolledEvents removeFromEvent(Event event) {
        requireNonNull(event);
        EventName eventName = event.getName();

        HashMap<EventName, Event> updatedEventHashMap = new HashMap<>();
        for (Event currEvent : eventsEnrolledIn.values()) {
            updatedEventHashMap.put(currEvent.getName(), currEvent);
        }

        updatedEventHashMap.remove(eventName);
        return of(updatedEventHashMap);

    }

    /**
     * Checks if there are enrolled events.
     * @return true if there is 1 or more events enrolled.
     */
    public boolean hasEvents() {
        return eventsEnrolledIn.size() > 0;
    }

    /**
     * Gets a String that lists out all the events that are enrolled in.
     * @return the String with event names delimited using commas.
     */
    public String getEventNamesString() {
        return eventsEnrolledIn.values().stream().map(Event::getName).map(EventName::getEventName)
                .collect(Collectors.joining(", "));
    }

    /**
     * Returns an immutable {@code Event} Set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @param model the model to get the most up-to-date {@code Event} from.
     * @return the Set of {@code Event} that is in this {@code EnrolledEvents}.
     */
    public Set<Event> getAllEventsEnrolledIn(Model model) {
        Set<Event> allEventSet = new HashSet<>();

        for (Event currEvent : eventsEnrolledIn.values()) {
            Event upToDateEvent = model.getEvent(currEvent.getName());

            allEventSet.add(upToDateEvent);
        }

        return allEventSet;
    }

    /**
     * Checks if the student is enrolled in a specific event.
     *
     * @param eventName the name of the event.
     * @return true if student is enrolled in event, false otherwise.
     */
    public boolean isEnrolledInEvent(EventName eventName) {
        requireNonNull(eventName);
        return eventsEnrolledIn.containsKey(eventName);
    }


    @Override
    public String toString() {
        // Prints list of events nicely separated by commas
        return getEventNamesString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EnrolledEvents // instanceof handles nulls
                && eventsEnrolledIn.equals(((EnrolledEvents) other).eventsEnrolledIn)); // state check
    }

    // If 2 students are enrolled in the same set of events they will have the same hash code.
    @Override
    public int hashCode() {
        return eventsEnrolledIn.hashCode();
    }
}
