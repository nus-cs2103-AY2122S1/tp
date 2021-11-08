package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

public class AddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Address(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Address(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Address.isValidAddress(null));

        // invalid addresses
        assertFalse(Address.isValidAddress("")); // empty string
        assertFalse(Address.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(Address.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(Address.isValidAddress("-")); // one character
        assertTrue(Address.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA"));

        // long address
        assertFalse(Address.isValidAddress("123456789012345678901234567890123456789012345678901234567890123"
                + "456789012345678901234567890"));

    }

    @Test
    public void isEqualAddress() {
        Address address = new Address("Blk 456, Den Road, #01-355");
        Address differentAddress = new Address("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA");
        Address sameAddress = new Address("Blk 456, Den Road, #01-355");
        Person person = ALICE;

        //Different Objects
        assertFalse(address.equals(person));

        // Different Address
        assertFalse(address.equals(differentAddress));

        // Same Object
        assertTrue(address.equals(address));

        // Different Objects Same Address
        assertTrue(address.equals(sameAddress));
    }

    @Test
    public void hashCodeTest() {
        assertEquals(VALID_ADDRESS_AMY.hashCode(), VALID_ADDRESS_AMY.hashCode());
        assertNotEquals(VALID_ADDRESS_AMY.hashCode(), VALID_ADDRESS_BOB.hashCode());
    }
}
