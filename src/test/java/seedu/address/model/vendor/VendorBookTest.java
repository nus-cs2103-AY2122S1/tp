package seedu.address.model.vendor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HIGH_RATINGS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.vendor.TypicalVendors.DANIEL_VENDOR;
import static seedu.address.testutil.vendor.TypicalVendors.getTypicalVendorBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.uniquelist.exceptions.DuplicateItemException;
import seedu.address.testutil.vendor.VendorBuilder;

public class VendorBookTest {

    private final VendorBook vendorBook = new VendorBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), vendorBook.getVendorList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> vendorBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyVendorBook_replacesData() {
        VendorBook newData = getTypicalVendorBook();
        vendorBook.resetData(newData);
        assertEquals(newData, vendorBook);
    }

    @Test
    public void resetData_withDuplicateVendors_throwsDuplicateItemException() {
        // Two vendors with the same identity fields
        Vendor editedDaniel =
                new VendorBuilder(DANIEL_VENDOR).withTags(VALID_TAG_HIGH_RATINGS).build();
        List<Vendor> newVendors = Arrays.asList(DANIEL_VENDOR, editedDaniel);
        VendorBookStub newData = new VendorBookStub(newVendors);

        assertThrows(DuplicateItemException.class, () -> vendorBook.resetData(newData));
    }

    @Test
    public void hasVendor_nullVendor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> vendorBook.hasVendor(null));
    }

    @Test
    public void hasVendor_vendorNotInVendorBook_returnsFalse() {
        assertFalse(vendorBook.hasVendor(DANIEL_VENDOR));
    }

    @Test
    public void hasVendor_vendorInAddressBook_returnsTrue() {
        vendorBook.addVendor(DANIEL_VENDOR);
        assertTrue(vendorBook.hasVendor(DANIEL_VENDOR));
    }

    @Test
    public void hasVendor_vendorWithSameIdentityFieldsInVendorBook_returnsTrue() {
        vendorBook.addVendor(DANIEL_VENDOR);
        Vendor editedDaniel =
                new VendorBuilder(DANIEL_VENDOR).withTags(VALID_TAG_HIGH_RATINGS)
                        .build();
        assertTrue(vendorBook.hasVendor(editedDaniel));
    }

    @Test
    public void getVendorList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> vendorBook.getVendorList().remove(0));
    }

    /**
     * A stub ReadOnlyVendorBook whose vendors list can violate interface constraints.
     */
    private static class VendorBookStub implements ReadOnlyVendorBook {
        private final ObservableList<Vendor> vendors = FXCollections.observableArrayList();

        VendorBookStub(Collection<Vendor> vendors) {
            this.vendors.setAll(vendors);
        }

        @Override
        public ObservableList<Vendor> getVendorList() {
            return vendors;
        }

    }

}
