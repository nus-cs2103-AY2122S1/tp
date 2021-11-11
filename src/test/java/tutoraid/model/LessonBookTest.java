package tutoraid.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutoraid.logic.commands.CommandTestUtil.VALID_PRICE_SCIENCE_TWO;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.lesson.exceptions.DuplicateLessonException;
import tutoraid.testutil.Assert;
import tutoraid.testutil.LessonBuilder;
import tutoraid.testutil.TypicalLessons;

public class LessonBookTest {

    private final LessonBook lessonBook = new LessonBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), lessonBook.getLessonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> lessonBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyLessonBook_replacesData() {
        LessonBook newData = TypicalLessons.getTypicalLessonBook();
        lessonBook.resetData(newData);
        assertEquals(newData, lessonBook);
    }

    @Test
    public void resetData_withDuplicateLessons_throwsDuplicateLessonException() {
        // Two lessons with the same identity fields
        Lesson editedMathsOne = new LessonBuilder(TypicalLessons.MATHS_ONE).withPrice(VALID_PRICE_SCIENCE_TWO).build();
        List<Lesson> newLessons = Arrays.asList(TypicalLessons.MATHS_ONE, editedMathsOne);
        LessonBookStub newData = new LessonBookStub(newLessons);

        Assert.assertThrows(DuplicateLessonException.class, () -> lessonBook.resetData(newData));
    }

    @Test
    public void hasLesson_nullLesson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> lessonBook.hasLesson(null));
    }

    @Test
    public void hasLesson_lessonNotInLessonBook_returnsFalse() {
        assertFalse(lessonBook.hasLesson(TypicalLessons.MATHS_ONE));
    }

    @Test
    public void hasLesson_lessonInLessonBook_returnsTrue() {
        lessonBook.addLesson(TypicalLessons.MATHS_ONE);
        assertTrue(lessonBook.hasLesson(TypicalLessons.MATHS_ONE));
    }

    @Test
    public void hasLesson_lessonWithSameIdentityFieldsInLessonBook_returnsTrue() {
        lessonBook.addLesson(TypicalLessons.MATHS_ONE);
        Lesson editedMathsOne = new LessonBuilder(TypicalLessons.MATHS_ONE).withPrice(VALID_PRICE_SCIENCE_TWO).build();
        assertTrue(lessonBook.hasLesson(editedMathsOne));
    }

    @Test
    public void getLessonList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> lessonBook.getLessonList().remove(0));
    }

    /**
     * A stub ReadOnlyLessonBook whose lessons list can violate interface constraints.
     */
    private static class LessonBookStub implements ReadOnlyLessonBook {
        private final ObservableList<Lesson> lessons = FXCollections.observableArrayList();

        LessonBookStub(Collection<Lesson> lessons) {
            this.lessons.setAll(lessons);
        }

        @Override
        public ObservableList<Lesson> getLessonList() {
            return lessons;
        }
    }

}
