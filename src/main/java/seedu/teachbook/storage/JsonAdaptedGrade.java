package seedu.teachbook.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.teachbook.commons.exceptions.IllegalValueException;
import seedu.teachbook.model.gradeobject.Grade;

public class JsonAdaptedGrade {
    private final String value;

    /**
     * Constructs a {@code JsonAdaptedGrade} with the given {@code value}.
     */
    @JsonCreator
    public JsonAdaptedGrade(String value) {
        this.value = value;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedGrade(Grade source) {
        value = source.value;
    }

    @JsonValue
    public String getGradeValue() {
        return value;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Grade} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted grade.
     */
    public Grade toModelType() throws IllegalValueException {
        return new Grade(value);
    }
}
