package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.staff.Staff;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the staffs list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Staff> getStaffList();

}
