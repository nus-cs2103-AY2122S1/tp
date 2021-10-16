package seedu.address.storage;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link ModuleCode}.
 */
public class JsonAdaptedModuleCode {

    private final String moduleCodeName;
    private final Set<Tag> tags;

    /**
     * Constructs a {@code JsonAdaptedModuleCode} with the given {@code moduleCodeName}.
     *
     * @param moduleCodeName The given module code name.
     */
    @JsonCreator
    public JsonAdaptedModuleCode(String moduleCodeName, Set<Tag> tags) {
        this.moduleCodeName = moduleCodeName;
        this.tags = tags;
    }

    /**
     * Converts a given {@code ModuleCode} into this class for Jackson use.
     *
     * @param source The given ModuleCode.
     */
    public JsonAdaptedModuleCode(ModuleCode source) {
        moduleCodeName = source.value;
        tags = source.tags;
    }

    @JsonValue
    public String getModuleCodeName() {
        return moduleCodeName;
    }

    @JsonValue
    public Set<Tag> getTags() {
        return tags;
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

        // Throw exception if there is a tag that is not valid
        if (!tags.stream().map(tag -> tag.tagName).allMatch(Tag::isValidTagName)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new ModuleCode(moduleCodeName, tags);
    }
}
