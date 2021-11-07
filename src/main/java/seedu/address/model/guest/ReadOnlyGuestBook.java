package seedu.address.model.guest;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of a guest manager
 */
public interface ReadOnlyGuestBook {

    /**
     * Returns an unmodifiable view of the guest list.
     * This list will not contain any duplicate guests.
     */
    ObservableList<Guest> getGuestList();

}
