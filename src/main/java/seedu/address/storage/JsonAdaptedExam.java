package seedu.address.storage;

import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Subject;
import seedu.address.model.person.Exam;

/**
 * Jackson-friendly version of {@link Exam}.
 */
public class JsonAdaptedExam {
    private final String subject;
    private final String datetime;

    /**
     * Constructs a {@code JsonAdaptedExam} with the given exam details.
     */
    @JsonCreator
    public JsonAdaptedExam(@JsonProperty("subject") String subject, @JsonProperty("dayOfWeek") String datetime) {
        this.subject = subject;
        this.datetime = datetime;
    }

    /**
     * Converts a given {@code Exam} into this class for Jackson use.
     */
    public JsonAdaptedExam(Exam source) {
        this.subject = source.getSubject().getSubjectName();
        this.datetime = ParserUtil.localDateTimeAsString(source.getDateTime());
    }

    /**
     * Converts this Jackson-friendly adapted exam object into the model's {@code Exam} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted exam.
     */
    public Exam toModelType() throws IllegalValueException {
        if (subject == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Subject.class.getSimpleName()));
        } else if (!Subject.isValidSubject(subject)) {
            throw new IllegalValueException(Subject.MESSAGE_CONSTRAINTS);
        }

        Subject subject = new Subject(this.subject);

        LocalDateTime localDateTime;
        try {
            localDateTime = ParserUtil.parseLocalDateTime(datetime);
        } catch (ParseException p) {
            throw new IllegalValueException(p.getMessage());
        }

        return new Exam(subject, localDateTime);
    }
}
