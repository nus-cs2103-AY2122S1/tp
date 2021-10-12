package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.data.event.Event;
import seedu.address.model.data.member.Member;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the members list.
     * This list will not contain any duplicate members.
     *
     * @return Observable list of members
     */
    ObservableList<Member> getMemberList();

    /**
     * Returns an unmodifiable view of the events list.
     * This list will not contain any duplicate events.
     *
     * @return Observable list of events
     */
    ObservableList<Event> getEventList();

}
