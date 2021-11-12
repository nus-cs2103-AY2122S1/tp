package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_HOMEWORK_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_MON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_TUE;
import static seedu.address.storage.JsonAdaptedLesson.MESSAGE_INVALID_CANCELLED_DATE;
import static seedu.address.storage.JsonAdaptedLesson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLessons.MAKEUP_LESSON;
import static seedu.address.testutil.TypicalLessons.RECURRING_LESSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lesson.Date;
import seedu.address.model.lesson.LessonRates;
import seedu.address.model.lesson.OutstandingFees;
import seedu.address.model.lesson.Subject;
import seedu.address.model.lesson.TimeRange;

class JsonAdaptedLessonTest {

    private static final String INVALID_DATE = "29 Feb 2021";
    private static final String INVALID_TIME_RANGE = "0100-0200";
    private static final String INVALID_LESSON_RATES = "$50.00";
    private static final String INVALID_OUTSTANDING_FEES = "$100.00";
    private static final String INVALID_SUBJECT = "1^739;";
    private static final String INVALID_HOMEWORK = INVALID_HOMEWORK_DESC;

    private static final String VALID_DATE = RECURRING_LESSON.getDisplayDate().toString();
    private static final String VALID_END_DATE = RECURRING_LESSON.getEndDate().toString();
    private static final String VALID_TIME_RANGE = RECURRING_LESSON.getTimeRange().toString();
    private static final String VALID_LESSON_RATES = RECURRING_LESSON.getLessonRates().value;
    private static final String VALID_SUBJECT = RECURRING_LESSON.getSubject().toString();
    private static final String VALID_OUTSTANDING_FEES = RECURRING_LESSON.getOutstandingFees().value;
    private static final List<JsonAdaptedHomework> VALID_HOMEWORK_PIECES = RECURRING_LESSON
            .getHomework()
            .stream()
            .map(JsonAdaptedHomework::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedDate> EMPTY_CANCELLED_DATES = new ArrayList<>();

    @Test
    public void toModelType_validLessonDetails_returnsLesson() throws IllegalValueException {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(RECURRING_LESSON);
        assertEquals(RECURRING_LESSON, lesson.toModelType());

        lesson = new JsonAdaptedLesson(MAKEUP_LESSON);
        assertEquals(MAKEUP_LESSON, lesson.toModelType());
    }

    @Test
    public void toModelType_invalidStartDate_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(INVALID_DATE, VALID_END_DATE, VALID_TIME_RANGE, VALID_SUBJECT,
                        VALID_HOMEWORK_PIECES, VALID_LESSON_RATES, VALID_OUTSTANDING_FEES, EMPTY_CANCELLED_DATES);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidEndDate_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_DATE, INVALID_DATE, VALID_TIME_RANGE, VALID_SUBJECT,
                        VALID_HOMEWORK_PIECES, VALID_LESSON_RATES, VALID_OUTSTANDING_FEES, EMPTY_CANCELLED_DATES);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidDateRange_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_DATE_TUE, VALID_DATE_MON, VALID_TIME_RANGE, VALID_SUBJECT,
                        VALID_HOMEWORK_PIECES, VALID_LESSON_RATES, VALID_OUTSTANDING_FEES, EMPTY_CANCELLED_DATES);
        String expectedMessage = JsonAdaptedLesson.MESSAGE_INVALID_DATE_RANGE;
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_nullStartDate_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(null, VALID_END_DATE, VALID_TIME_RANGE, VALID_SUBJECT,
                        VALID_HOMEWORK_PIECES, VALID_LESSON_RATES, VALID_OUTSTANDING_FEES, EMPTY_CANCELLED_DATES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_nullEndDate_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_DATE, null, VALID_TIME_RANGE, VALID_SUBJECT,
                        VALID_HOMEWORK_PIECES, VALID_LESSON_RATES, VALID_OUTSTANDING_FEES, EMPTY_CANCELLED_DATES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidTimeRange_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_DATE, VALID_END_DATE, INVALID_TIME_RANGE, VALID_SUBJECT,
                        VALID_HOMEWORK_PIECES, VALID_LESSON_RATES, VALID_OUTSTANDING_FEES, EMPTY_CANCELLED_DATES);
        String expectedMessage = TimeRange.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_nullTimeRange_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_DATE, VALID_END_DATE, null, VALID_SUBJECT,
                        VALID_HOMEWORK_PIECES, VALID_LESSON_RATES, VALID_OUTSTANDING_FEES, EMPTY_CANCELLED_DATES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TimeRange.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidSubject_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_DATE, VALID_END_DATE, VALID_TIME_RANGE, INVALID_SUBJECT,
                        VALID_HOMEWORK_PIECES, VALID_LESSON_RATES, VALID_OUTSTANDING_FEES, EMPTY_CANCELLED_DATES);
        String expectedMessage = Subject.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_nullSubject_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_DATE, VALID_END_DATE, VALID_TIME_RANGE, null,
                        VALID_HOMEWORK_PIECES, VALID_LESSON_RATES, VALID_OUTSTANDING_FEES, EMPTY_CANCELLED_DATES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Subject.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidHomeworkPieces_throwsIllegalValueException() {
        List<JsonAdaptedHomework> invalidHomeworkPieces = new ArrayList<>(VALID_HOMEWORK_PIECES);
        invalidHomeworkPieces.add(new JsonAdaptedHomework(INVALID_HOMEWORK));
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_DATE, VALID_END_DATE, VALID_TIME_RANGE, VALID_SUBJECT,
                        invalidHomeworkPieces, VALID_LESSON_RATES, VALID_OUTSTANDING_FEES, EMPTY_CANCELLED_DATES);

        assertThrows(IllegalValueException.class, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidLessonRates_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_DATE, VALID_END_DATE, VALID_TIME_RANGE, VALID_SUBJECT,
                        VALID_HOMEWORK_PIECES, INVALID_LESSON_RATES, VALID_OUTSTANDING_FEES, EMPTY_CANCELLED_DATES);

        String expectedMessage = LessonRates.MESSAGE_FORMAT_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_nullLessonRates_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_DATE, VALID_END_DATE, VALID_TIME_RANGE, VALID_SUBJECT,
                        VALID_HOMEWORK_PIECES, null, VALID_OUTSTANDING_FEES, EMPTY_CANCELLED_DATES);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LessonRates.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidOutstandingFees_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_DATE, VALID_END_DATE, VALID_TIME_RANGE, VALID_SUBJECT,
                        VALID_HOMEWORK_PIECES, VALID_LESSON_RATES, INVALID_OUTSTANDING_FEES, EMPTY_CANCELLED_DATES);

        String expectedMessage = OutstandingFees.MESSAGE_FORMAT_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_nullOutstandingFees_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_DATE, VALID_END_DATE, VALID_TIME_RANGE, VALID_SUBJECT,
                        VALID_HOMEWORK_PIECES, VALID_LESSON_RATES, null, EMPTY_CANCELLED_DATES);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, OutstandingFees.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidCancelledDates_throwsIllegalValueException() {
        List<JsonAdaptedDate> invalidCancelledDates = new ArrayList<>();
        invalidCancelledDates.add(new JsonAdaptedDate(VALID_DATE_TUE));

        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_DATE_MON, VALID_END_DATE, VALID_TIME_RANGE, VALID_SUBJECT,
                        VALID_HOMEWORK_PIECES, VALID_LESSON_RATES, VALID_OUTSTANDING_FEES, invalidCancelledDates);

        String expectedMessage = MESSAGE_INVALID_CANCELLED_DATE;
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }
}
