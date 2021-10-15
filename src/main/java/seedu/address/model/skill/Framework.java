package seedu.address.model.skill;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Framework in the skill data field.
 * Guarantees: immutable; name is valid as declared in {@link #isValidFrameworkName(String)}
 */
public class Framework {

    public static final String MESSAGE_CONSTRAINTS = "Frameworks should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String frameworkName;

    /**
     * Constructs a {@code Framework}.
     *
     * @param frameworkName A valid framework name.
     */
    public Framework(String frameworkName) {
        requireNonNull(frameworkName);
        checkArgument(isValidFrameworkName(frameworkName), MESSAGE_CONSTRAINTS);
        this.frameworkName = frameworkName;
    }

    /**
     * Returns true if a given string is a valid framework name.
     */
    public static boolean isValidFrameworkName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Framework // instanceof handles nulls
                && frameworkName.equals(((Framework) other).frameworkName)); // state check
    }

    @Override
    public int hashCode() {
        return frameworkName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + frameworkName + ']';
    }
}
