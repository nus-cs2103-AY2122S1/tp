package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTeachingAssistantBuddy;
import seedu.address.model.TeachingAssistantBuddy;
import seedu.address.model.module.Module;

/**
 * An Immutable TeachingAssistantBuddy that is serializable to JSON format.
 */
@JsonRootName(value = "assistantbuddy")
class JsonSerializableAssistantBuddy {

    public static final String MESSAGE_DUPLICATE_MODULE = "Module list contains duplicate module(s)";

    private final List<JsonAdaptedModule> modules = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAssistantBuddy} with the given modules.
     */
    @JsonCreator
    public JsonSerializableAssistantBuddy(@JsonProperty("modules") List<JsonAdaptedModule> modules) {
        this.modules.addAll(modules);
    }

    /**
     * Converts a given {@code ReadOnlyTeachingAssistantBuddy} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAssistantBuddy}.
     */
    public JsonSerializableAssistantBuddy(ReadOnlyTeachingAssistantBuddy source) {
        modules.addAll(source.getModuleList().stream().map(JsonAdaptedModule::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code TeachingAssistantBuddy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TeachingAssistantBuddy toModelType() throws IllegalValueException {
        TeachingAssistantBuddy teachingAssistantBuddy = new TeachingAssistantBuddy();
        for (JsonAdaptedModule jsonAdaptedModule : modules) {
            Module module = jsonAdaptedModule.toModelType();
            if (teachingAssistantBuddy.hasModule(module)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULE);
            }
            teachingAssistantBuddy.addModule(module);
        }
        return teachingAssistantBuddy;
    }

}
