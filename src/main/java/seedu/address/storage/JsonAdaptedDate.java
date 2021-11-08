package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lesson.Date;

/**
 * Jackson-friendly version of {@link Date}.
 */
class JsonAdaptedDate {

    private final String value;

    /**
     * Constructs a {@code JsonAdaptedDate} with the given {@code value}.
     */
    @JsonCreator
    public JsonAdaptedDate(String value) {
        this.value = value;
    }

    /**
     * Converts a given {@code Date} into this class for Jackson use.
     */
    public JsonAdaptedDate(Date source) {
        value = source.value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Converts this Jackson-friendly adapted date object into the model's {@code Date} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted date.
     */
    public Date toModelType() throws IllegalValueException {
        String strippedDate = value.strip();
        if (!Date.isValidDate(strippedDate)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(strippedDate);
    }

}
