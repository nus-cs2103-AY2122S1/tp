package seedu.address.model.gamefriendlink;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SkillValueTest {
    @Test
    public void constructor_invalidSkillValue_throwsIllegalArgumentException() {
        Integer outsideLowerBounds = -1;
        assertThrows(IllegalArgumentException.class, () -> new SkillValue(outsideLowerBounds));

        Integer outsideUpperBounds = 11;
        assertThrows(IllegalArgumentException.class, () -> new SkillValue(outsideUpperBounds));
    }

    // EP: invalid ranges: less than valid range and greater than valid range
    // test near the boundaries of equivalence partitions
    @Test
    public void validateSkillValue_invalidSkillValues_returnsFalse() {
        assertFalse(SkillValue.validateSkillValue(-1));
        assertFalse(SkillValue.validateSkillValue(-12));
        assertFalse(SkillValue.validateSkillValue(11));
        assertFalse(SkillValue.validateSkillValue(100));
    }

    // EP: within valid range
    @Test
    public void validateSkillValue_validSkillValues_returnsTrue() {
        assertTrue(SkillValue.validateSkillValue(0));
        assertTrue(SkillValue.validateSkillValue(10));
        assertTrue(SkillValue.validateSkillValue(5));
        assertTrue(SkillValue.validateSkillValue(7));
    }

    @Test
    public void isValidSkillValueString_nonIntegerString_returnsFalse() {
        String notAnInteger = "NotAnInteger";
        assertFalse(SkillValue.isValidSkillValueString(notAnInteger));
    }

    @Test
    public void equals() {
        SkillValue skillValue = new SkillValue(10);

        // same object
        assertEquals(skillValue, skillValue);

        // null
        assertNotEquals(skillValue, null);

        // different type
        assertNotEquals(skillValue, "String");

        // different object, same identity fields
        assertEquals(skillValue, new SkillValue(skillValue.skillVal));

        // different identity fields
        assertNotEquals(skillValue, new SkillValue(9));
    }
}
