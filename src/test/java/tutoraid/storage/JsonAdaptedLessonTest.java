package tutoraid.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import tutoraid.commons.exceptions.IllegalValueException;
import tutoraid.model.lesson.Capacity;
import tutoraid.model.lesson.LessonName;
import tutoraid.model.lesson.Price;
import tutoraid.model.lesson.Timing;
import tutoraid.testutil.Assert;
import tutoraid.testutil.TypicalLessons;

public class JsonAdaptedLessonTest {

    private static final String INVALID_LESSON_NAME = "M@ths 1";
    private static final String INVALID_LESSON_CAPACITY = "fifty";
    private static final String INVALID_LESSON_PRICE = "one hundred";
    private static final String INVALID_LESSON_TIMING = " ";

    private static final String VALID_LESSON_NAME = TypicalLessons.SCIENCE_ONE.getLessonName().toString();
    private static final String VALID_LESSON_CAPACITY = TypicalLessons.SCIENCE_ONE.getCapacity().toString();
    private static final String VALID_LESSON_PRICE = TypicalLessons.SCIENCE_ONE.getPrice().toString();
    private static final ArrayList<JsonAdaptedStudent> VALID_LESSON_STUDENTS =
            (ArrayList<JsonAdaptedStudent>) TypicalLessons.SCIENCE_ONE.getStudents().students.stream()
                    .map(JsonAdaptedStudent::new).collect(Collectors.toList());
    private static final String VALID_LESSON_TIMING = TypicalLessons.SCIENCE_ONE.getTiming().toString();

    @Test
    public void toModelType_validLessonDetails_returnsLesson() throws Exception {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(TypicalLessons.SCIENCE_ONE);
        assertEquals(TypicalLessons.SCIENCE_ONE, lesson.toModelType());
    }

    @Test
    public void toModelType_invalidLessonName_throwsIllegalValueException() {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(INVALID_LESSON_NAME, VALID_LESSON_CAPACITY,
                VALID_LESSON_PRICE, VALID_LESSON_STUDENTS, VALID_LESSON_TIMING);

        String expectedMessage = LessonName.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_nullLessonName_throwsIllegalValueException() {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(null, VALID_LESSON_CAPACITY,
                VALID_LESSON_PRICE, VALID_LESSON_STUDENTS, VALID_LESSON_TIMING);

        String expectedMessage = String.format(
                JsonAdaptedLesson.MISSING_FIELD_MESSAGE_FORMAT, LessonName.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidCapacity_throwsIllegalValueException() {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(VALID_LESSON_NAME, INVALID_LESSON_CAPACITY,
                VALID_LESSON_PRICE, VALID_LESSON_STUDENTS, VALID_LESSON_TIMING);

        String expectedMessage = Capacity.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidPrice_throwsIllegalValueException() {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(VALID_LESSON_NAME, VALID_LESSON_CAPACITY,
                INVALID_LESSON_PRICE, VALID_LESSON_STUDENTS, VALID_LESSON_TIMING);

        String expectedMessage = Price.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidTiming_throwsIllegalValueException() {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(VALID_LESSON_NAME, VALID_LESSON_CAPACITY,
                VALID_LESSON_PRICE, VALID_LESSON_STUDENTS, INVALID_LESSON_TIMING);

        String expectedMessage = Timing.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }
}
