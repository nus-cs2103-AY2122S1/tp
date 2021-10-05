package seedu.address.model.facility;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FacilityNameTest {

    @Test
    public void constructor_null_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> new FacilityName(null));
    }

    @Test
    public void equals() {
        FacilityName name = new FacilityName("Name");
        FacilityName nameCopy = new FacilityName("Name");

        assertTrue(name.equals(nameCopy));
        assertTrue(name.equals(name));

        assertFalse(name.equals(null));
        FacilityName differentName = new FacilityName("Test");
        assertFalse(name.equals(differentName));

    }
}
