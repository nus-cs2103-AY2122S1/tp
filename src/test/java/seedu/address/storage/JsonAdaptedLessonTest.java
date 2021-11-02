package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLessons.MON_10_12_BIOLOGY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ValidateUtil;
import seedu.address.model.lesson.Subject;

class JsonAdaptedLessonTest {

    public static final String INVALID_SUBJECT = "-";
    public static final int INVALID_DAY_OF_WEEK = 1000;

    public static final String VALID_SUBJECT = "Hi";
    public static final int VALID_DAY_OF_WEEK = 7;
    public static final JsonAdaptedTimeslot VALID_TIMESLOT = new JsonAdaptedTimeslot(
            MON_10_12_BIOLOGY.getTimeslot());

    @Test
    void toModelType_validLessonDetails_returnsLesson() throws Exception {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(MON_10_12_BIOLOGY);
        assertEquals(MON_10_12_BIOLOGY, lesson.toModelType());
    }

    @Test
    void toModelType_invalidSubject_throwsIllegalValueException() {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(VALID_TIMESLOT, INVALID_SUBJECT, VALID_DAY_OF_WEEK);
        assertThrows(IllegalValueException.class, Subject.MESSAGE_CONSTRAINTS, lesson::toModelType);
    }

    @Test
    void toModelType_nullSubject_throwsIllegalValueException() {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(VALID_TIMESLOT, null, VALID_DAY_OF_WEEK);
        String expected = String.format(MISSING_FIELD_MESSAGE_FORMAT, Subject.class.getSimpleName());
        assertThrows(IllegalValueException.class, expected, lesson::toModelType);
    }

    @Test
    void toModelType_invalidDayOfWeek_throwsIllegalValueException() {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(VALID_TIMESLOT, VALID_SUBJECT, INVALID_DAY_OF_WEEK);
        assertThrows(IllegalValueException.class, ValidateUtil.DAY_OF_WEEK_CONSTRAINTS, lesson::toModelType);
    }
}
