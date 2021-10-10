package seedu.address.model.moduleclass;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;

import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Remark;

/**
 * Represents a ModuleClass in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ModuleClass {

    // Identity fields
    private final ModuleCode moduleCode;
    private final DayOfWeek day;
    private final LocalDateTime dateTime;
    private final Remark remark;


    /**
     * Every field must be present and not null.
     */
    public ModuleClass(ModuleCode moduleCode, DayOfWeek day, LocalDateTime dateTime, Remark remark) {
        requireAllNonNull(moduleCode, day, dateTime, remark);
        this.moduleCode = moduleCode;
        this.day = day;
        this.dateTime = dateTime;
        this.remark = remark;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameModuleClass(ModuleClass otherModuleClass) {
        if (otherModuleClass == this) {
            return true;
        }

        return otherModuleClass != null
                && otherModuleClass.getModuleCode().equals(getModuleCode());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
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
        return ((ModuleClass) other).getModuleCode().equals(getModuleCode())
                && otherModuleClass.getDay().equals(getDay())
                && otherModuleClass.getDateTime().equals(getDateTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(moduleCode, day, dateTime, remark);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getModuleCode())
                .append("; Module: ")
                .append(getModuleCode())
                .append("; Day: ")
                .append(day.getDisplayName(TextStyle.FULL, Locale.US));

        if (!remark.toString().trim().isEmpty()) {
            builder.append("; Remark: ");
            builder.append(getRemark());
        }

        return builder.toString();
    }

}
