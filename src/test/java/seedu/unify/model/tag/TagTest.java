package seedu.unify.model.tag;

import static seedu.unify.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.unify.model.task.Tag;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagTaskName_throwsIllegalArgumentException() {
        String invalidTagTaskName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagTaskName));
    }

    @Test
    public void isValidTagTaskName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagTaskName(null));
    }

}
