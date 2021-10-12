package seedu.address.model.data.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.data.Data;
import seedu.address.model.data.Name;
import seedu.address.model.data.member.Member;

/**
 * Represents an Event in the Ailurus.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event extends Data {
    /**
     * Identity fields
     */
    private final EventDate date;

    /**
     * Data field is a hashmap of members and booleans that represent their attendance.
     */
    private final Map<Member, Boolean> participants = new HashMap<>();

    /**
     * Creates a {@code Event} with the given {@code EventName} for name and {@code EventDate}
     * for participants.
     * Every field must be present and not null.
     */
    public Event(Name name, EventDate date) {
        super(name);
        requireAllNonNull(date);
        this.date = date;
    }

    /**
     * Creates a {@code Event} with the given {@code EventName} for name, {@code EventDate} and {@code Set<Member>}
     * for participants.
     * Every field must be present and not null.
     */
    public Event(Name name, EventDate date, Set<Member> participants) {
        super(name);
        requireAllNonNull(date);
        this.date = date;
        for (Member m : participants) {
            this.participants.put(m, false);
        }
    }

    /**
     * Creates a {@code Event} with the given {@code EventName} for name, {@code EventDate} and
     * {@code Map<Member, Boolean>} for participants.
     * Every field must be present and not null.
     */
    public Event(Name name, EventDate date, Map<Member, Boolean> participants) {
        super(name);
        requireAllNonNull(date);
        this.date = date;
        this.participants.putAll(participants);
    }

    public EventDate getDate() {
        return date;
    }

    public Map<Member, Boolean> getMap() {
        return participants;
    }

    /**
     * Get of participants of the event.
     *
     * @return an immutable set of participants, which throws {@code UnsupportedOperationException}
     * if modification is attempted
     */
    public Set<Member> getParticipants() {
        return Collections.unmodifiableSet(participants.keySet());
    }

    /**
     * Removes a set of members from the event.
     *
     * @param members to be removed
     */
    public void removeParticipants(Set<Member> members) {
        for (Member m : members) {
            participants.remove(m);
        }
    }

    /**
     * Add a set of members from the event.
     *
     * @param members to be added
     */
    public void addParticipants(Set<Member> members) {
        for (Member m : members) {
            participants.put(m, false);
        }
    }

    /**
     * Checks if a member is part of the event.
     *
     * @param member to check
     * @return boolean indicating if member is included
     */
    public boolean isParticipatingInEvent(Member member) {
        return participants.containsKey(member);
    }

    /**
     * Checks if a member has attended in this event.
     *
     * @param member to check
     * @return boolean indicating whether the member has attended the event
     */
    public boolean hasAttended(Member member) {
        return participants.get(member);
    }

    /**
     * Marks the attendance of the set of members.
     *
     * @param members to be marked as present
     */
    public void markAttendance(Set<Member> members) {
        for (Member m : members) {
            participants.replace(m, true);
        }
    }

    /**
     * Mark the absence of a set of members.
     *
     * @param members to be marked as absent
     */
    public void markAbsent(Set<Member> members) {
        for (Member m : members) {
            participants.replace(m, false);
        }
    }

    /**
     * Checks if two events are similar types.
     *
     * @param otherEvent is the event to be compared with
     * @return true if both Events have the same name and date
     */
    @Override
    public boolean isSameType(Data otherEvent) {
        if (otherEvent == this) {
            return true;
        } else if (otherEvent instanceof Event) {
            Event other = (Event) otherEvent;
            return other.getName().equals(getName()) && other.getDate().equals(getDate());
        }
        return false;
    }

    /**
     * Checks if something is equal to the event. This defines a stronger notion of equality between two events.
     *
     * @param other is the object to be compared to
     * @return true if both events have the same identity and data fields
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
                && otherEvent.getParticipants().equals(getParticipants());
    }

    /**
     * use this method for custom fields hashing instead of implementing your own
     *
     * @return integer obtained from hashing
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName(), date, participants);
    }

    /**
     * Returns the name and date of the event in string.
     *
     * @return String representation of the event
     */
    @Override
    public String toString() {
        return String.format("%s; Date: %s", getName(), date);
    }
}
