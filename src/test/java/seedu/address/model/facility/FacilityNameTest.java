package seedu.address.model.facility;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FacilityNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FacilityName(null));
    }

    @Test
    public void constructor_invalidFacilityName_throwsIllegalArgumentException() {
        // below 1 character
        assertThrows(IllegalArgumentException.class, () -> new FacilityName(""));
        // more than 50 characters
        assertThrows(IllegalArgumentException.class, () -> new FacilityName("1111111111111"
                + "11111111111111111111111111111111111111"));
        // non-alphanumeric characters
        assertThrows(IllegalArgumentException.class, () -> new FacilityName("@#$"));
        assertThrows(IllegalArgumentException.class, () -> new FacilityName("NUS_SPORTS"));
    }

    @Test
    public void isValidFacilityName() {
        // null facility name
        assertThrows(NullPointerException.class, () -> FacilityName.isValidFacilityName(null));

        // valid facility name
        assertTrue(FacilityName.isValidFacilityName("1")); // 1 character(lower boundary)
        assertTrue(FacilityName.isValidFacilityName("1111111111111"
                + "1111111111111111111111111111111111111")); // 50 characters(upper boundary)
        assertTrue(FacilityName.isValidFacilityName("25"));

        // invalid facility name
        assertFalse(FacilityName.isValidFacilityName("")); // empty string
        assertFalse(FacilityName.isValidFacilityName("")); // // below 1 character
        assertFalse(FacilityName.isValidFacilityName("1111111111111"
                + "11111111111111111111111111111111111111")); // more than 50 characters
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
