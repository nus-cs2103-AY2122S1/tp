package tutoraid.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutoraid.logic.commands.CommandTestUtil.VALID_CAPACITY_MATHS_TWO;
import static tutoraid.logic.commands.CommandTestUtil.VALID_LESSON_NAME_MATHS_TWO;
import static tutoraid.logic.commands.CommandTestUtil.VALID_PRICE_MATHS_TWO;
import static tutoraid.logic.commands.CommandTestUtil.VALID_TIMING_SCIENCE_TWO;

import org.junit.jupiter.api.Test;

import tutoraid.testutil.LessonBuilder;
import tutoraid.testutil.TypicalLessons;

public class LessonTest {
    @Test
    public void isSameLesson() {
        // same object -> returns true
        assertTrue(TypicalLessons.MATHS_ONE.isSameLesson(TypicalLessons.MATHS_ONE));

        // null -> returns false
        assertFalse(TypicalLessons.MATHS_ONE.isSameLesson(null));

        // same name, all other attributes different -> returns true
        Lesson editedMathsOne = new LessonBuilder(TypicalLessons.MATHS_ONE).withLessonName("Maths 1")
                .withCapacity("51")
                .withPrice("101")
                .withTiming("1001-1201")
                .build();;
        assertTrue(TypicalLessons.MATHS_ONE.isSameLesson(editedMathsOne));

        // different name, all other attributes same -> returns false
        editedMathsOne =
                new LessonBuilder(TypicalLessons.MATHS_ONE).withLessonName(VALID_LESSON_NAME_MATHS_TWO).build();
        assertFalse(TypicalLessons.MATHS_ONE.isSameLesson(editedMathsOne));

        // name differs in case, all other attributes same -> returns true
        Lesson editedMathsTwo = new LessonBuilder(TypicalLessons.MATHS_TWO).withLessonName(
                VALID_LESSON_NAME_MATHS_TWO.toLowerCase()).build();
        assertTrue(TypicalLessons.MATHS_TWO.isSameLesson(editedMathsTwo));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_LESSON_NAME_MATHS_TWO + " ";
        editedMathsTwo = new LessonBuilder(TypicalLessons.MATHS_TWO).withLessonName(nameWithTrailingSpaces).build();
        assertFalse(TypicalLessons.MATHS_TWO.isSameLesson(editedMathsTwo));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Lesson mathsOneCopy = new LessonBuilder(TypicalLessons.MATHS_ONE).build();
        assertTrue(TypicalLessons.MATHS_ONE.equals(mathsOneCopy));

        // same object -> returns true
        assertTrue(TypicalLessons.MATHS_ONE.equals(TypicalLessons.MATHS_ONE));

        // null -> returns false
        assertFalse(TypicalLessons.MATHS_ONE.equals(null));

        // different type -> returns false
        assertFalse(TypicalLessons.MATHS_ONE.equals(5));

        // different lesson -> returns false
        assertFalse(TypicalLessons.MATHS_ONE.equals(TypicalLessons.MATHS_TWO));

        // different lesson name -> returns false
        Lesson editedMathsOne =
                new LessonBuilder(TypicalLessons.MATHS_ONE).withLessonName(VALID_LESSON_NAME_MATHS_TWO).build();
        assertFalse(TypicalLessons.MATHS_ONE.equals(editedMathsOne));

        // different price -> returns false
        editedMathsOne = new LessonBuilder(TypicalLessons.MATHS_ONE).withPrice(VALID_PRICE_MATHS_TWO).build();
        assertFalse(TypicalLessons.MATHS_ONE.equals(editedMathsOne));

        // different capacity -> returns false
        editedMathsOne = new LessonBuilder(TypicalLessons.MATHS_ONE).withCapacity(VALID_CAPACITY_MATHS_TWO).build();
        assertFalse(TypicalLessons.MATHS_ONE.equals(editedMathsOne));

        // different timing -> returns false
        editedMathsOne = new LessonBuilder(TypicalLessons.MATHS_ONE).withTiming(VALID_TIMING_SCIENCE_TWO).build();
        assertFalse(TypicalLessons.MATHS_ONE.equals(editedMathsOne));
    }
}
