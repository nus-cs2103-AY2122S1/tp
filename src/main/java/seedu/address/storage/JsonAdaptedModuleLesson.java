package seedu.address.storage;

import static seedu.address.logic.parser.ParserUtil.parseModuleCode;

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

    private final String moduleCode;
    private final String lessonDay;
    private final String lessonStartTime;
    private final String lessonEndTime;
    private final String remark;

    /**
     * Constructs a {@code JsonAdaptedModuleLesson} with the given lesson details.
     *
     */
    @JsonCreator
    public JsonAdaptedModuleLesson(@JsonProperty("moduleCodes") String moduleCode,
                                   @JsonProperty("lessonDay") String lessonDay,
                                   @JsonProperty("lessonStartTime") String lessonStartTime,
                                   @JsonProperty("lessonEndTime") String lessonEndTime,
                                   @JsonProperty("remark") String remark) {
        this.moduleCode = moduleCode;
        this.lessonDay = lessonDay;
        this.lessonStartTime = lessonStartTime;
        this.lessonEndTime = lessonEndTime;
        this.remark = remark;
    }

    /**
     * Converts a given {@code ModuleLesson} into this lesson for Jackson use.
     */
    public JsonAdaptedModuleLesson(ModuleLesson source) {
        int length = source.getModuleCode().toString().length();
        moduleCode = source.getModuleCode().toString().substring(1, length - 1);
        lessonDay = source.getDay().getDayAsIntString();
        lessonStartTime = source.getLessonStartTime().toString();
        lessonEndTime = source.getLessonEndTime().toString();
        remark = source.getRemark().value;
    }

    /**
     * Converts this Jackson-friendly adapted lesson object into the model's {@code ModuleLesson} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module lesson.
     */
    public ModuleLesson toModelType() throws IllegalValueException {
        if (this.moduleCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleCode.class.getSimpleName()));
        }

        final ModuleCode moduleCode = parseModuleCode(this.moduleCode);

        if (this.lessonDay == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, LessonDay.class.getSimpleName())
            );
        }
        final LessonDay lessonDay = new LessonDay(this.lessonDay);

        if (this.lessonStartTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Lesson start time"));
        }
        final LessonTime lessonStartTime = new LessonTime(this.lessonStartTime);

        if (this.lessonEndTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Lesson end time"));
        }
        final LessonTime lessonEndTime = new LessonTime(this.lessonEndTime);

        if (this.remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        final Remark remark = new Remark(this.remark);

        return new ModuleLesson(moduleCode, lessonDay, lessonStartTime, lessonEndTime, remark);
    }

}
