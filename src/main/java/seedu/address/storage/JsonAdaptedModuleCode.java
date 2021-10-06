package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.ModuleCode;

/**
 * Jackson-friendly version of {@link ModuleCode}.
 */
public class JsonAdaptedModuleCode {

    private final String moduleCodeName;

    /**
     * Constructs a {@code JsonAdaptedModuleCode} with the given {@code moduleCodeName}.
     *
     * @param moduleCodeName The given module code name.
     */
    @JsonCreator
    public JsonAdaptedModuleCode(String moduleCodeName) {
        this.moduleCodeName = moduleCodeName;
    }

    /**
     * Converts a given {@code ModuleCode} into this class for Jackson use.
     *
     * @param source The given ModuleCode.
     */
    public JsonAdaptedModuleCode(ModuleCode source) {
        moduleCodeName = source.value;
    }

    @JsonValue
    public String getModuleCodeName() {
        return moduleCodeName;
    }

    /**
     * Converts this Jackson-friendly adapted module code object into the model's {@code ModuleCode} object.
     *
     * @return The ModuleCode object.
     * @throws IllegalValueException If there were any data constraints violated in the adapted module code.
     */
    public ModuleCode toModelType() throws IllegalValueException {
        if (!ModuleCode.isValidModuleCode(moduleCodeName)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        return new ModuleCode(moduleCodeName);
    }
}
