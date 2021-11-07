package seedu.address.model.vendor;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of a vendor book
 */
public interface ReadOnlyVendorBook {

    /**
     * Returns an unmodifiable view of the vendor list.
     * This list will not contain any duplicate vendors.
     */
    ObservableList<Vendor> getVendorList();

}
