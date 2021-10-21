package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lessoncode.LessonCode;

/**
 * Jackson-friendly version of {@link LessonCode}.
 */
class JsonAdaptedLessonCode {

    private final String lessonCode;

    /**
     * Constructs a {@code JsonAdaptedLessonCode} with the given {@code lessonCode}.
     */
    @JsonCreator
    public JsonAdaptedLessonCode(String lessonCode) {
        this.lessonCode = lessonCode;
    }

    /**
     * Converts a given {@code LessonCode} into this class for Jackson use.
     */
    public JsonAdaptedLessonCode(LessonCode source) {
        lessonCode = source.lessonCode;
    }

    @JsonValue
    public String getLessonCode() {
        return lessonCode;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code LessonCode} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public LessonCode toModelType() throws IllegalValueException {
        if (!LessonCode.isValidLessonCode(lessonCode)) {
            throw new IllegalValueException(LessonCode.MESSAGE_CONSTRAINTS);
        }
        return new LessonCode(lessonCode);
    }

}
