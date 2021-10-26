package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.skill.Framework;

/**
 * Jackson-friendly version of {@link Framework}.
 */
class JsonAdaptedFramework {

    private final String frameworkName;

    /**
     * Constructs a {@code JsonAdaptedFramework} with the given {@code frameworkName}.
     */
    @JsonCreator
    public JsonAdaptedFramework(String frameworkName) {
        this.frameworkName = frameworkName;
    }

    /**
     * Converts a given {@code Framework} into this class for Jackson use.
     */
    public JsonAdaptedFramework(Framework source) {
        frameworkName = source.frameworkName;
    }

    @JsonValue
    public String getFrameworkName() {
        return frameworkName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Framework} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted framework.
     */
    public Framework toModelType() throws IllegalValueException {
        if (!Framework.isValidFrameworkName(frameworkName)) {
            throw new IllegalValueException(Framework.MESSAGE_CONSTRAINTS);
        }
        return new Framework(frameworkName);
    }

}
