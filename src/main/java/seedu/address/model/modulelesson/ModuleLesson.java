package seedu.address.model.modulelesson;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Remark;

/**
 * Represents a ModuleLesson in contHACKS.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ModuleLesson {

    // Identity fields
    private final ModuleCode moduleCode;
    private final LessonDay lessonDay;
    private final LessonTime lessonStartTime;
    private final LessonTime lessonEndTime;
    private final Remark remark;


    /**
     * Every field must be present and not null.
     */
    public ModuleLesson(ModuleCode moduleCode, LessonDay lessonDay, LessonTime lessonStartTime,
                        LessonTime lessonEndTime, Remark remark) {
        requireAllNonNull(moduleCode, lessonDay, lessonStartTime, lessonEndTime, remark);
        assert lessonStartTime.value.isBefore(lessonEndTime.value) : "Start time should be before end time";
        assert moduleCode.getLessonCodes().size() == 1
                : "Each ModuleCode should contain exactly 1 LessonCode";
        this.moduleCode = moduleCode;
        this.lessonDay = lessonDay;
        this.lessonStartTime = lessonStartTime;
        this.lessonEndTime = lessonEndTime;
        this.remark = remark;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    public LessonDay getDay() {
        return lessonDay;
    }

    public LessonTime getLessonStartTime() {
        return lessonStartTime;
    }

    public LessonTime getLessonEndTime() {
        return lessonEndTime;
    }

    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns true if both lessons have the same module code, day and lessonTime.
     * This defines a weaker notion of equality between two lessons.
     */
    public boolean isSameModuleLesson(ModuleLesson otherModuleLesson) {
        if (otherModuleLesson == this) {
            return true;
        }

        return otherModuleLesson != null
                && otherModuleLesson.getModuleCode().equals(getModuleCode())
                && otherModuleLesson.getModuleCode().getLessonCodes().equals(getModuleCode().getLessonCodes());
    }

    /**
     * Returns true if the {@code otherModuleLesson} clashes with this moduleLesson.
     */
    public boolean clashesWith(ModuleLesson otherModuleLesson) {
        if (getDay().equals(otherModuleLesson.getDay())) {
            return getLessonStartTime().value.isBefore(otherModuleLesson.getLessonEndTime().value)
                    && otherModuleLesson.getLessonStartTime().value.isBefore(getLessonEndTime().value);
        }
        return false;
    }

    /**
     * Returns true if both lessons have the same identity and data fields.
     * This defines a stronger notion of equality between two moduleLessons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ModuleLesson)) {
            return false;
        }

        ModuleLesson otherModuleLesson = (ModuleLesson) other;
        return ((ModuleLesson) other).getModuleCode().equals(getModuleCode())
                && otherModuleLesson.getDay().equals(getDay())
                && otherModuleLesson.getLessonStartTime().equals(getLessonStartTime())
                && otherModuleLesson.getLessonEndTime().equals(getLessonEndTime())
                && otherModuleLesson.getRemark().equals(getRemark());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(moduleCode, lessonDay, lessonStartTime, lessonEndTime, remark);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Module: ")
                .append(getModuleCode().toString())
                .append("; Day: ")
                .append(getDay().toString())
                .append("; Start time: ")
                .append(getLessonStartTime().toString())
                .append("; End time: ")
                .append(getLessonEndTime().toString());
        if (!remark.toString().trim().isEmpty()) {
            builder.append("; Remark: ");
            builder.append(getRemark());
        }
        return builder.toString();
    }

}
