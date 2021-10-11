package seedu.address.testutil;

import seedu.address.model.moduleclass.Day;
import seedu.address.model.moduleclass.ModuleClass;
import seedu.address.model.moduleclass.Time;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Remark;

public class ModuleClassBuilder {

    public static final String DEFAULT_MODULE_CODE = "CS2103";
    public static final String DEFAULT_DAY = "2";
    public static final String DEFAULT_TIME = "15:00";
    public static final String DEFAULT_REMARK = "Location: COM1 113";

    private ModuleCode moduleCode;
    private Day day;
    private Time time;
    private Remark remark;

    /**
     * Creates a {@code ModuleClassBuilder} with the default details.
     */
    public ModuleClassBuilder() {
        moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
        day = new Day(DEFAULT_DAY);
        time = new Time(DEFAULT_TIME);
        remark = new Remark(DEFAULT_REMARK);
    }

    /**
     * Initialises the ModuleClass with the data of {@code classToCopy}.
     */
    public ModuleClassBuilder(ModuleClass classToCopy) {
        moduleCode = classToCopy.getModuleCode();
        day = classToCopy.getDay();
        time = classToCopy.getTime();
        remark = classToCopy.getRemark();
    }

    /**
     * Sets the {@code moduleCode} of the {@code ModuleClass} that we are building.
     */
    public ModuleClassBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }


    /**
     * Sets the {@code day} of the {@code ModuleClass} that we are building.
     */
    public ModuleClassBuilder withDay(String day) {
        this.day = new Day(day);
        return this;
    }

    /**
     * Sets the {@code time} of the {@code ModuleClass} that we are building.
     */
    public ModuleClassBuilder withTime(String time) {
        this.time = new Time(time);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code ModuleClass} that we are building.
     */
    public ModuleClassBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    public ModuleClass build() {
        return new ModuleClass(moduleCode, day, time, remark);
    }



}
