package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
    public void testEquals() {
        Tag sampleTag = new Tag("test");
        Tag sampleTagCopy = new Tag("test");
        Tag differentSampleTag = new Tag("test2");

        // same address -> return true
        assertEquals(sampleTag, sampleTag);

        // different object, same value -> return true
        assertEquals(sampleTagCopy, sampleTag);

        // different value -> return false
        assertNotEquals(differentSampleTag, sampleTag);

        // null object -> return false
        assertNotEquals(sampleTag, null);

        // different type -> return false
        assertNotEquals(sampleTag, 5);
    }

}
