package seedu.fast.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fast.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.fast.commons.util.TagUtil;

public class TagTest {

    public static final String VALID_ALPHABET_TAG_TERM = "test";
    public static final String VALID_NUMBER_TAG_TERM = "1234567890";
    public static final String VALID_MIXED_TAG_TERM = "te12345test67890st";
    public static final String INVALID_TAG_TERM = "hi there";
    public static final String VALID_PRIORITY_TAG_TERM = "pr/low";

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
        assertTrue(Tag.isValidTagTerm(VALID_ALPHABET_TAG_TERM));
        assertTrue(Tag.isValidTagTerm(VALID_NUMBER_TAG_TERM));
        assertTrue(Tag.isValidTagTerm(VALID_MIXED_TAG_TERM));

        //invalid Tag term
        assertFalse(Tag.isValidTagTerm(INVALID_TAG_TERM));
    }

    @Test
    public void createTag() {
        //null term
        assertThrows(NullPointerException.class, () -> Tag.createTag(null));

        //valid Tag term
        assertTrue(Tag.createTag(VALID_TAG_TERM) instanceof Tag);

        //valid PriorityTag term
        assertTrue(Tag.createTag(VALID_PRIORITY_TAG_TERM) instanceof PriorityTag);

        //invalid term
        assertThrows(IllegalArgumentException.class, () -> Tag.createTag(INVALID_TAG_TERM));
    }

    @Test
    public void setPriority() {
        //individual cases for each PriorityTag
        assertTrue(Tag.setPriority(PriorityTag.HighPriority.NAME) == PriorityTag.HighPriority.PRIORITY);
        assertTrue(Tag.setPriority(PriorityTag.MediumPriority.NAME) == PriorityTag.MediumPriority.PRIORITY);
        assertTrue(Tag.setPriority(PriorityTag.LowPriority.NAME) == PriorityTag.LowPriority.PRIORITY);

        //case for a normal tag
        assertTrue(Tag.setPriority("test") == TagUtil.NO_PRIORITY);
    }

    @Test
    public void getPriority() {
        //individual cases for each PriorityTag
        assertTrue(Tag.createTag(PriorityTag.PRIORITY_TAG_PREFIX + PriorityTag.HighPriority.TERM)
                .getPriority() == PriorityTag.HighPriority.PRIORITY);
        assertTrue(Tag.createTag(PriorityTag.PRIORITY_TAG_PREFIX + PriorityTag.MediumPriority.TERM)
                .getPriority() == PriorityTag.MediumPriority.PRIORITY);
        assertTrue(Tag.createTag(PriorityTag.PRIORITY_TAG_PREFIX + PriorityTag.LowPriority.TERM)
                .getPriority() == PriorityTag.LowPriority.PRIORITY);

        //case for a normal tag
        assertTrue(new Tag("test").getPriority() == TagUtil.NO_PRIORITY);
    }

}
