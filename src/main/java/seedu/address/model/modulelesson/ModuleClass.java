package seedu.address.model.modulelesson;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Remark;

/**
 * Represents a ModuleClass in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ModuleClass {

    // Identity fields
    private final Set<ModuleCode> moduleCodes = new HashSet<>();
    private final Day day;
    private final Time time;
    private final Remark remark;


    /**
     * Every field must be present and not null.
     */
    public ModuleClass(Set<ModuleCode> moduleCode, Day day, Time time, Remark remark) {
        requireAllNonNull(moduleCode, day, time, remark);
        assert(moduleCode.size() == 1) : "Class should only contain 1 module code!";
        this.moduleCodes.addAll(moduleCode);
        this.day = day;
        this.time = time;
        this.remark = remark;
    }

    /**
     * Returns an immutable module codes set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<ModuleCode> getModuleCodes() {
        return Collections.unmodifiableSet(moduleCodes);
    }

    public Day getDay() {
        return day;
    }

    public Time getTime() {
        return time;
    }

    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns true if both classes have the same module code, day and time.
     * This defines a weaker notion of equality between two classes.
     */
    public boolean isSameModuleClass(ModuleClass otherModuleClass) {
        if (otherModuleClass == this) {
            return true;
        }

        return otherModuleClass != null
                && otherModuleClass.getModuleCodes().equals(getModuleCodes())
                && otherModuleClass.getDay().equals(getDay())
                && otherModuleClass.getTime().equals(getTime());
    }

    /**
     * Returns true if both classes have the same identity and data fields.
     * This defines a stronger notion of equality between two moduleClasses.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ModuleClass)) {
            return false;
        }

        ModuleClass otherModuleClass = (ModuleClass) other;
        return ((ModuleClass) other).getModuleCodes().equals(getModuleCodes())
                && otherModuleClass.getDay().equals(getDay())
                && otherModuleClass.getTime().equals(getTime())
                && otherModuleClass.getRemark().equals(getRemark());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(moduleCodes, day, time, remark);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Module: ")
                .append(getModuleCodes())
                .append("; Day: ")
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
