package seedu.address.testutil;

import static seedu.address.model.util.SampleDataUtil.parseModuleCode;

import seedu.address.model.modulelesson.LessonDay;
import seedu.address.model.modulelesson.LessonTime;
import seedu.address.model.modulelesson.ModuleLesson;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Remark;

public class ModuleLessonBuilder {

    public static final ModuleCode DEFAULT_MODULE_CODE = parseModuleCode("CS2103 T17");
    public static final String DEFAULT_LESSON_DAY = "2";
    public static final String DEFAULT_LESSON_START_TIME = "15:00";
    public static final String DEFAULT_LESSON_END_TIME = "16:00";

    private ModuleCode moduleCode;
    private LessonDay lessonDay;
    private LessonTime lessonStartTime;
    private LessonTime lessonEndTime;
    private Remark remark;

    /**
     * Creates a {@code ModuleLessonBuilder} with the default details.
     */
    public ModuleLessonBuilder() {
        moduleCode = DEFAULT_MODULE_CODE;
        lessonDay = new LessonDay(DEFAULT_LESSON_DAY);
        lessonStartTime = new LessonTime(DEFAULT_LESSON_START_TIME);
        lessonEndTime = new LessonTime(DEFAULT_LESSON_END_TIME);
        remark = new Remark("");
    }

    /**
     * Initialises the ModuleClass with the data of {@code classToCopy}.
     */
    public ModuleLessonBuilder(ModuleLesson classToCopy) {
        moduleCode = classToCopy.getModuleCode();
        lessonDay = classToCopy.getDay();
        lessonStartTime = classToCopy.getLessonStartTime();
        lessonEndTime = classToCopy.getLessonEndTime();
        remark = classToCopy.getRemark();
    }

    /**
     * Sets the {@code moduleCode} of the {@code ModuleClass} that we are building.
     */
    public ModuleLessonBuilder withModuleCode(String moduleCode) {
        this.moduleCode = parseModuleCode(moduleCode);
        return this;
    }


    /**
     * Sets the {@code lessonDay} of the {@code ModuleLesson} that we are building.
     */
    public ModuleLessonBuilder withLessonDay(String lessonDay) {
        this.lessonDay = new LessonDay(lessonDay);
        return this;
    }

    /**
     * Sets the {@code lessonStartTime} of the {@code ModuleLesson} that we are building.
     */
    public ModuleLessonBuilder withLessonStartTime(String lessonStartTime) {
        this.lessonStartTime = new LessonTime(lessonStartTime);
        return this;
    }

    /**
     * Sets the {@code lessonEndTime} of the {@code ModuleLesson} that we are building.
     */
    public ModuleLessonBuilder withLessonEndTime(String lessonEndTime) {
        this.lessonEndTime = new LessonTime(lessonEndTime);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code ModuleLesson} that we are building.
     */
    public ModuleLessonBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    public ModuleLesson build() {
        return new ModuleLesson(moduleCode, lessonDay, lessonStartTime, lessonEndTime, remark);
    }



}
