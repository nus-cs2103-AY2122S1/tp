package seedu.tracker.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.tracker.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's code in the mod tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidCode(String)}
 */
public class Code {
    public static final String MESSAGE_CONSTRAINTS =
            "Module codes should start with 2-3 capital letters,\nfollowed by 4 numerical digits,\n"
                    + "and end with 1-2 optional capital letters.\nIt also should not be blank";

    public static final String VALIDATION_REGEX = "[A-Z]{2,4}[0-9]{4}[A-Z]{0,2}?";

    public final String value;

    /**
     * Constructs a {@code Code}.
     *
     * @param code A valid module code.
     */
    public Code(String code) {
        requireNonNull(code);
        checkArgument(isValidCode(code), MESSAGE_CONSTRAINTS);
        value = code;
    }

    /**
     * Returns true if a given string is a valid code.
     */
    public static boolean isValidCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Code // instanceof handles nulls
                && value.equalsIgnoreCase(((Code) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
