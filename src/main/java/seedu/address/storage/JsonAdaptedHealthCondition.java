package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.healthcondition.HealthCondition;

/**
 * Jackson-friendly version of {@link HealthCondition}.
 */
class JsonAdaptedHealthCondition {

    private final String healthCondition;

    /**
     * Constructs a {@code JsonAdaptedHealthCondition} with the given {@code healthCondition}.
     */
    @JsonCreator
    public JsonAdaptedHealthCondition(String healthCondition) {
        this.healthCondition = healthCondition;
    }

    /**
     * Converts a given {@code HealthCondition} into this class for Jackson use.
     */
    public JsonAdaptedHealthCondition(HealthCondition source) {
        healthCondition = source.healthCondition;
    }

    @JsonValue
    public String getHealthCondition() {
        return healthCondition;
    }

    /**
     * Converts this Jackson-friendly adapted healthCondition object into the model's {@code HealthCondition} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted healthCondition.
     */
    public HealthCondition toModelType() throws IllegalValueException {
        if (!HealthCondition.isValidHealthCondition(healthCondition)) {
            throw new IllegalValueException(HealthCondition.MESSAGE_CONSTRAINTS);
        }
        return new HealthCondition(healthCondition);
    }

}
