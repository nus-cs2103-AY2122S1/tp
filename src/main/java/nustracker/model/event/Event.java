package nustracker.model.event;

import static java.util.Objects.requireNonNull;
import static nustracker.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import nustracker.model.AddressBook;
import nustracker.model.student.Student;
import nustracker.model.student.StudentId;

public class Event {

    private final EventName name;
    private final EventDate date;
    private final EventTime time;

    private final Set<Participant> participants = new HashSet<>();
    private final Set<Participant> blacklist = new HashSet<>();

    /**
     * Create an Event with no participants.
     *
     * @param name Event name
     * @param date Event date
     * @param time Event time
     */
    public Event(EventName name, EventDate date, EventTime time) {
        requireAllNonNull(name, date, time);
        this.name = name;
        this.date = date;
        this.time = time;
    }

    /**
     * Create an Event with a set of Participants.
     *  @param name Event name
     * @param date Event date
     * @param time Event time
     * @param participants Event participants
     * @param blacklist Event blacklist
     */
    public Event(EventName name, EventDate date, EventTime time,
                 Set<Participant> participants, Set<Participant> blacklist) {
        requireAllNonNull(name, date, time, participants, blacklist);
        this.name = name;
        this.date = date;
        this.time = time;
        this.participants.addAll(participants);
        this.blacklist.addAll(blacklist);
    }

    public EventName getName() {
        return name;
    }

    public EventDate getDate() {
        return date;
    }

    public EventTime getTime() {
        return time;
    }

    /**
     * Returns an immutable participant set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Participant> getParticipants() {
        return Collections.unmodifiableSet(participants);
    }

    /**
     * Returns an immutable participant set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Participant> getBlacklist() {
        return Collections.unmodifiableSet(blacklist);
    }

    /**
     * Returns an {@code Student} set.
     *
     * @param addressBook to find the students from.
     * @return the set of {@code Student} from this model that are in this {@code Event}.
     */
    public Set<Student> getParticipantsAsStudents(AddressBook addressBook) {
        HashSet<Student> returnThis = new HashSet<>();
        for (Participant currParticipant : participants) {
            Student currStudent = addressBook.getStudent(currParticipant.getStudentId());
            returnThis.add(currStudent);
        }
        return returnThis;
    }

    /**
     * Checks if a student with a certain student ID is currently enrolled in this event.
     * @param studentId the {@code StudentId} of the student to check.
     * @return true if the student is currently enrolled, false otherwise.
     */
    public boolean isInEvent(StudentId studentId) {
        for (Participant currParticipant : participants) {
            if (currParticipant.getStudentId().equals(studentId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a certain Student ID is blacklisted in this event.
     * @param studentId the {@code StudentId} to check.
     * @return true if the Student ID is blacklisted, false otherwise.
     */
    public boolean isBlacklisted(StudentId studentId) {
        for (Participant blacklisted : blacklist) {
            if (blacklisted.getStudentId().equals(studentId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if both events have the same name.
     * This defines a weaker notion of equality between two events.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && otherEvent.getName().equals(getName());
    }


    /**
     * Creates a new Event object that has the same details but different participants.
     *
     * @param newParticipants the new Set of {@code Participants} that are attending this {@code Event}.
     * @return the newly created {@code Event}.
     */
    public Event getNewEventWithUpdatedParticipants(Set<Participant> newParticipants) {
        return new Event(getName(), getDate(), getTime(), newParticipants, getBlacklist());
    }

    /**
     * Wraps the EventName in an Event for easy re-usability with other methods.
     *
     * @param eventName The event name
     * @return An Event with the given EventName, and a pseudo EventDate and EventTime.
     */
    public static Event pseudoEvent(EventName eventName) {
        requireNonNull(eventName);

        String validEventDate = "09-10-2021";
        String validEventTime = "1115";
        return new Event(eventName,
                new EventDate(validEventDate),
                new EventTime(validEventTime));
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
                && otherEvent.getParticipants().equals(getParticipants())
                && otherEvent.getBlacklist().equals(getBlacklist());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, date, time, participants, blacklist);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Date: ")
                .append(getDate())
                .append("; Time: ")
                .append(getTime());

        Set<Participant> participants = getParticipants();
        if (!participants.isEmpty()) {
            builder.append("; Participants: ");
            participants.forEach(builder::append);
        }

        Set<Participant> blacklist = getBlacklist();
        if (!blacklist.isEmpty()) {
            builder.append("; Blacklist: ");
            blacklist.forEach(builder::append);
        }

        return builder.toString();
    }
}
