package seedu.address.storage;

import static seedu.address.model.lesson.LessonTime.TIME_FORMATTER;
import static seedu.address.model.lesson.LessonTime.TIME_MESSAGE_CONSTRAINTS;
import static seedu.address.model.lesson.LessonTime.isValidTime;
import static seedu.address.model.lesson.LessonTime.parseDayToString;
import static seedu.address.model.lesson.LessonTime.parseStringToDay;
import static seedu.address.model.lesson.Price.PRICE_MESSAGE_CONSTRAINT;
import static seedu.address.model.lesson.Price.isValidPrice;
import static seedu.address.model.lesson.Subject.SUBJECT_MESSAGE_CONSTRAINTS;
import static seedu.address.model.lesson.Subject.isValidSubject;
import static seedu.address.model.person.Grade.GRADE_MESSAGE_CONSTRAINTS;
import static seedu.address.model.person.Grade.isValidGrade;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonTime;
import seedu.address.model.lesson.Price;
import seedu.address.model.lesson.Subject;
import seedu.address.model.person.Grade;

/**
 * Jackson-friendly version of {@link Lesson}.
 */
public class JsonAdaptedLesson {

    public static final String INVALID_FIELD_MESSAGE_FORMAT = "Lesson's %s field is invalid!";
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Lesson's %s field is missing!";

    private final String subject;
    private final String grade;
    private final String startTime;
    private final String day;
    private final Double price;

    /**
     * Constructs a {@code JsonAdaptedLesson} with the given lesson details.
     */
    @JsonCreator
    public JsonAdaptedLesson(@JsonProperty("subject") String subject, @JsonProperty("grade") String grade,
             @JsonProperty("startTime") String startTime, @JsonProperty("day") String day,
             @JsonProperty("price") Double price) {

        this.subject = subject;
        this.grade = grade;
        this.startTime = startTime;
        this.day = day;
        this.price = price;
    }

    /**
     * Converts a given {@code Lesson} into this class for Jackson use.
     */
    public JsonAdaptedLesson(Lesson source) {
        this.subject = source.getSubject().value;
        this.grade = source.getGrade().value;
        this.startTime = source.getLessonTime().startTime.format(TIME_FORMATTER);
        this.day = parseDayToString(source.getLessonTime().dayOfWeek);
        this.price = source.getPrice().value;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Lesson} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted lesson.
     */
    public Lesson toModelType() throws IllegalValueException {
        if (subject == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Subject.class.getSimpleName()));
        }
        if (!isValidSubject(subject)) {
            throw new IllegalValueException(SUBJECT_MESSAGE_CONSTRAINTS);
        }
        final Subject lessonSubject = new Subject(subject);

        if (grade == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.class.getSimpleName()));
        }
        if (!isValidGrade(grade)) {
            throw new IllegalValueException(GRADE_MESSAGE_CONSTRAINTS);
        }
        final Grade lessonGrade = new Grade(grade);

        LocalTime lessonStart;
        if (startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalTime.class.getSimpleName()));
        }
        try {
            lessonStart = LocalTime.parse(startTime, TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ParseException(String.format(INVALID_FIELD_MESSAGE_FORMAT, LocalTime.class.getSimpleName()));
        }
        if (!isValidTime(lessonStart)) {
            throw new IllegalValueException(TIME_MESSAGE_CONSTRAINTS);
        }
        final DayOfWeek lessonDay = parseStringToDay(day);
        if (lessonDay == null) {
            throw new IllegalValueException(String.format(INVALID_FIELD_MESSAGE_FORMAT,
                    DayOfWeek.class.getSimpleName()));
        }
        final LessonTime lessonTime = new LessonTime(lessonDay, lessonStart);

        if (price == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "price"));
        }
        if (!isValidPrice(price)) {
            throw new IllegalValueException(PRICE_MESSAGE_CONSTRAINT);
        }
        final Price lessonPrice = new Price(price);

        return new Lesson(lessonSubject, lessonGrade, lessonTime, lessonPrice);
    }
}
