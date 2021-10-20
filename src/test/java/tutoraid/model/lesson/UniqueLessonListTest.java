package tutoraid.model.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutoraid.logic.commands.CommandTestUtil.VALID_PRICE_MATHS_TWO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import tutoraid.model.lesson.exceptions.DuplicateLessonException;
import tutoraid.model.lesson.exceptions.LessonNotFoundException;
import tutoraid.testutil.Assert;
import tutoraid.testutil.LessonBuilder;
import tutoraid.testutil.TypicalLessons;

public class UniqueLessonListTest {

    private final UniqueLessonList uniqueLessonList = new UniqueLessonList();

    @Test
    public void contains_nullLesson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueLessonList.contains(null));
    }

    @Test
    public void contains_lessonNotInList_returnsFalse() {
        assertFalse(uniqueLessonList.contains(TypicalLessons.MATHS_ONE));
    }

    @Test
    public void contains_lessonInList_returnsTrue() {
        uniqueLessonList.add(TypicalLessons.MATHS_ONE);
        assertTrue(uniqueLessonList.contains(TypicalLessons.MATHS_ONE));
    }

    @Test
    public void contains_lessonWithSameIdentityFieldsInList_returnsTrue() {
        uniqueLessonList.add(TypicalLessons.MATHS_ONE);
        Lesson editedMathsOne = new LessonBuilder(TypicalLessons.MATHS_ONE).withPrice(VALID_PRICE_MATHS_TWO).build();
        assertTrue(uniqueLessonList.contains(editedMathsOne));
    }

    @Test
    public void add_nullLesson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueLessonList.add(null));
    }

    @Test
    public void add_duplicateLesson_throwsDuplicateLessonException() {
        uniqueLessonList.add(TypicalLessons.MATHS_ONE);
        Assert.assertThrows(DuplicateLessonException.class, () -> uniqueLessonList.add(TypicalLessons.MATHS_ONE));
    }

    @Test
    public void setLesson_nullTargetLesson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueLessonList.setLesson(
                null, TypicalLessons.MATHS_ONE));
    }

    @Test
    public void setLesson_nullEditedLesson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueLessonList.setLesson(
                TypicalLessons.MATHS_ONE, null));
    }

    @Test
    public void setLesson_targetLessonNotInList_throwsLessonNotFoundException() {
        Assert.assertThrows(LessonNotFoundException.class, () -> uniqueLessonList.setLesson(
                TypicalLessons.MATHS_ONE, TypicalLessons.MATHS_ONE));
    }

    @Test
    public void setLesson_editedLessonIsSameLesson_success() {
        uniqueLessonList.add(TypicalLessons.MATHS_ONE);
        uniqueLessonList.setLesson(TypicalLessons.MATHS_ONE, TypicalLessons.MATHS_ONE);
        UniqueLessonList expectedUniqueLessonList = new UniqueLessonList();
        expectedUniqueLessonList.add(TypicalLessons.MATHS_ONE);
        assertEquals(expectedUniqueLessonList, uniqueLessonList);
    }

    @Test
    public void setLesson_editedLessonHasSameIdentity_success() {
        uniqueLessonList.add(TypicalLessons.MATHS_ONE);
        Lesson editedMathsOne = new LessonBuilder(TypicalLessons.MATHS_ONE)
                .withPrice(TypicalLessons.MATHS_ONE.getPrice().toString()).build();
        uniqueLessonList.setLesson(TypicalLessons.MATHS_ONE, editedMathsOne);
        UniqueLessonList expectedUniqueLessonList = new UniqueLessonList();
        expectedUniqueLessonList.add(editedMathsOne);
        assertEquals(expectedUniqueLessonList, uniqueLessonList);
    }

    @Test
    public void setLesson_editedLessonHasDifferentIdentity_success() {
        uniqueLessonList.add(TypicalLessons.MATHS_ONE);
        uniqueLessonList.setLesson(TypicalLessons.MATHS_ONE, TypicalLessons.MATHS_TWO);
        UniqueLessonList expectedUniqueLessonList = new UniqueLessonList();
        expectedUniqueLessonList.add(TypicalLessons.MATHS_TWO);
        assertEquals(expectedUniqueLessonList, uniqueLessonList);
    }

    @Test
    public void setLesson_editedLessonHasNonUniqueIdentity_throwsDuplicateLessonException() {
        uniqueLessonList.add(TypicalLessons.MATHS_ONE);
        uniqueLessonList.add(TypicalLessons.MATHS_TWO);
        Assert.assertThrows(DuplicateLessonException.class, () -> uniqueLessonList.setLesson(
                TypicalLessons.MATHS_ONE, TypicalLessons.MATHS_TWO));
    }

    @Test
    public void remove_nullLesson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueLessonList.remove(null));
    }

    @Test
    public void remove_lessonDoesNotExist_throwsLessonNotFoundException() {
        Assert.assertThrows(LessonNotFoundException.class, () -> uniqueLessonList.remove(TypicalLessons.MATHS_ONE));
    }

    @Test
    public void remove_existingLesson_removesLesson() {
        uniqueLessonList.add(TypicalLessons.MATHS_ONE);
        uniqueLessonList.remove(TypicalLessons.MATHS_ONE);
        UniqueLessonList expectedUniqueLessonList = new UniqueLessonList();
        assertEquals(expectedUniqueLessonList, uniqueLessonList);
    }

    @Test
    public void setLessons_nullUniqueLessonList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueLessonList.setLessons((UniqueLessonList) null));
    }

    @Test
    public void setLessons_uniqueLessonList_replacesOwnListWithProvidedUniqueLessonList() {
        uniqueLessonList.add(TypicalLessons.MATHS_ONE);
        UniqueLessonList expectedUniqueLessonList = new UniqueLessonList();
        expectedUniqueLessonList.add(TypicalLessons.MATHS_TWO);
        uniqueLessonList.setLessons(expectedUniqueLessonList);
        assertEquals(expectedUniqueLessonList, uniqueLessonList);
    }

    @Test
    public void setLessons_nullList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueLessonList.setLessons((List<Lesson>) null));
    }

    @Test
    public void setLessons_list_replacesOwnListWithProvidedList() {
        uniqueLessonList.add(TypicalLessons.MATHS_ONE);
        List<Lesson> lessonList = Collections.singletonList(TypicalLessons.MATHS_TWO);
        uniqueLessonList.setLessons(lessonList);
        UniqueLessonList expectedUniqueLessonList = new UniqueLessonList();
        expectedUniqueLessonList.add(TypicalLessons.MATHS_TWO);
        assertEquals(expectedUniqueLessonList, uniqueLessonList);
    }

    @Test
    public void setLessons_listWithDuplicateLessons_throwsDuplicateLessonException() {
        List<Lesson> listWithDuplicateLessons = Arrays.asList(TypicalLessons.MATHS_ONE, TypicalLessons.MATHS_ONE);
        Assert.assertThrows(
                DuplicateLessonException.class, () -> uniqueLessonList.setLessons(listWithDuplicateLessons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, ()
            -> uniqueLessonList.asUnmodifiableObservableList().remove(0));
    }
}
