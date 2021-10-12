package seedu.address.model.person.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCustomers.CUSTOMER_ALICE;
import static seedu.address.testutil.TypicalCustomers.CUSTOMER_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CustomerBuilder;

public class CustomerTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Customer customer = new CustomerBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> customer.getTags().remove(0));
    }

    @Test
    public void isSameCustomer() {
        // same object -> returns true
        assertTrue(CUSTOMER_ALICE.isSameCustomer(CUSTOMER_ALICE));

        // null -> returns false
        assertFalse(CUSTOMER_ALICE.isSameCustomer(null));

        // same name, all other attributes different -> returns true
        Customer editedAlice =
                new CustomerBuilder(CUSTOMER_ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(CUSTOMER_ALICE.isSameCustomer(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new CustomerBuilder(CUSTOMER_ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(CUSTOMER_ALICE.isSameCustomer(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Customer editedBob = new CustomerBuilder(CUSTOMER_BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(CUSTOMER_BOB.isSameCustomer(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new CustomerBuilder(CUSTOMER_BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(CUSTOMER_BOB.isSameCustomer(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Customer aliceCopy = new CustomerBuilder(CUSTOMER_ALICE).build();
        assertTrue(CUSTOMER_ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(CUSTOMER_ALICE.equals(CUSTOMER_ALICE));

        // null -> returns false
        assertFalse(CUSTOMER_ALICE.equals(null));

        // different type -> returns false
        assertFalse(CUSTOMER_ALICE.equals(5));

        // different person -> returns false
        assertFalse(CUSTOMER_ALICE.equals(CUSTOMER_BOB));

        // different name -> returns false
        Customer editedAlice = new CustomerBuilder(CUSTOMER_ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(CUSTOMER_ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new CustomerBuilder(CUSTOMER_ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(CUSTOMER_ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new CustomerBuilder(CUSTOMER_ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(CUSTOMER_ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new CustomerBuilder(CUSTOMER_ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(CUSTOMER_ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new CustomerBuilder(CUSTOMER_ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(CUSTOMER_ALICE.equals(editedAlice));
    }
}
