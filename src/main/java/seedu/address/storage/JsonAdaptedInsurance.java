package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Insurance;

/**
 * Jackson-friendly version of {@link Insurance}.
 */
class JsonAdaptedInsurance {

    private final String insuranceType;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedInsurance(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedInsurance(Insurance source) {
        insuranceType = source.getType().getTypeName();
    }

    @JsonValue
    public String getinsuranceType() {
        return insuranceType;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Insurance toModelType() throws IllegalValueException {
        return Insurance.of(insuranceType);
    }

}
