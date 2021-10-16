package seedu.address.model.participant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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
        assertTrue(Address.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }

    @Test
    public void testEquals() {
        Address sampleAddress = new Address("Blk 456, Den Road, #01-355");
        Address sampleAddressCopy = new Address("Blk 456, Den Road, #01-355");
        Address differentSampleAddress = new Address("Blk 456, Den Road, #01-356");

        // same address -> return true
        assertEquals(sampleAddress, sampleAddress);

        // different object, same value -> return true
        assertEquals(sampleAddressCopy, sampleAddress);

        // different value -> return false
        assertNotEquals(differentSampleAddress, sampleAddress);

        // null object -> return false
        assertNotEquals(sampleAddress, null);

        // different type -> return false
        assertNotEquals(sampleAddress, 5);
    }
}
