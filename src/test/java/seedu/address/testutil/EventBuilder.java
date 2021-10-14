package seedu.address.testutil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import seedu.address.model.data.Name;
import seedu.address.model.data.event.Event;
import seedu.address.model.data.event.EventDate;
import seedu.address.model.data.member.Member;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {

    public static final String DEFAULT_NAME = "CYCLING";
    public static final String DEFAULT_DATE = "12-12-2022";
    public static final Set<Member> DEFAULT_PARTICIPANTS = Set.of(TypicalMembers.ALICE, TypicalMembers.BOB,
            TypicalMembers.BENSON);

    private Name name;
    private EventDate date;
    private Map<Member, Boolean> participants;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        name = new Name(DEFAULT_NAME);
        date = new EventDate(DEFAULT_DATE);
        participants = new HashMap<>();
        for (Member m : DEFAULT_PARTICIPANTS) {
            participants.put(m, false);
        }
    }

    /**
     * Initializes the EventBuilder with the data of {@code EventToCopy}.
     *
     * @param EventToCopy is the event to copy
     */
    public EventBuilder(Event EventToCopy) {
        name = EventToCopy.getName();
        date = EventToCopy.getDate();
        participants = EventToCopy.getMap();

    }

    /**
     * Sets the {@code Name} of the {@code Event} that we are building.
     *
     * @param name is the name to be set
     * @return the EventBuilder after setting
     */
    public EventBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Event} that we are building.
     *
     * @param date is the string representing the date
     * @return the EventBuilder after setting
     */
    public EventBuilder withDate(String date) {
        this.date = new EventDate(date);
        return this;
    }

    /**
     * Parses the {@code Members} into a {@code Set<Member>} and set it to the {@code Event} that we are building.
     *
     * @param members is the members to set
     * @return the EventBuilder after setting
     */
    public EventBuilder withParticipants(Member... members) {
        this.participants = new HashMap<>();
        for (Member m : SampleDataUtil.getMemberSet(members)) {
            participants.put(m, false);
        }
        return this;
    }

    /**
     * Sets the {@code Participants} of the {@code Event} that we are building
     * from a map.
     *
     * @param participants is the map of participants to be set
     * @return the EventBuilder after setting
     */
    public EventBuilder withParticipants(Map<Member, Boolean> participants) {
        this.participants = participants;
        return this;
    }

    /**
     * Builds a default event.
     *
     * @return default event
     */
    public Event build() {
        return new Event(name, date, participants);
    }

}
