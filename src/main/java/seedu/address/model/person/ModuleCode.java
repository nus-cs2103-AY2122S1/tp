package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a module code that a Person is taking in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidModuleCode(String)}
 */
public class ModuleCode {

    public static final String MESSAGE_CONSTRAINTS =
            "Module codes should consists of a two- or three-letter prefix followed by a 4-digit number"
                    + " and optionally a one-letter suffix";

    public static final String VALIDATION_REGEX = "[a-zA-Z]{2,3}[\\d]{4}[a-zA-Z]*";
    public final String value;

    /**
     * Constructs a {@code ModuleCode}.
     *
     * @param moduleCode A valid module code.
     */
    public ModuleCode(String moduleCode) {
        requireNonNull(moduleCode);
        checkArgument(isValidModuleCode(moduleCode), MESSAGE_CONSTRAINTS);
        value = moduleCode;
    }

    /**
     * Returns true if a given string is a valid module code.
     *
     * @param test The given string.
     * @return True if the given string is a valid module code, false otherwise.
     */
    public static boolean isValidModuleCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ModuleCode
                && value.equals(((ModuleCode) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public int compareTo(ModuleCode c) {
        return this.value.toLowerCase().compareTo(c.value.toLowerCase());
    }
}
