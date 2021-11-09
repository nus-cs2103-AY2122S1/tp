package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's name in TAB.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class ModuleName {

    public static final String MESSAGE_CONSTRAINTS =
            "Module Names should contain either 2 to 4 upper case characters, followed by 4 numbers, followed by "
            + "0 to 4 upper case characters\n"
            + "Examples: ST2334, CS1010S, ASP1201CH";

    /*
     * The first character of the module name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[A-Z]{2,4}[0-9]{4}[A-Z]{0,4}";

    private final String moduleName;

    /**
     * Constructs a {@code ModuleName}.
     *
     * @param name A valid module name.
     */
    public ModuleName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        moduleName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getModuleName() {
        return this.moduleName;
    }


    @Override
    public String toString() {
        return moduleName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleName // instanceof handles nulls
                && moduleName.equals(((ModuleName) other).moduleName)); // state check
    }

    @Override
    public int hashCode() {
        return moduleName.hashCode();
    }

}
