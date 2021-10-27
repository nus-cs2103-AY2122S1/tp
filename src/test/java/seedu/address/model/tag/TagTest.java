package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTags.TAG_ZOOM;

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
    public void getTagName_success() {
        // all upper case -> success
        assertEquals("ZOOM", TAG_ZOOM.getTagName());

        // all lower case -> failed
        assertNotEquals("zoom", TAG_ZOOM.getTagName());

        // partial lower case -> failed
        assertNotEquals("zOom", TAG_ZOOM.getTagName());
    }

    @Test
    public void equals_success() {
        assertEquals(new Tag("zoom"), TAG_ZOOM);
    }

    @Test
    public void testHashCode() {
        assertEquals(new Tag("zoom").hashCode(), TAG_ZOOM.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals("[ZOOM]", TAG_ZOOM.toString());
    }
}
