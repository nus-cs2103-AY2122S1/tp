package tutoraid.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import tutoraid.testutil.Assert;

public class LessonNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new LessonName(null));
    }

    @Test
    public void constructor_invalidStudentLessonName_throwsIllegalArgumentException() {
        String invalidLessonName = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new LessonName(invalidLessonName));
    }

    @Test
    public void isValidLessonName() {
        // null LessonName
        Assert.assertThrows(NullPointerException.class, () -> LessonName.isValidLessonName(null));

        // invalid LessonName
        assertFalse(LessonName.isValidLessonName("")); // empty string
        assertFalse(LessonName.isValidLessonName(" ")); // spaces only
        assertFalse(LessonName.isValidLessonName("^")); // only non-alphanumeric characters
        assertFalse(LessonName.isValidLessonName("Math*")); // contains non-alphanumeric characters

        // valid LessonName
        assertTrue(LessonName.isValidLessonName("E Math")); // alphabets only
        assertTrue(LessonName.isValidLessonName("12345")); // numbers only
        assertTrue(LessonName.isValidLessonName("H2 Math")); // alphanumeric characters
        assertTrue(LessonName.isValidLessonName("ENGLISH LANGUAGE")); // with capital letters
        assertTrue(LessonName.isValidLessonName("NUS CS2103T Team Project")); // long LessonNames
    }
}
