package seedu.address.model.event;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.participant.Participant;

/**
 * This is an Event class.
 */
public class Event implements Comparable<Event> {

    public static final String COMPLETED = "Completed";
    public static final String UNCOMPLETED = "Uncompleted";
    private boolean isDone;
    private ObservableList<Participant> participants = FXCollections.observableArrayList();
    private EventName eventName;
    private EventDate eventDate;
    private EventTime eventTime;

    /**
     * This is the constructor of an Event.
     *
     * @param name of the Event.
     * @param date at which the Event occurs.
     * @param time of the Event.
     */
    public Event(EventName name, EventDate date, EventTime time) {
        this.eventName = name;
        this.eventDate = date;
        this.eventTime = time;
        isDone = false;
    }

    /**
     * This is an overloaded constructor of an Event.
     *
     * @param name of the Event.
     * @param date at which the Event occurs.
     * @param time of the Event.
     * @param isDone A boolean to indicate is the event is done.
     * @param participants participants to be added
     */
    public Event(EventName name, EventDate date, EventTime time, boolean isDone, List<Participant> participants) {
        requireNonNull(participants);
        this.eventName = name;
        this.eventDate = date;
        this.eventTime = time;
        this.isDone = isDone;
        this.participants.addAll(participants);
    }

    /**
     * This is an overloaded constructor of Event.
     *
     * @param name of the Event.
     * @param date at which the Event occurs.
     */
    public Event(EventName name, EventDate date) {
        this(name, date, new EventTime());
    }

    public EventName getName() {
        return eventName;
    }

    public String getNameString() {
        return eventName.toString();
    }

    public EventDate getDate() {
        return eventDate;
    }

    public String getDateString() {
        return eventDate.toString();
    }

    public EventTime getTime() {
        return eventTime;
    }

    public String getTimeString() {
        return eventTime.toString();
    }

    public boolean getIsDone() {
        return isDone;
    }

    public ObservableList<Participant> getParticipants() {
        return participants;
    }

    /**
     * Adds the given participant to the list of participants.
     *
     * @param participant The participant to be added.
     */
    public void addParticipant(Participant participant) {
        requireNonNull(participant);
        this.participants.add(participant);
        participant.addEvent(this);
    }

    /**
     * Removes the given participant from the list of participants.
     *
     * @param participant The participant to be removed.
     */
    public void removeParticipant(Participant participant) {
        requireNonNull(participant);
        this.participants.remove(participant);
        participant.removeEvent(this);
    }

    /** Returns true if the given participant is attending this event.
     *
     * @param participant The given participant.
     * @return True if the participant is attending.
     */
    public boolean hasParticipant(Participant participant) {
        requireNonNull(participant);
        return this.participants.stream().anyMatch(p -> p.isSameParticipant(participant));
    }

    /**
     * Returns a copy of the Event that is marked done.
     *
     * @return An Event that is marked done.
     */
    public Event markAsDone() {
        return new Event(eventName, eventDate, eventTime, true, participants);
    }

    /**
     * Returns true if both events have the same name.
     * This defines a weaker notion of equality between two events.
     *
     * @param otherEvent to be compared to this event.
     * @return A boolean to indicate if the events are the same or has the same name.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && otherEvent.getName().equals(getName());
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
        return otherEvent.getName().equals(getName())
                && otherEvent.getDate().equals(getDate())
                && otherEvent.getTime().equals(getTime())
                && otherEvent.getIsDone() == (getIsDone())
                && otherEvent.getParticipants().equals(getParticipants());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(eventName, isDone, eventDate, eventTime, participants);
    }

    @Override
    public String toString() {
        return String.format(
                "Event Name: %s\nEvent Date: %s\nEvent Time: %s\nCompletion Status: %s\nNumber of Participants: %d", 
                getNameString(),
                getDateString(),
                getTimeString(),
                getIsDone() ? "Completed" : "Uncompleted",
                getParticipants().size());
    }

    @Override
    public int compareTo(Event o) {
        int compareDateResult = eventDate.compareTo(o.eventDate);
        if (compareDateResult == 0) { // same date
            int compareTimeResult = eventTime.compareTo(o.eventTime);
            if (compareTimeResult == 0) { // same time
                return eventName.compareTo(o.eventName);
            } else {
                return compareTimeResult;
            }
        } else {
            return compareDateResult;
        }
    }
}
