package seedu.fast.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.fast.testutil.Assert.assertThrows;

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
    public void isValidTagTerm() {
        //null tag term
        assertThrows(NullPointerException.class, () -> Tag.isValidTagTerm(null));

        //valid Tag term
        String VALID_TAG_TERM = "test";
        assertTrue(Tag.isValidTagTerm(VALID_TAG_TERM));

        //invalid Tag term
        String INVALID_TAG_TERM = "hi there";
        assertFalse(Tag.isValidTagTerm(INVALID_TAG_TERM));
    }

    @Test
    public void createTag() {
        //null term
        assertThrows(NullPointerException.class, () -> Tag.createTag(null));

        //valid Tag term
        String VALID_TAG_TERM = "test";
        assertTrue(Tag.createTag(VALID_TAG_TERM) instanceof Tag);

        //valid PriorityTag term
        String VALID_PRIORITY_TAG_TERM = "pr/low";
        assertTrue(Tag.createTag(VALID_PRIORITY_TAG_TERM) instanceof PriorityTag);

        //invalid term
        String INVALID_TAG_TERM = "big fat";
        assertThrows(IllegalArgumentException.class, () -> Tag.createTag(INVALID_TAG_TERM));
    }

}