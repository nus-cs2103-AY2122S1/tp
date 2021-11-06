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
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
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
        // all upper case -> success
        assertEquals("ZOOM", TAG_ZOOM.toString());

        // all lower case -> failed
        assertNotEquals("zoom", TAG_ZOOM.toString());

        // partial lower case -> failed
        assertNotEquals("zOom", TAG_ZOOM.toString());
    }
}
