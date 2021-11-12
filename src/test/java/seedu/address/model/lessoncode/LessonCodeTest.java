package seedu.address.model.lessoncode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LessonCodeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LessonCode(null));
    }

    @Test
    public void constructor_invalidLessonCode_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new LessonCode(invalidTagName));
    }

    @Test
    public void isValidLessonCode() {
        // null tag name
        assertThrows(NullPointerException.class, () -> LessonCode.isValidLessonCode(null));

        // invalid lesson code
        assertFalse(LessonCode.isValidLessonCode(" ")); // spaces only
        assertFalse(LessonCode.isValidLessonCode("(T12)")); // non-alphanumeric characters
        assertFalse(LessonCode.isValidLessonCode("T 12")); // with space

        // valid lesson code
        assertTrue(LessonCode.isValidLessonCode("T12")); // alphanumeric characters
        assertTrue(LessonCode.isValidLessonCode("t12")); // with lower case
    }

}
