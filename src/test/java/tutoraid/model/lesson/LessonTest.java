package tutoraid.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutoraid.logic.commands.CommandTestUtil.*;

import org.junit.jupiter.api.Test;

import tutoraid.testutil.LessonBuilder;
import tutoraid.testutil.TypicalLessons;

import java.util.ArrayList;
import java.util.Arrays;

public class LessonTest {
    @Test
    public void isSameLesson() {
        // same object -> returns true
        assertTrue(TypicalLessons.MATHS_ONE.isSameLesson(TypicalLessons.MATHS_ONE));

        // null -> returns false
        assertFalse(TypicalLessons.MATHS_ONE.isSameLesson(null));

        // same name, all other attributes different -> returns true
        Lesson editedMaths_One = new LessonBuilder(TypicalLessons.MATHS_ONE).withLessonName("Maths 1")
                .withCapacity("51")
                .withPrice("101")
                .withStudents(new ArrayList<>(Arrays.asList()))
                .withTiming("1001-1201")
                .build();;
        assertTrue(TypicalLessons.MATHS_ONE.isSameLesson(editedMaths_One));

        // different name, all other attributes same -> returns false
        editedMaths_One = new LessonBuilder(TypicalLessons.MATHS_ONE).withLessonName(VALID_LESSON_NAME_MATHS_TWO).build();
        assertFalse(TypicalLessons.MATHS_ONE.isSameLesson(editedMaths_One));

        // name differs in case, all other attributes same -> returns false
        Lesson editedMaths_Two = new LessonBuilder(TypicalLessons.MATHS_TWO).withLessonName(
                VALID_LESSON_NAME_MATHS_TWO.toLowerCase()).build();
        assertFalse(TypicalLessons.MATHS_TWO.isSameLesson(editedMaths_Two));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_LESSON_NAME_MATHS_TWO + " ";
        editedMaths_Two = new LessonBuilder(TypicalLessons.MATHS_TWO).withLessonName(nameWithTrailingSpaces).build();
        assertFalse(TypicalLessons.MATHS_TWO.isSameLesson(editedMaths_Two));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Lesson maths_One_Copy = new LessonBuilder(TypicalLessons.MATHS_ONE).build();
        assertTrue(TypicalLessons.MATHS_ONE.equals(maths_One_Copy));

        // same object -> returns true
        assertTrue(TypicalLessons.MATHS_ONE.equals(TypicalLessons.MATHS_ONE));

        // null -> returns false
        assertFalse(TypicalLessons.MATHS_ONE.equals(null));

        // different type -> returns false
        assertFalse(TypicalLessons.MATHS_ONE.equals(5));

        // different lesson -> returns false
        assertFalse(TypicalLessons.MATHS_ONE.equals(TypicalLessons.MATHS_TWO));

        // different lesson name -> returns false
        Lesson editedMaths_One = new LessonBuilder(TypicalLessons.MATHS_ONE).withLessonName(VALID_LESSON_NAME_MATHS_TWO).build();
        assertFalse(TypicalLessons.MATHS_ONE.equals(editedMaths_One));

        // different price -> returns false
        editedMaths_One = new LessonBuilder(TypicalLessons.MATHS_ONE).withPrice(VALID_PRICE_MATHS_TWO).build();
        assertFalse(TypicalLessons.MATHS_ONE.equals(editedMaths_One));

        // different capacity -> returns false
        editedMaths_One = new LessonBuilder(TypicalLessons.MATHS_ONE).withCapacity(VALID_CAPACITY_MATHS_TWO).build();
        assertFalse(TypicalLessons.MATHS_ONE.equals(editedMaths_One));

        // different timing -> returns false
        editedMaths_One = new LessonBuilder(TypicalLessons.MATHS_ONE).withTiming(VALID_TIMING_SCIENCE_TWO).build();
        assertFalse(TypicalLessons.MATHS_ONE.equals(editedMaths_One));
    }
}
