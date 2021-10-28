package seedu.address.storage;

import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;

import java.time.DayOfWeek;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ValidateUtil;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.Subject;
import seedu.address.model.lesson.Timeslot;

/**
 * Jackson-friendly version of {@link Lesson}.
 */
public class JsonAdaptedLesson {

    private final JsonAdaptedTimeslot timeslot;
    private final String subject;
    private final int dayOfWeek;

    /**
     * Constructs a {@code JsonAdaptedLesson} with the given lesson details.
     */
    @JsonCreator
    public JsonAdaptedLesson(@JsonProperty("timeslot") JsonAdaptedTimeslot timeslot,
            @JsonProperty("subject") String subject, @JsonProperty("dayOfWeek") int dayOfWeek) {
        this.timeslot = timeslot;
        this.subject = subject;
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Converts a given {@code Lesson} into this class for Jackson use.
     */
    public JsonAdaptedLesson(Lesson source) {
        this.timeslot = new JsonAdaptedTimeslot(source.getTimeslot());
        this.subject = source.getSubject().getSubjectName();
        this.dayOfWeek = source.getDayOfWeek().getValue();
    }

    /**
     * Converts this Jackson-friendly adapted lesson object into the model's {@code Lesson} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted lesson.
     */
    public Lesson toModelType() throws IllegalValueException {
        Timeslot timeslot = this.timeslot.toModelType();

        if (subject == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Subject.class.getSimpleName()));
        } else if (!Subject.isValidSubject(subject)) {
            throw new IllegalValueException(Subject.MESSAGE_CONSTRAINTS);
        }

        Subject subject = new Subject(this.subject);

        if (!ValidateUtil.validDayOfWeekInteger(dayOfWeek)) {
            throw new IllegalValueException(ValidateUtil.DAY_OF_WEEK_CONSTRAINTS);
        }

        DayOfWeek dayOfWeek = DayOfWeek.of(this.dayOfWeek);

        return new Lesson(timeslot, subject, dayOfWeek);
    }


}
