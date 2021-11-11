package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.application.Requirement;

/**
 * Jackson-friendly version of {@link Requirement}.
 */
class JsonAdaptedRequirement {

    private final String requirementName;

    /**
     * Constructs a {@code JsonAdaptedRequirement} with the given {@code requirementName}.
     */
    @JsonCreator
    public JsonAdaptedRequirement(String requirementName) {
        this.requirementName = requirementName;
    }

    /**
     * Converts a given {@code Requirement} into this class for Jackson use.
     */
    public JsonAdaptedRequirement(Requirement source) {
        requirementName = source.value;
    }

    @JsonValue
    public String getRequirementName() {
        return requirementName;
    }

    /**
     * Converts this Jackson-friendly adapted requirement object into the model's {@code Requirement} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted requirement.
     */
    public Requirement toModelType() throws IllegalValueException {
        if (!Requirement.isValidRequirement(requirementName)) {
            throw new IllegalValueException(Requirement.MESSAGE_CONSTRAINTS);
        }
        return new Requirement(requirementName);
    }

}
