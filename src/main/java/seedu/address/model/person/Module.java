package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's module in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidModule(String)}
 */
public class Module {

    public static final String MESSAGE_CONSTRAINTS = "Modules must be in the form"
            + " XX(X)1111(X), where X is any letter, 1 is any number and values in brackets"
            + " are optional, and it should not be blank";

    /*
     * The first character of the module must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input. Otherwise, detect
     * if the module code matches a 2/3 letter - 4 number - optional one letter pattern.
     *
     */
    public static final String VALIDATION_REGEX = "\\b([A-Z]{2,3})([0-9]{4})([A-Z]?)\\b";

    public final String moduleCode;

    /**
     * Constructs an {@code module}.
     *
     * @param module A valid module.
     */
    public Module(String module) {
        requireNonNull(module);
        checkArgument(isValidModule(module), MESSAGE_CONSTRAINTS);
        moduleCode = module;
    }

    /**
     * Returns true if a given string is a valid module.
     */
    public static boolean isValidModule(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return moduleCode;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Module // instanceof handles nulls
                && moduleCode.equals(((Module) other).moduleCode)); // state check
    }

    @Override
    public int hashCode() {
        return moduleCode.hashCode();
    }

}
