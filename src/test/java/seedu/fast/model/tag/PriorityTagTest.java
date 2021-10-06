package seedu.fast.model.tag;

import static seedu.fast.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PriorityTagTest {

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
    }

    @Test
    public void createTag() {
        //null term
        assertThrows(NullPointerException.class, () -> Tag.createTag(null));
    }

}
