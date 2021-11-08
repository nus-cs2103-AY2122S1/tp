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
    }

    @Test
    public void equals() {
        Tag tagA = new Tag("hi");
        Tag tagB = new Tag("hi");
        Tag tagC = new Tag("bye");

        // same object
        assertTrue(tagA.equals(tagA));

        // same tagName
        assertTrue(tagA.equals(tagB));

        // different tagName
        assertFalse(tagA.equals(tagC));

        // different class
        assertFalse(tagA.equals("hi"));
    }
}
