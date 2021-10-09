package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.member.Member;

/**
 * Represents an Event in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event {
    // Identity fields
    private final String name;
    private final LocalDate date;

    // Data fields
    private final HashMap<Member, Boolean> participants = new HashMap<>();

    /**
     * Every field must be present and not null.
     */
    public Event(String name, LocalDate date, Set<Member> participants) {
        requireAllNonNull(name, date);
        this.name = name;
        this.date = date;
        for (Member m : participants) {
            this.participants.put(m, false);
        }
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns an immutable set of participants, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
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
     * Check if a member is part of the event.
     *
     * @param member to check
     * @return boolean indicating if member is included
     */
    public boolean isParticipant(Member member) {
        return participants.containsKey(member);
    }

    /**
     * Check if a member has attended in this event.
     *
     * @param member to check
     * @return boolean indicating member's attendance
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
     * Returns true if both Events have the same name.
     */
    public boolean isSameTypeOfEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && otherEvent.getName().equals(getName());
    }

    /**
     * Returns true if both members have the same identity and data fields.
     * This defines a stronger notion of equality between two members.
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

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, date, participants);
    }

    @Override
    public String toString() {
        return String.format("%s ; Date: %s", name, date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
