package seedu.address.model.vendor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class VendorIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new VendorId(null));
    }

    @Test
    public void constructor_invalidVendorId_throwsIllegalArgumentException() {
        String invalidVendorId = "";
        assertThrows(IllegalArgumentException.class, () -> new VendorId(invalidVendorId));
    }

    @Test
    public void isValidVendorId() {
        // null staff id
        assertThrows(NullPointerException.class, () -> VendorId.isValidVendorId(null));

        // invalid staff id
        assertFalse(VendorId.isValidVendorId("")); // empty string
        assertFalse(VendorId.isValidVendorId("^")); // only non-alphanumeric characters
        assertFalse(VendorId.isValidVendorId("peter*")); // contains non-alphanumeric characters
        assertFalse(VendorId.isValidVendorId("  ")); // spaces only

        // valid staff id
        assertTrue(VendorId.isValidVendorId("peter jack")); // alphabets only
        assertTrue(VendorId.isValidVendorId("12345")); // numbers only
        assertTrue(VendorId.isValidVendorId("peter the 2nd")); // alphanumeric characters
        assertTrue(VendorId.isValidVendorId("Capital Tan")); // with capital letters
        assertTrue(VendorId.isValidVendorId("David Roger Jackson Ray Jr 2nd")); // long staff id
        assertTrue(VendorId.isValidVendorId("g")); // single character

    }


}
