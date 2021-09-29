package seedu.address.model.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import seedu.address.model.participant.Participant;

/**
 * This is an Event class.
 */
public class Event implements Comparable<Event> {

    private boolean isDone = false;
    private ArrayList<Participant> participants = new ArrayList<>();
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
        return this.eventName;
    }

    public EventDate getDate() {
        return this.eventDate;
    }

    public EventTime getTime() {
        return this.eventTime;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(this.participants);
    }

    /**
     * Marks the Event as done.
     */
    public void markAsDone() {
        this.isDone = true;
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
        final StringBuilder builder = new StringBuilder();
        builder.append("[").append((isDone ? "X" : " ")).append("] ")
                .append(eventName.toString()).append(" (at: ")
                .append(eventDate.toString()).append(" ")
                .append(eventTime.toString())
                .append(") ")
                .append("Total Participants: ").append(this.participants.size());
        return builder.toString();
    }

    @Override
    public int compareTo(Event o) {
        int compareDateResult = this.eventDate.date.compareTo(o.eventDate.date);
        if (compareDateResult == 0) { // same date
            int compareTimeResult = this.eventTime.time.compareTo(o.eventTime.time);
            if (compareTimeResult == 0) { // same time
                return this.eventName.eventName.compareTo(o.eventName.eventName);
            } else {
                return compareTimeResult;
            }
        } else {
            return compareDateResult;
        }
    }
}
