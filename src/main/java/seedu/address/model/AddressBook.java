package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.module.event.Event;
import seedu.address.model.module.event.UniqueEventList;
import seedu.address.model.module.member.Member;
import seedu.address.model.module.member.UniqueMemberList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameMember comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueMemberList members;
    private final UniqueEventList events;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        members = new UniqueMemberList();
        events = new UniqueEventList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Members in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the member list with {@code members}.
     * {@code members} must not contain duplicate members.
     *
     * @param members is the member list
     */
    public void setMembers(List<Member> members) {
        this.members.setMembers(members);
    }

    /**
     * Replaces the contents of the member list with {@code members}.
     * {@code members} must not contain duplicate members.
     *
     * @param events is the event list
     */
    public void setEvents(List<Event> events) {
        this.events.setEvents(events);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     *
     * @param newData is the new data to be reset with
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setMembers(newData.getMemberList());
        setEvents(newData.getEventList());
    }

    //// member-level operations

    /**
     * Returns true if a member with the same identity as {@code member} exists in the address book.
     *
     * @param member is the member to check for
     * @return boolean
     */
    public boolean hasMember(Member member) {
        requireNonNull(member);
        return members.contains(member);
    }

    /**
     * Returns true if an event with the same identity as {@code event} exists in the address book.
     *
     * @param event is the event to check for
     * @return boolean
     */
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return events.contains(event);
    }

    /**
     * Adds a member to the address book.
     * The member must not already exist in the address book.
     *
     * @param p is the member to be added
     */
    public void addMember(Member p) {
        members.add(p);
    }

    /**
     * Adds an event to the address book.
     * The event must not already exist in the address book.
     *
     * @param e is the event to be added
     */
    public void addEvent(Event e) {
        events.add(e);
    }

    /**
     * Adds members to an event to the Ailurus.
     * The event must already exist in the Ailurus.
     *
     * @param target is the event to be modified
     * @param memberSet is the member list to be added
     */
    public void addEventMembers(Event target, Set<Member> memberSet) {
        events.addMembers(target, memberSet);
    }

    /**
     * Replaces the given member {@code target} in the list with {@code editedMember}.
     * {@code target} must exist in the address book.
     * The member identity of {@code editedMember} must not be the same as another existing member in the address book.
     *
     * @param target is the member to replace
     * @param editedMember is the member replacing with
     */
    public void setMember(Member target, Member editedMember) {
        requireNonNull(editedMember);

        members.setMember(target, editedMember);
        for (Event event : events) {
            Set<Member> memberSet = event.getParticipants();
            if (memberSet.contains(target)) {
                boolean attend = event.hasAttended(target);
                event.removeParticipant(target);
                event.addParticipant(editedMember, attend);
            }
        }
    }

    /**
     * Replaces the given event {@code target} in the list with {@code editedEvent}.
     * {@code target} must exist in the address book.
     * The member identity of {@code editedEvent} must not be the same as another existing event in the address book.
     *
     * @param target is the event to replace
     * @param editedEvent is the event replacing with
     */
    public void setEvent(Event target, Event editedEvent) {
        requireNonNull(editedEvent);

        events.setEvent(target, editedEvent);
    }

    /**
     * Removes {@code key} member from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     *
     * @param key is the member to be removed
     */
    public void removeMember(Member key) {
        members.remove(key);
        for (Event event : events) {
            Set<Member> memberSet = event.getParticipants();
            if (memberSet.contains(key)) {
                event.removeParticipant(key);
            }
        }
    }

    /**
     * Removes {@code key} event from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     *
     * @param key is the event to be removed
     */
    public void removeEvent(Event key) {
        events.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return members.asUnmodifiableObservableList().size() + " members\n"
                + events.asUnmodifiableObservableList().size() + " events";
        // TODO: refine later
    }

    @Override
    public ObservableList<Member> getMemberList() {
        return members.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Event> getEventList() {
        return events.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && members.equals(((AddressBook) other).members)
                && events.equals(((AddressBook) other).events));
    }

    @Override
    public int hashCode() {
        return Objects.hash(members, events);
    }
}
