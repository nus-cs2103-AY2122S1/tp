package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's Module id in TAB.
 * Guarantees: immutable; is valid as declared in {@link #isValidModuleId(String)}
 */
public class ModuleId {

    public static final String MESSAGE_CONSTRAINTS = "Must be a valid Module Id";

    /*
     *  ModuleId must start and end with a capitalised letter, with 7 digits in between
     *
     */
    public static final String VALIDATION_REGEX = "[A-Z]\\d{7}[A-Z]";

    public final String value;

    /**
     * Constructs an {@code ModuleId}.
     *
     * @param moduleId A valid ModuleId.
     */
    public ModuleId(String moduleId) {
        requireNonNull(moduleId);
        checkArgument(isValidModuleId(moduleId), MESSAGE_CONSTRAINTS);
        value = moduleId;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidModuleId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleId // instanceof handles nulls
                && value.equals(((ModuleId) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
