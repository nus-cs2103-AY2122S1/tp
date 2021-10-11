package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.moduleclass.Time;
import seedu.address.model.moduleclass.Day;
import seedu.address.model.moduleclass.ModuleClass;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Remark;


/**
 * Jackson-friendly version of {@link ModuleClass}.
 */
public class JsonAdaptedModuleClass {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module Class's %s field is missing!";

    private final String moduleCode;
    private final String day;
    private final String time;
    private final String remark;

    /**
     * Constructs a {@code JsonAdaptedModuleClass} with the given class details.
     *
     */
    @JsonCreator
    public JsonAdaptedModuleClass(@JsonProperty("moduleCode") String moduleCode,
                                  @JsonProperty("day") String day,
                                  @JsonProperty("time") String time,
                                  @JsonProperty("remark") String remark) {
        this.moduleCode = moduleCode;
        this.day = day;
        this.time = time;
        this.remark = remark;
    }

    /**
     * Converts a given {@code ModuleClass} into this class for Jackson use.
     */
    public JsonAdaptedModuleClass(ModuleClass source) {
        moduleCode = source.getModuleCode().value;
        day = source.getDay().getDayAsIntString();
        time = source.getTime().toString();
        remark = source.getRemark().value;
    }

    /**
     * Converts this Jackson-friendly adapted class object into the model's {@code ModuleClass} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module class.
     */
    public ModuleClass toModelType() throws IllegalValueException {
        if (moduleCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Module.class.getSimpleName()));
        }
        final ModuleCode classModuleCode = new ModuleCode(moduleCode);

        if (day == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Day.class.getSimpleName()));
        }
        final Day classDay = new Day(day);

        if (time == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName()));
        }
        final Time classTime = new Time(time);

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        final Remark classRemark = new Remark(remark);

        return new ModuleClass(classModuleCode, classDay, classTime, classRemark);
    }

}
