package seedu.edrecord.storage.module;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.edrecord.commons.exceptions.IllegalValueException;
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.module.ModuleSystem;
import seedu.edrecord.model.module.ReadOnlyModuleSystem;

/**
 * An Immutable ModuleSystem that is serializable to JSON format.
 */
@JsonRootName(value = "modulesystem")
class JsonSerializableModuleSystem {

    private final List<JsonAdaptedModule> modules = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableModuleSystem} with the given persons.
     */
    @JsonCreator
    public JsonSerializableModuleSystem(@JsonProperty("modules") List<JsonAdaptedModule> modules) {
        this.modules.addAll(modules);
    }

    /**
     * Converts a given {@code ReadOnlyModuleSystem} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableModuleSystem}.
     */
    public JsonSerializableModuleSystem(ReadOnlyModuleSystem source) {
        modules.addAll(source.getModuleList().stream().map(JsonAdaptedModule::new).collect(Collectors.toList()));
    }

    /**
     * Converts this module system into the model's {@code ModuleSystem} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ModuleSystem toModelType() throws IllegalValueException {
        ModuleSystem moduleSystem = Module.MODULE_SYSTEM;
        for (JsonAdaptedModule jsonAdaptedModule : modules) {
            Module mod = jsonAdaptedModule.toModelType();
            moduleSystem.addModule(mod);
        }
        return moduleSystem;
    }

}

