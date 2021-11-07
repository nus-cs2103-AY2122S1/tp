package seedu.address.model.vendor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ServiceNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ServiceName(null));
    }

    @Test
    public void constructor_invalidServiceName_throwsIllegalArgumentException() {
        String invalidServiceName = "";
        assertThrows(IllegalArgumentException.class, () -> new ServiceName(invalidServiceName));
    }

    @Test
    public void isValidServiceName() {
        // null serviceName
        assertThrows(NullPointerException.class, () -> ServiceName.isValidServiceName(null));

        // blank serviceName
        assertFalse(ServiceName.isValidServiceName("")); // empty string
        assertFalse(ServiceName.isValidServiceName(" ")); // spaces only

        // invalid parts
        assertFalse(ServiceName.isValidServiceName("123")); // cannot contain numbers
        assertFalse(ServiceName.isValidServiceName("!*")); // cannot contain special characters

        // valid serviceName
        assertTrue(ServiceName.isValidServiceName("Massage"));
        assertTrue(ServiceName.isValidServiceName("Dry Cleaning"));
    }

}
