package seedu.address.model.modulelesson;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Remark;

/**
 * Represents a ModuleLesson in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ModuleLesson {

    // Identity fields
    private final Set<ModuleCode> moduleCodes = new HashSet<>();
    private final LessonDay lessonDay;
    private final LessonTime lessonTime;
    private final Remark remark;


    /**
     * Every field must be present and not null.
     */
    public ModuleLesson(Set<ModuleCode> moduleCode, LessonDay lessonDay, LessonTime lessonTime, Remark remark) {
        requireAllNonNull(moduleCode, lessonDay, lessonTime, remark);
        assert(moduleCode.size() == 1) : "Lesson should only contain 1 module code!";
        this.moduleCodes.addAll(moduleCode);
        this.lessonDay = lessonDay;
        this.lessonTime = lessonTime;
        this.remark = remark;
    }

    /**
     * Returns an immutable module codes set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<ModuleCode> getModuleCodes() {
        return Collections.unmodifiableSet(moduleCodes);
    }

    public LessonDay getDay() {
        return lessonDay;
    }

    public LessonTime getTime() {
        return lessonTime;
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
                && otherModuleLesson.getModuleCodes().equals(getModuleCodes())
                && otherModuleLesson.getDay().equals(getDay())
                && otherModuleLesson.getTime().equals(getTime());
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
        return ((ModuleLesson) other).getModuleCodes().equals(getModuleCodes())
                && otherModuleLesson.getDay().equals(getDay())
                && otherModuleLesson.getTime().equals(getTime())
                && otherModuleLesson.getRemark().equals(getRemark());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(moduleCodes, lessonDay, lessonTime, remark);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Module: ");
        Set<ModuleCode> moduleCodes = getModuleCodes();
        moduleCodes.forEach(builder::append);
        builder.append("; Day: ")
                .append(getDay().toString())
                .append("; Time: ")
                .append(getTime().toString());
        if (!remark.toString().trim().isEmpty()) {
            builder.append("; Remark: ");
            builder.append(getRemark());
        }
        return builder.toString();
    }

}
