package seedu.address.model.gamefriendlink;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SkillLevelTest {
    @Test
    public void constructor_invalidSkillValue_throwsIllegalArgumentException() {
        Integer outsideLowerBounds = -1;
        assertThrows(IllegalArgumentException.class, () -> new SkillValue(outsideLowerBounds));

        Integer outsideUpperBounds = 11;
        assertThrows(IllegalArgumentException.class, () -> new SkillValue(outsideUpperBounds));
    }

    @Test
    public void validateSkillValue_null_throwsNullPointerException() {
        // null skillValue
        assertThrows(NullPointerException.class, () -> SkillValue.validateSkillValue(null));
    }

    // test near the boundaries of equivalence partitions
    @Test
    public void validateSkillValue_invalidSkillValues_returnsFalse() {
        assertFalse(SkillValue.validateSkillValue(-1));
        assertFalse(SkillValue.validateSkillValue(-12));
        assertFalse(SkillValue.validateSkillValue(11));
        assertFalse(SkillValue.validateSkillValue(100));
    }

    @Test
    public void validateSkillValue_validSkillValues_returnsTrue() {
        assertTrue(SkillValue.validateSkillValue(0));
        assertTrue(SkillValue.validateSkillValue(10));
        assertTrue(SkillValue.validateSkillValue(5));
        assertTrue(SkillValue.validateSkillValue(7));
    }

    @Test
    public void equals() {
        // same skillValue is equal
        assertEquals(new SkillValue(10), new SkillValue(10));
        // different skillValue not equal
        assertNotEquals(new SkillValue(10), new SkillValue(9));
        // not equals to null
        assertNotEquals(new SkillValue(10), null);
        // different type and different from int
        assertNotEquals(new SkillValue(10), 10);
    }
}
