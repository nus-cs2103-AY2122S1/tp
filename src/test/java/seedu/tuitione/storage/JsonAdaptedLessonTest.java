package seedu.tuitione.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.tuitione.model.lesson.LessonTime.TIME_FORMATTER;
import static seedu.tuitione.model.lesson.LessonTime.TIME_MESSAGE_CONSTRAINTS;
import static seedu.tuitione.model.lesson.LessonTime.parseDayToString;
import static seedu.tuitione.model.lesson.Price.PRICE_MESSAGE_CONSTRAINT;
import static seedu.tuitione.model.lesson.Subject.SUBJECT_MESSAGE_CONSTRAINTS;
import static seedu.tuitione.model.student.Grade.GRADE_MESSAGE_CONSTRAINTS;
import static seedu.tuitione.storage.JsonAdaptedLesson.INVALID_FIELD_MESSAGE_FORMAT;
import static seedu.tuitione.storage.JsonAdaptedLesson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.tuitione.testutil.Assert.assertThrows;
import static seedu.tuitione.testutil.TypicalLessons.SCIENCE_P2;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.tuitione.commons.exceptions.IllegalValueException;
import seedu.tuitione.model.lesson.Price;
import seedu.tuitione.model.lesson.Subject;
import seedu.tuitione.model.student.Grade;

public class JsonAdaptedLessonTest {

    private static final String INVALID_SUBJECT = "ab12&!";
    private static final String INVALID_GRADE = "F9";
    private static final String INVALID_START_TIME_BY_PARSING = "09:00";
    private static final String INVALID_START_TIME_BY_VALUE = "2300";
    private static final String INVALID_DAY = "Wednesday";
    private static final Double INVALID_PRICE = -3.5;

    private static final String VALID_SUBJECT = SCIENCE_P2.getSubject().value;
    private static final String VALID_GRADE = SCIENCE_P2.getGrade().value;
    private static final String VALID_START_TIME = SCIENCE_P2.getLessonTime().startTime.format(TIME_FORMATTER);
    private static final String VALID_DAY = parseDayToString(SCIENCE_P2.getLessonTime().dayOfWeek);
    private static final Double VALID_PRICE = SCIENCE_P2.getPrice().value;

    @Test
    public void toModelType_validLessonDetails_returnsLesson() throws IllegalValueException {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(SCIENCE_P2);
        assertEquals(SCIENCE_P2, lesson.toModelType());
    }

    @Test
    public void toModelType_invalidSubject_throwsIllegalValueException() {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(INVALID_SUBJECT, VALID_GRADE, VALID_START_TIME,
                VALID_DAY, VALID_PRICE);
        assertThrows(IllegalValueException.class, SUBJECT_MESSAGE_CONSTRAINTS, lesson::toModelType);
    }

    @Test
    public void toModelType_nullSubject_throwsIllegalValueException() {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(null, VALID_GRADE, VALID_START_TIME,
                VALID_DAY, VALID_PRICE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Subject.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidGrade_throwsIllegalValueException() {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(VALID_SUBJECT, INVALID_GRADE, VALID_START_TIME,
                VALID_DAY, VALID_PRICE);
        assertThrows(IllegalValueException.class, GRADE_MESSAGE_CONSTRAINTS, lesson::toModelType);
    }

    @Test
    public void toModelType_nullGrade_throwsIllegalValueException() {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(VALID_SUBJECT, null, VALID_START_TIME,
                VALID_DAY, VALID_PRICE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidStartTime_throwsIllegalValueException() {
        // invalid parsing
        JsonAdaptedLesson invalidParsingLesson = new JsonAdaptedLesson(VALID_SUBJECT, VALID_GRADE,
                INVALID_START_TIME_BY_PARSING, VALID_DAY, VALID_PRICE);
        String expectedMessage = String.format(INVALID_FIELD_MESSAGE_FORMAT, LocalTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, invalidParsingLesson::toModelType);

        // invalid time
        JsonAdaptedLesson invalidTimeLesson = new JsonAdaptedLesson(VALID_SUBJECT, VALID_GRADE,
                INVALID_START_TIME_BY_VALUE, VALID_DAY, VALID_PRICE);
        assertThrows(IllegalValueException.class, TIME_MESSAGE_CONSTRAINTS, invalidTimeLesson::toModelType);
    }

    @Test
    public void toModelType_nullStartTime_throwsIllegalValueException() {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(VALID_SUBJECT, VALID_GRADE, null,
                VALID_DAY, VALID_PRICE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidDay_throwsIllegalValueException() {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(VALID_SUBJECT, VALID_GRADE, VALID_START_TIME,
                INVALID_DAY, VALID_PRICE);
        String expectedMessage = String.format(INVALID_FIELD_MESSAGE_FORMAT, DayOfWeek.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_nullDay_throwsIllegalValueException() {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(VALID_SUBJECT, VALID_GRADE, VALID_START_TIME,
                null, VALID_PRICE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DayOfWeek.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidPrice_throwsIllegalValueException() {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(VALID_SUBJECT, VALID_GRADE, VALID_START_TIME,
                VALID_DAY, INVALID_PRICE);
        assertThrows(IllegalValueException.class, PRICE_MESSAGE_CONSTRAINT, lesson::toModelType);
    }

    @Test
    public void toModelType_nullPrice_throwsIllegalValueException() {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(VALID_SUBJECT, VALID_GRADE, VALID_START_TIME,
                VALID_DAY, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }
}
