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
    private final String day;
    private final String time;
    private final String remark;

    /**
     * Constructs a {@code JsonAdaptedModuleLesson} with the given lesson details.
     *
     */
    @JsonCreator
    public JsonAdaptedModuleLesson(@JsonProperty("moduleCodes") List<JsonAdaptedModuleCode> moduleCodes,
                                   @JsonProperty("day") String day,
                                   @JsonProperty("time") String time,
                                   @JsonProperty("remark") String remark) {
        if (moduleCodes != null) {
            this.moduleCodes.addAll(moduleCodes);
        }
        this.day = day;
        this.time = time;
        this.remark = remark;
    }

    /**
     * Converts a given {@code ModuleLesson} into this lesson for Jackson use.
     */
    public JsonAdaptedModuleLesson(ModuleLesson source) {
        moduleCodes.addAll(source.getModuleCodes().stream()
                .map(JsonAdaptedModuleCode::new)
                .collect(Collectors.toList()));
        day = source.getDay().getDayAsIntString();
        time = source.getTime().toString();
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

        if (day == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, LessonDay.class.getSimpleName()));
        }
        final LessonDay lessonDay = new LessonDay(day);

        if (time == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, LessonTime.class.getSimpleName()));
        }
        final LessonTime lessonTime = new LessonTime(time);

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        final Remark lessonRemark = new Remark(remark);

        return new ModuleLesson(modelModuleCodes, lessonDay, lessonTime, lessonRemark);
    }

}
