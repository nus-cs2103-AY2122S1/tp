package seedu.address.testutil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.model.participant.Participant;

public class EventBuilder {

    public static final String DEFAULT_EVENT_NAME = "Sleep";
    public static final String DEFAULT_EVENT_DATE = "2021-09-18";
    public static final String DEFAULT_EVENT_TIME = "1000";

    private ObservableList<Participant> participants = FXCollections.observableArrayList();
    private EventName eventName;
    private EventDate eventDate;
    private EventTime eventTime;

    /**
     * Creates an {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        this.eventName = new EventName(DEFAULT_EVENT_NAME);
        this.eventDate = new EventDate(DEFAULT_EVENT_DATE);
        this.eventTime = new EventTime(DEFAULT_EVENT_TIME);
    }

    /**
     * Adds a Participant to the list of participants.
     *
     * @param participant to be added.
     */
    public void addParticipant(Participant participant) {
        this.participants.add(participant);
    }

    /**
     * Returns a new instance of Event with the details set in {@code EventBuilder}.
     *
     * @return an Event.
     */
    public Event build() {
        return new Event(this.eventName, this.eventDate, this.eventTime, false, this.participants);
    }

    /**
     * Sets the {@code Name} of the {@code Event} that we are building.
     */
    public EventBuilder withEventName(String name) {
        this.eventName = new EventName(name);
        return this;
    }
}
