package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's remark in contHACKS.
 * Guarantees: immutable; is always valid
 */
public class Remark {

    public static final String MESSAGE_CONSTRAINTS =
            "Remarks should only contain ASCII characters, and it should be at most 52 characters long.";

    /**
     * The first character of the remark must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid remark.
     */
    public static final String VALIDATION_REGEX = "[\\p{ASCII}&&[^ ]][\\p{ASCII}]{0,51}";

    public final String value;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remark Remark to be added.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    /**
     * Returns true if a given string is a valid remark.
     */
    public static boolean isValidRemark(String test) {
        return test.equals("") || (test.matches(VALIDATION_REGEX) && test.length() <= 52 && !test.trim().isEmpty());
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
