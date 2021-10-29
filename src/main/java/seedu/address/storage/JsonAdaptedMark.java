package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.StudentMark;

/**
 * Jackson-friendly version of {@link StudentMark}.
 */
class JsonAdaptedMark {

    private final String mark;

    /**
     * Constructs a {@code JsonAdapterMark} with the given {@code mark}.
     */
    @JsonCreator
    public JsonAdaptedMark(String mark) {
        this.mark = mark;
    }

    /**
     * Converts a given {@code StudentMark} into this class for Jackson use.
     */
    public JsonAdaptedMark(StudentMark source) {
        mark = source.name();
    }

    @JsonValue
    public String getMark() {
        return mark;
    }

    /**
     * Converts this Jackson-friendly adapted mark object into the model's {@code StudentMark} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public StudentMark toModelType() throws IllegalValueException {
        StudentMark newMark;
        try {
            newMark = StudentMark.valueOf(mark);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(StudentMark.MESSAGE_CONSTRAINTS);
        }
        return newMark;
    }

}
