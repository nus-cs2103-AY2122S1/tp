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
    private final String insuranceBrand;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedInsurance(String insuranceType, String insuranceBrand) {
        this.insuranceType = insuranceType;
        this.insuranceBrand = insuranceBrand;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedInsurance(Insurance source) {
        insuranceType = source.getTypeName();
        insuranceBrand = source.getBrand();
    }

    @JsonValue
    public String getInsuranceType() {
        return insuranceType;
    }

    @JsonValue
    public String getInsuranceBrand() {
        return insuranceBrand;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Insurance toModelType() throws IllegalValueException {
        return Insurance.of(insuranceType, insuranceBrand);
    }

}
