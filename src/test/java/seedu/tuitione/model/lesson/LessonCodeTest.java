package seedu.tuitione.model.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tuitione.model.lesson.LessonCode.CODE_MESSAGE_CONSTRAINT;
import static seedu.tuitione.testutil.Assert.assertThrows;
import static seedu.tuitione.testutil.LessonBuilder.DEFAULT_LESSON_CODE;
import static seedu.tuitione.testutil.LessonBuilder.DEFAULT_LESSON_TIME;

import org.junit.jupiter.api.Test;

public class LessonCodeTest {

    private static final String[] INVALID_CODES = new String[] {
        "", // empty
        "S1-Wed-1500", // fewer params
        "Science-S1-Wed-15:00", // invalid time format
        "Science-S1-Wednesday-1500", // invalid date format
        "Science-L3-Wed-1500", // invalid grade
        "!!!-S1-Wed-1500" // invalid subject
    };

    @Test
    public void constructor_validLessonCode_returnsLessonCode() {
        LessonCode otherLessonCode = new LessonCode(DEFAULT_LESSON_CODE.value);
        assertEquals(DEFAULT_LESSON_CODE, otherLessonCode);
    }

    @Test
    public void constructor_invalidLessonCode_throwsIllegalArgumentException() {
        for (String code : INVALID_CODES) {
            assertThrows(IllegalArgumentException.class, CODE_MESSAGE_CONSTRAINT, () -> new LessonCode(code));
        }
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LessonCode(null));
    }

    @Test
    public void isValidLessonCode() {
        // null code -> false
        assertFalse(LessonCode.isValidLessonCode(null));

        // valid code -> true
        assertTrue(LessonCode.isValidLessonCode(DEFAULT_LESSON_CODE.value));

        // invalid code -> false
        for (String code : INVALID_CODES) {
            assertFalse(LessonCode.isValidLessonCode(code));
        }
    }

    @Test
    public void getStartTimeFromCode() {
        assertEquals(DEFAULT_LESSON_TIME, LessonCode.getLessonTimeFromCode(DEFAULT_LESSON_CODE));
    }

    @Test
    public void equals() {
        // this -> true
        assertEquals(DEFAULT_LESSON_CODE, DEFAULT_LESSON_CODE);

        // null -> false
        assertNotEquals(DEFAULT_LESSON_CODE, null);

        // different instance -> false
        assertNotEquals(DEFAULT_LESSON_CODE, 5);

        // diff value -> false
        LessonCode differentLessonCode = new LessonCode("Maths-P3-Fri-1200");
        assertNotEquals(DEFAULT_LESSON_CODE, differentLessonCode);

        // same value but diff instance -> true
        LessonCode otherLessonCode = new LessonCode(DEFAULT_LESSON_CODE.value);
        assertEquals(DEFAULT_LESSON_CODE, otherLessonCode);
    }
}
