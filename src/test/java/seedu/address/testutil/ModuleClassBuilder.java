package seedu.address.testutil;

import static seedu.address.model.util.SampleDataUtil.getModuleCodeSet;

import java.util.Set;

import seedu.address.model.modulelesson.LessonDay;
import seedu.address.model.modulelesson.ModuleLesson;
import seedu.address.model.modulelesson.LessonTime;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Remark;

public class ModuleClassBuilder {

    public static final Set<ModuleCode> DEFAULT_MODULE_CODE = getModuleCodeSet("CS2103 T17");
    public static final String DEFAULT_DAY = "2";
    public static final String DEFAULT_TIME = "15:00";
    public static final String DEFAULT_REMARK = "Location: COM1 113";

    private Set<ModuleCode> moduleCode;
    private LessonDay lessonDay;
    private LessonTime lessonTime;
    private Remark remark;

    /**
     * Creates a {@code ModuleClassBuilder} with the default details.
     */
    public ModuleClassBuilder() {
        moduleCode = DEFAULT_MODULE_CODE;
        lessonDay = new LessonDay(DEFAULT_DAY);
        lessonTime = new LessonTime(DEFAULT_TIME);
        remark = new Remark(DEFAULT_REMARK);
    }

    /**
     * Initialises the ModuleClass with the data of {@code classToCopy}.
     */
    public ModuleClassBuilder(ModuleLesson classToCopy) {
        moduleCode = classToCopy.getModuleCodes();
        lessonDay = classToCopy.getDay();
        lessonTime = classToCopy.getTime();
        remark = classToCopy.getRemark();
    }

    /**
     * Sets the {@code moduleCode} of the {@code ModuleClass} that we are building.
     */
    public ModuleClassBuilder withModuleCode(String moduleCode) {
        this.moduleCode = getModuleCodeSet(moduleCode);
        return this;
    }


    /**
     * Sets the {@code day} of the {@code ModuleClass} that we are building.
     */
    public ModuleClassBuilder withDay(String day) {
        this.lessonDay = new LessonDay(day);
        return this;
    }

    /**
     * Sets the {@code time} of the {@code ModuleClass} that we are building.
     */
    public ModuleClassBuilder withTime(String time) {
        this.lessonTime = new LessonTime(time);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code ModuleClass} that we are building.
     */
    public ModuleClassBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    public ModuleLesson build() {
        return new ModuleLesson(moduleCode, lessonDay, lessonTime, remark);
    }



}
