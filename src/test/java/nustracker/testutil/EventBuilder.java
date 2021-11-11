package nustracker.testutil;

import java.util.HashSet;
import java.util.Set;

import nustracker.model.event.Event;
import nustracker.model.event.EventDate;
import nustracker.model.event.EventName;
import nustracker.model.event.EventTime;
import nustracker.model.event.Participant;
import nustracker.model.student.StudentId;
import nustracker.model.util.SampleDataUtil;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {

    public static final String DEFAULT_NAME = "Test event";
    public static final String DEFAULT_DATE = "09-10-2021";
    public static final String DEFAULT_TIME = "1530";

    private EventName name;
    private EventDate date;
    private EventTime time;
    private Set<Participant> participants;
    private Set<Participant> blacklist;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        name = new EventName(DEFAULT_NAME);
        date = new EventDate(DEFAULT_DATE);
        time = new EventTime(DEFAULT_TIME);
        participants = new HashSet<>();
        blacklist = new HashSet<>();
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event event) {
        name = event.getName();
        date = event.getDate();
        time = event.getTime();
        participants = event.getParticipants();
        blacklist = event.getBlacklist();
    }

    /**
     * Sets the {@code Name} of the {@code Event} that we are building.
     */
    public EventBuilder withName(String name) {
        this.name = new EventName(name);
        return this;
    }

    /**
     * Parses the {@code participants} into a {@code Set<Participants>} and set it to the {@code Event} that we are
     * building.
     */
    public EventBuilder withParticipants(String ... participants) {
        this.participants = SampleDataUtil.getParticipantSet(participants);
        return this;
    }

    /**
     * Parses the {@code blacklist} into a {@code Set<Participants>} and set it to the {@code Event} that we are
     * building.
     */
    public EventBuilder withBlacklist(String ... blacklist) {
        this.blacklist = SampleDataUtil.getParticipantSet(blacklist);
        return this;
    }

    /**
     * Parses the {@code participantSet} and set it to the {@code Event} that we are
     * building.
     */
    public EventBuilder withParticipantSet(Set<Participant> participantSet) {
        this.participants = participantSet;
        return this;
    }

    /**
     * Parses the {@code studentId} and add it as a {@code Participant} to the {@code Event} that we are
     * building.
     */
    public EventBuilder addParticipant(StudentId studentId) {
        Participant toAdd = new Participant(studentId.getStudentIdString());
        Set<Participant> updatedParticipants = new HashSet<>(participants);
        updatedParticipants.add(toAdd);
        participants = updatedParticipants;
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
     * Sets the {@code Time} of the {@code Event} that we are building.
     */
    public EventBuilder withTime(String time) {
        this.time = new EventTime(time);
        return this;
    }

    public Event build() {
        return new Event(name, date, time, participants, blacklist);
    }

}
