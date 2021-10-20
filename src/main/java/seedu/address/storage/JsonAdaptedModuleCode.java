package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lessoncode.LessonCode;
import seedu.address.model.person.ModuleCode;

/**
 * Jackson-friendly version of {@link ModuleCode}.
 */
public class JsonAdaptedModuleCode {

    private final String moduleCodeName;
    private final List<JsonAdaptedLessonCode> lessonCodes = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedModuleCode} with the given {@code moduleCodeName}.
     *
     * @param moduleCodeName The given module code name.
     */
    @JsonCreator
    public JsonAdaptedModuleCode(@JsonProperty("moduleCode") String moduleCodeName,
                                 @JsonProperty("lessonCodes") List<JsonAdaptedLessonCode> lessonCodes) {
        this.moduleCodeName = moduleCodeName;
        if (lessonCodes != null) {
            this.lessonCodes.addAll(lessonCodes);
        }
    }

    /**
     * Converts a given {@code ModuleCode} into this class for Jackson use.
     *
     * @param source The given ModuleCode.
     */
    public JsonAdaptedModuleCode(ModuleCode source) {
        moduleCodeName = source.value;
        lessonCodes.addAll(source.getLessonCodes().stream()
                .map(JsonAdaptedLessonCode::new)
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

        final List<LessonCode> moduleCodeLessonCodes = new ArrayList<>();
        for (JsonAdaptedLessonCode lessonCode: lessonCodes) {
            moduleCodeLessonCodes.add(lessonCode.toModelType());
        }
        final Set<LessonCode> modelLessonCodes = new HashSet<>(moduleCodeLessonCodes);
        return new ModuleCode(moduleCodeName, modelLessonCodes);
    }
}
