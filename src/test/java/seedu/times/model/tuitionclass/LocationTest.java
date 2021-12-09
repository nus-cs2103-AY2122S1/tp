package seedu.times.model.tuitionclass;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.times.model.ModelTestUtils.LONG_STRING_85_CHAR;
import static seedu.times.model.ModelTestUtils.LONG_STRING_86_CHAR;
import static seedu.times.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LocationTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Location(null));
    }

    @Test
    public void constructor_invalidLocation_throwsIllegalArgumentException() {
        String invalidLocation = "";
        String invalidLocation2 = " ";
        assertThrows(IllegalArgumentException.class, () -> new Location(invalidLocation));
        assertThrows(IllegalArgumentException.class, () -> new Location(invalidLocation2));
    }

    @Test
    public void isValidLocation() {
        //null location
        assertThrows(NullPointerException.class, () -> Location.isValidLocation(null));

        //invalid locations
        assertFalse(Location.isValidLocation(""));
        assertFalse(Location.isValidLocation(" "));
        assertFalse(Location.isValidLocation(" Serangoon"));
        assertFalse(Location.isValidLocation(LONG_STRING_86_CHAR));

        //valid locations
        assertTrue(Location.isValidLocation("Nex Kumon")); //no non-alpha numeric
        assertTrue(Location.isValidLocation("Hougang blk 111 #02-310")); //contains non-alpha numeric
        assertTrue(Location.isValidLocation(LONG_STRING_85_CHAR));
    }

}
