package seedu.tuitione.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.tuitione.commons.exceptions.IllegalValueException;
import seedu.tuitione.model.remark.Remark;

/**
 * Jackson-friendly version of {@link Remark}.
 */
class JsonAdaptedRemark {

    private final String remarkName;

    /**
     * Constructs a {@code JsonAdaptedRemark} with the given {@code remarkName}.
     */
    @JsonCreator
    public JsonAdaptedRemark(String remarkName) {
        this.remarkName = remarkName;
    }

    /**
     * Converts a given {@code Remark} into this class for Jackson use.
     */
    public JsonAdaptedRemark(Remark source) {
        remarkName = source.remarkName;
    }

    @JsonValue
    public String getRemarkName() {
        return remarkName;
    }

    /**
     * Converts this Jackson-friendly adapted remark object into the model's {@code Remark} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted remark.
     */
    public Remark toModelType() throws IllegalValueException {
        if (!Remark.isValidRemarkName(remarkName)) {
            throw new IllegalValueException(Remark.MESSAGE_CONSTRAINTS);
        }
        return new Remark(remarkName);
    }

}
