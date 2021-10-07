package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.event.UniqueEventList;
import seedu.address.model.participant.Participant;
import seedu.address.model.participant.UniqueParticipantList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameParticipant comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueParticipantList participants;
    private final UniqueEventList events;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        participants = new UniqueParticipantList();
        //Add on for Managera
        events = new UniqueEventList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Participants in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the participant list with {@code participant}.
     * {@code participant} must not contain duplicate participants.
     */
    public void setParticipants(List<Participant> participants) {
        this.participants.setParticipants(participants);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setParticipants(newData.getParticipantList());
        setEvents(newData.getEventList());
    }

    //// person-level operations

    /**
     * Returns true if a participant with the same identity as {@code participant} exists in the address book.
     */
    public boolean hasParticipant(Participant participant) {
        requireNonNull(participant);
        return participants.contains(participant);
    }

    /**
     * Adds a Particpant to the Managera.
     * The Participant must not already exist in Managera.
     */
    public void addParticipant(Participant p) {
        participants.add(p);
    }

    /**
     * Replaces the given Participant {@code target} in the list with {@code editedParticipant}.
     * {@code target} must exist in Managera.
     * The person identity of {@code editedParticipant} must not be the same as another
     * existing Participant in Managera.
     */
    public void setParticipant(Participant target, Participant editedParticipant) {
        requireNonNull(editedParticipant);

        participants.setParticipant(target, editedParticipant);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in Managera.
     */
    public void removeParticipant(Participant key) {
        participants.remove(key);
    }

    //Add on for Managera
    /**
     * Replaces the contents of the event list with {@code events}.
     * {@code events} must not contain duplicate events.
     */
    public void setEvents(List<Event> events) {
        this.events.setEvents(events);
    }

    /**
     * Returns true if an event with the same identity as {@code event} exists in the address book.
     */
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return events.contains(event);
    }

    /**
     * Adds a participant to the Managera.
     * The participant must not already exist in the Managera.
     */
    public void addEvent(Event e) {
        events.add(e);
    }

    //setEvent omitted for future implementation

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in Managera.
     */
    public void removeEvent(Event key) {
        events.remove(key);
    }

    /**
     * Marks {@code key} from this {@code AddressBook} as done.
     * {@code key} must exist in Managera.
     */
    public void markEventAsDone(Event key) {
        events.setEvent(key, key.markAsDone());
    }

    @Override
    public void sortEvents() {
        events.sort();
    }

    //// util methods

    @Override
    public String toString() {
        return participants.asUnmodifiableObservableList().size() + " participants";
        // TODO: refine later
    }

    @Override
    public ObservableList<Participant> getParticipantList() {
        return participants.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Event> getEventList() {
        return events.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && participants.equals(((AddressBook) other).participants));
    }

    @Override
    public int hashCode() {
        return participants.hashCode();
    }
}
