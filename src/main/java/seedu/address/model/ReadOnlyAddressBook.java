package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.participant.Participant;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the Participants list.
     * This list will not contain any duplicate Participants.
     */
    ObservableList<Participant> getParticipantList();



    /**
     * Returns an unmodifiable view of the events list.
     * This list will not contain any duplicate events.
     */
    ObservableList<Event> getEventList();

    /**
     * Sorts the event list chronologically.
     */
    void sortEvents();

}
