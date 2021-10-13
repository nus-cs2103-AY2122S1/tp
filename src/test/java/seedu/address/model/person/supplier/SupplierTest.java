package seedu.address.model.person.supplier;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_DELIVERY_DETAIL_DAILY;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_SUPPLY_TYPE_BEEF;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSuppliers.BOB;
import static seedu.address.testutil.TypicalSuppliers.CHETWIN;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.SupplierBuilder;

public class SupplierTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Supplier supplier = new SupplierBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> supplier.getTags().remove(0));
    }

    @Test
    public void isSameSupplier() {
        // same object -> returns true
        assertTrue(CHETWIN.isSamePerson(CHETWIN));

        // null -> returns false
        assertFalse(CHETWIN.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Supplier editedChetwin = new SupplierBuilder(CHETWIN).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .withSupplyType(VALID_SUPPLY_TYPE_BEEF).withDeliveryDetails(VALID_DELIVERY_DETAIL_DAILY).build();
        assertTrue(CHETWIN.isSamePerson(editedChetwin));

        // different name, all other attributes same -> returns false
        editedChetwin = new SupplierBuilder(CHETWIN).withName(VALID_NAME_BOB).build();
        assertFalse(CHETWIN.isSamePerson(editedChetwin));

        // name differs in case, all other attributes same -> returns false
        Supplier editedBob = new SupplierBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new SupplierBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Supplier chetwinCopy = new SupplierBuilder(CHETWIN).build();
        assertTrue(CHETWIN.equals(chetwinCopy));

        // same object -> returns true
        assertTrue(CHETWIN.equals(CHETWIN));

        // null -> returns false
        assertFalse(CHETWIN.equals(null));

        // different type -> returns false
        assertFalse(CHETWIN.equals(5));

        // different person -> returns false
        assertFalse(CHETWIN.equals(BOB));

        // different name -> returns false
        Supplier editedChetwin = new SupplierBuilder(CHETWIN).withName(VALID_NAME_BOB).build();
        assertFalse(CHETWIN.equals(editedChetwin));

        // different phone -> returns false
        editedChetwin = new SupplierBuilder(CHETWIN).withPhone(VALID_PHONE_BOB).build();
        assertFalse(CHETWIN.equals(editedChetwin));

        // different email -> returns false
        editedChetwin = new SupplierBuilder(CHETWIN).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(CHETWIN.equals(editedChetwin));

        // different address -> returns false
        editedChetwin = new SupplierBuilder(CHETWIN).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(CHETWIN.equals(editedChetwin));

        // different tags -> returns false
        editedChetwin = new SupplierBuilder(CHETWIN).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(CHETWIN.equals(editedChetwin));

        // different supply type -> returns false
        editedChetwin = new SupplierBuilder(CHETWIN).withSupplyType(VALID_SUPPLY_TYPE_BEEF).build();
        assertFalse(CHETWIN.equals(editedChetwin));

        // different delivery details -> returns false
        editedChetwin = new SupplierBuilder(CHETWIN).withDeliveryDetails(VALID_DELIVERY_DETAIL_DAILY).build();
        assertFalse(CHETWIN.equals(editedChetwin));
    }
}
