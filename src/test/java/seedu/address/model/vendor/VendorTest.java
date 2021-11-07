package seedu.address.model.vendor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COST_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COST_FIONA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OPERATING_HOURS_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OPERATING_HOURS_FIONA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SERVICE_NAME_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SERVICE_NAME_FIONA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HIGH_RATINGS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VENDOR_ID_DANIEL;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.vendor.TypicalVendors.DANIEL_VENDOR;
import static seedu.address.testutil.vendor.TypicalVendors.FIONA_VENDOR;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.vendor.VendorBuilder;

public class VendorTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Vendor vendor = new VendorBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> vendor.getTags().remove(0));
    }

    @Test
    public void isSame() {
        // same object -> returns true
        assertTrue(FIONA_VENDOR.isSame(FIONA_VENDOR));

        // null -> returns false
        assertFalse(FIONA_VENDOR.isSame(null));

        // same VID, all other attributes different -> returns true
        Vendor editedFiona = new VendorBuilder(FIONA_VENDOR)
                .withName(VALID_NAME_DANIEL)
                .withPhone(VALID_PHONE_DANIEL)
                .withEmail(VALID_EMAIL_DANIEL)
                .withAddress(VALID_ADDRESS_DANIEL)
                .withTags(VALID_TAG_HIGH_RATINGS)
                .withCost(VALID_COST_FIONA)
                .withOperatingHours(VALID_OPERATING_HOURS_FIONA)
                .withServiceName(VALID_SERVICE_NAME_FIONA)
                .build();

        assertTrue(FIONA_VENDOR.isSame(editedFiona));

        // different VID, all other attributes same -> returns false
        editedFiona = new VendorBuilder(FIONA_VENDOR).withVendorId(VALID_VENDOR_ID_DANIEL).build();
        assertFalse(FIONA_VENDOR.isSame(editedFiona));

        // SID has trailing spaces, all other attributes same -> returns false
        String idWithTrailingSpaces = VALID_VENDOR_ID_DANIEL + "  ";
        Vendor editedDaniel = new VendorBuilder(DANIEL_VENDOR).withVendorId(idWithTrailingSpaces).build();
        assertFalse(DANIEL_VENDOR.isSame(editedDaniel));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Vendor fionaCopy = new VendorBuilder(FIONA_VENDOR).build();
        assertTrue(FIONA_VENDOR.equals(fionaCopy));

        // same object -> returns true
        assertTrue(FIONA_VENDOR.equals(FIONA_VENDOR));

        // null -> returns false
        assertFalse(FIONA_VENDOR.equals(null));

        // different type -> returns false
        assertFalse(FIONA_VENDOR.equals(5));

        // different vendor -> returns false
        assertFalse(FIONA_VENDOR.equals(DANIEL_VENDOR));

        // different vendor id -> returns false
        Vendor editedFiona = new VendorBuilder(FIONA_VENDOR).withVendorId(VALID_VENDOR_ID_DANIEL).build();
        assertFalse(FIONA_VENDOR.equals(editedFiona));

        // different name -> returns false
        editedFiona = new VendorBuilder(FIONA_VENDOR).withName(VALID_NAME_DANIEL).build();
        assertFalse(FIONA_VENDOR.equals(editedFiona));

        // different phone -> returns false
        editedFiona = new VendorBuilder(FIONA_VENDOR).withPhone(VALID_PHONE_DANIEL).build();
        assertFalse(FIONA_VENDOR.equals(editedFiona));

        // different email -> returns false
        editedFiona = new VendorBuilder(FIONA_VENDOR).withEmail(VALID_EMAIL_DANIEL).build();
        assertFalse(FIONA_VENDOR.equals(editedFiona));

        // different address -> returns false
        editedFiona = new VendorBuilder(FIONA_VENDOR).withAddress(VALID_ADDRESS_DANIEL).build();
        assertFalse(FIONA_VENDOR.equals(editedFiona));

        // different tags -> returns false
        editedFiona = new VendorBuilder(FIONA_VENDOR).withTags(VALID_TAG_HIGH_RATINGS).build();
        assertFalse(FIONA_VENDOR.equals(editedFiona));

        // different cost -> returns false
        editedFiona = new VendorBuilder(FIONA_VENDOR).withCost(VALID_COST_DANIEL).build();
        assertFalse(FIONA_VENDOR.equals(editedFiona));

        // different serviceName -> returns false
        editedFiona = new VendorBuilder(FIONA_VENDOR).withServiceName(VALID_SERVICE_NAME_DANIEL).build();
        assertFalse(FIONA_VENDOR.equals(editedFiona));

        // different operatingHours -> returns false
        editedFiona = new VendorBuilder(FIONA_VENDOR).withOperatingHours(VALID_OPERATING_HOURS_DANIEL).build();
        assertFalse(FIONA_VENDOR.equals(editedFiona));

    }
}
