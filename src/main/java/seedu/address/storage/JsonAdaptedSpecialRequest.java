package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.customer.SpecialRequest;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedSpecialRequest {

    private final String specialRequestName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedSpecialRequest(String specialRequestName) {
        this.specialRequestName = specialRequestName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedSpecialRequest(SpecialRequest source) {
        specialRequestName = source.specialRequestName;
    }

    @JsonValue
    public String getSpecialRequestName() {
        return specialRequestName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public SpecialRequest toModelType() throws IllegalValueException {
        if (!SpecialRequest.isValidSpecialRequestName(specialRequestName)) {
            throw new IllegalValueException(SpecialRequest.MESSAGE_CONSTRAINTS);
        }
        return new SpecialRequest(specialRequestName);
    }

}
