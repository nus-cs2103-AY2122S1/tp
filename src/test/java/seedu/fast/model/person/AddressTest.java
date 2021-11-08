package seedu.fast.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fast.testutil.Assert.assertThrows;

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
        assertFalse(Address.isValidAddress(LengthUtil.EMPTY_STRING)); // empty string
        assertFalse(Address.isValidAddress(LengthUtil.WHITE_SPACE_STRING)); // spaces only
        assertFalse(Address.isValidAddress(LengthUtil.ONE_HUNDRED_CHARA + LengthUtil.ONE_CHARA));

        // valid addresses
        assertTrue(Address.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(Address.isValidAddress(LengthUtil.ONE_CHARA)); // one character
        assertTrue(Address.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
        assertTrue(Address.isValidAddress(LengthUtil.ONE_HUNDRED_CHARA));
    }

    @Test
    public void hashcode() {
        Address standardAddress = new Address("Blk 456, Den Road, #01-355");
        Address addressWithSameValue = new Address("Blk 456, Den Road, #01-355");
        Address addressWithDifferentValue = new Address("Blk 717, Den Road, #01-355");

        assertTrue(standardAddress.hashCode() == addressWithSameValue.hashCode());
        assertTrue(standardAddress.hashCode() == standardAddress.hashCode());

        assertFalse(standardAddress.hashCode() == addressWithDifferentValue.hashCode());
    }
}
