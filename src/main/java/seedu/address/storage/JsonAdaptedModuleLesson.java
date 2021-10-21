package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.modulelesson.LessonDay;
import seedu.address.model.modulelesson.LessonTime;
import seedu.address.model.modulelesson.ModuleLesson;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Remark;


/**
 * Jackson-friendly version of {@link ModuleLesson}.
 */
public class JsonAdaptedModuleLesson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module Lesson's %s field is missing!";

    private final List<JsonAdaptedModuleCode> moduleCodes = new ArrayList<>();
    private final String lessonDay;
    private final String lessonTime;
    private final String remark;

    /**
     * Constructs a {@code JsonAdaptedModuleLesson} with the given lesson details.
     *
     */
    @JsonCreator
    public JsonAdaptedModuleLesson(@JsonProperty("moduleCodes") List<JsonAdaptedModuleCode> moduleCodes,
                                   @JsonProperty("lessonDay") String lessonDay,
                                   @JsonProperty("lessonTime") String lessonTime,
                                   @JsonProperty("remark") String remark) {
        if (moduleCodes != null) {
            this.moduleCodes.addAll(moduleCodes);
        }
        this.lessonDay = lessonDay;
        this.lessonTime = lessonTime;
        this.remark = remark;
    }

    /**
     * Converts a given {@code ModuleLesson} into this lesson for Jackson use.
     */
    public JsonAdaptedModuleLesson(ModuleLesson source) {
        moduleCodes.addAll(source.getModuleCodes().stream()
                .map(JsonAdaptedModuleCode::new)
                .collect(Collectors.toList()));
        lessonDay = source.getDay().getDayAsIntString();
        lessonTime = source.getTime().toString();
        remark = source.getRemark().value;
    }

    /**
     * Converts this Jackson-friendly adapted lesson object into the model's {@code ModuleLesson} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module lesson.
     */
    public ModuleLesson toModelType() throws IllegalValueException {
        if (moduleCodes == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Module.class.getSimpleName()));
        }
        final List<ModuleCode> lessonModuleCodes = new ArrayList<>();
        for (JsonAdaptedModuleCode moduleCode : moduleCodes) {
            lessonModuleCodes.add(moduleCode.toModelType());
        }
        final Set<ModuleCode> modelModuleCodes = new HashSet<>(lessonModuleCodes);

        if (lessonDay == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, LessonDay.class.getSimpleName())
            );
        }
        final LessonDay lessonDay = new LessonDay(this.lessonDay);

        if (lessonTime == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, LessonTime.class.getSimpleName())
            );
        }
        final LessonTime lessonTime = new LessonTime(this.lessonTime);

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        final Remark lessonRemark = new Remark(remark);

        return new ModuleLesson(modelModuleCodes, lessonDay, lessonTime, lessonRemark);
    }

}
