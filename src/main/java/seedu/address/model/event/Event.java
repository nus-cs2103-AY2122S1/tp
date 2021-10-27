package seedu.address.model.event;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.participant.Participant;

/**
 * This is an Event class.
 */
public class Event implements Comparable<Event> {

    public static final String COMPLETED = "Completed";
    public static final String UNCOMPLETED = "Uncompleted";
    private BooleanProperty isDone;
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
        this.isDone = new SimpleBooleanProperty(false);
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
        this.isDone = new SimpleBooleanProperty(isDone);
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

    public String getTimeDisplayString() {
        return eventTime.getTimeDisplayString();
    }

    public boolean getDoneValue() {
        return isDone.get();
    }

    public Observable getIsDone() {
        return isDone;
    }

    public ObservableList<Participant> getParticipants() {
        return participants;
    }

    /**
     * Re-links the participant of this event to the newly edited event.
     *
     * @param editedEvent  An instance of the newly edited event.
     */
    public void shiftParticipants(Event editedEvent) {
        for (int i = 0; i < participants.size(); i++) {
            participants.get(i).replaceEvent(this, editedEvent);
        }
    }

    /**
     * Replaces a Participant with an edited Participant.
     *
     * @param oldParticipant     An instance of an existing Participant in this Event that was edited.
     * @param editedParticipant  The newly edited Participant to replace the existing Participant.
     */
    public void replaceParticipant(Participant oldParticipant, Participant editedParticipant) {
        participants.remove(oldParticipant);
        participants.add(editedParticipant);
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
        participant.deleteEvent(this);
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
     * Marks the event as done.
     */
    public void markAsDone() {
        this.isDone.set(true);
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
                && otherEvent.getName().equals(getName())
                && otherEvent.getDate().equals(getDate());
    }

    /**
     * Removes this {@code Event} from the {@code participants} participating in it.
     */
    public void deleteFromParticipants() {
        for (int i = participants.size() - 1; i >= 0; i--) {
            participants.get(i).deleteEvent(this);
        }
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
                && otherEvent.getDoneValue() == (getDoneValue())
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
                "Event Name: %s\nEvent Date: %s\nEvent Time: %s\nCompletion Status: %s\nNumber of participants: %d",
                getNameString(),
                getDateString(),
                getTimeString(),
                getDoneValue() ? "Completed" : "Uncompleted",
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
