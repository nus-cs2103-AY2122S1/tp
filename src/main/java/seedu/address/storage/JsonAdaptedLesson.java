package seedu.address.storage;

import java.time.DayOfWeek;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Grade;

/**
 * Jackson-friendly version of {@link Lesson}.
 */
public class JsonAdaptedLesson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Lesson's %s field is missing!";

    private final String lessonCode;
    private final Double price;

    /**
     * Constructs a {@code JsonAdaptedLesson} with the given lesson details.
     */
    @JsonCreator
    public JsonAdaptedLesson(@JsonProperty("lessonCode") String lessonCode, @JsonProperty("price") Double price) {
        this.lessonCode = lessonCode;
        this.price = price;
    }

    /**
     * Converts a given {@code Lesson} into this class for Jackson use.
     */
    public JsonAdaptedLesson(Lesson source) {
        lessonCode = source.getLessonCode();
        price = source.getPrice();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Lesson} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted lesson.
     */
    public Lesson toModelType() throws IllegalValueException {
        if (lessonCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "code"));
        }
        if (!Lesson.isValidLessonCode(lessonCode)) {
            throw new IllegalValueException(Lesson.CODE_MESSAGE_CONSTRAINT);
        }

        final Lesson weakLesson = Lesson.getWeakLessonFromCode(lessonCode);
        final String subject = weakLesson.getSubject();
        final Grade grade = weakLesson.getGrade();
        final DayOfWeek dayOfWeek = weakLesson.getDayOfWeek();
        final LocalTime startTime = weakLesson.getStartTime();

        if (price == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "price"));
        }
        if (!Lesson.isValidPrice(price)) {
            throw new IllegalValueException(Lesson.PRICE_MESSAGE_CONSTRAINT);
        }

        return new Lesson(subject, grade, dayOfWeek, startTime, price);
    }
}
