package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link ModuleCode}.
 */
public class JsonAdaptedModuleCode {

    private final String moduleCodeName;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedModuleCode} with the given {@code moduleCodeName}.
     *
     * @param moduleCodeName The given module code name.
     */
    @JsonCreator
    public JsonAdaptedModuleCode(@JsonProperty("moduleCode") String moduleCodeName,
                                 @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.moduleCodeName = moduleCodeName;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code ModuleCode} into this class for Jackson use.
     *
     * @param source The given ModuleCode.
     */
    public JsonAdaptedModuleCode(ModuleCode source) {
        moduleCodeName = source.value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

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

        final List<Tag> moduleCodeTags = new ArrayList<>();
        for (JsonAdaptedTag tag: tags) {
            moduleCodeTags.add(tag.toModelType());
        }
        final Set<Tag> modelTags = new HashSet<>(moduleCodeTags);
        return new ModuleCode(moduleCodeName, modelTags);
    }
}
