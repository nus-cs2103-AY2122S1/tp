package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        // invalid tags
        assertFalse(Tag.isValidTagName("")); // empty string
        assertFalse(Tag.isValidTagName(" ")); // spaces only
        assertFalse(Tag.isValidTagName("class 4h")); // contains spaces
        assertFalse(Tag.isValidTagName("old@")); // contains non-alphanumeric
        assertFalse(Tag.isValidTagName("old(70)")); // brackets
        assertFalse(Tag.isValidTagName("!@#$%^&*()_+")); // only non-alphanumeric

        // valid tags
        assertTrue(Tag.isValidTagName("old")); // alphabets only
        assertTrue(Tag.isValidTagName("87773411")); // numbers only
        assertTrue(Tag.isValidTagName("cap5")); // alphanumeric characters
        assertTrue(Tag.isValidTagName("CAP5")); // contains capital letters
        assertTrue(Tag.isValidTagName("DoubleDegreeGraduateWithFirstClassHonoursAndPresidentOfCCA")); // long tag
    }

}
