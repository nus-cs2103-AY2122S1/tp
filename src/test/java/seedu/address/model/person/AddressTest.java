package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalPersons;

public class AddressTest {

    @Test
    public void constructor_null_createsDefaultAddress() {
        String noAddress = "N.A";
        assertEquals(new Address(null).value, noAddress);
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Address(invalidAddress));
    }

    @Test
    public void constructor_validAddress_createsAddress() {
        String addressString = TypicalPersons.AMY.getAddress().value;
        Address address = new Address(addressString);

        Address expectedAddress = TypicalPersons.AMY.getAddress();

        assertEquals(address, expectedAddress);
    }

    @Test
    public void isValidAddress() {
        // invalid addresses
        assertFalse(Address.isValidAddress("")); // empty string
        assertFalse(Address.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(Address.isValidAddress(null));
        assertTrue(Address.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(Address.isValidAddress("-")); // one character
        assertTrue(Address.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
    @Test
    public void equals() {

        Address address = new Address("my house");

        // same object -> returns true
        assertTrue(address.equals(address));

        // same values -> returns true
        Address remarkCopy = new Address("my house");
        assertTrue(address.equals(remarkCopy));

        // different types -> returns false
        assertFalse(address.equals(1));

        // null -> returns false
        assertFalse(address.equals(null));

        // different value -> returns false
        Address differentAddress = new Address("His house");
        assertFalse(address.equals(differentAddress));
    }

    @Test
    public void isEmpty() {
        Address test = new Address(null);
        assertTrue(test.isEmpty());

        test = new Address("N.A");
        assertTrue(test.isEmpty());

        test = new Address("ANYTHING");
        assertFalse(test.isEmpty());
    }

    @Test
    public void toStringTest() {
        String expectedString = "Block 123, Bobby Street 3";

        String test = TypicalPersons.BOB.getAddress().toString();

        assertEquals(test, expectedString);
    }

}
