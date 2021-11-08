package seedu.edrecord.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's info in edrecord.
 * Guarantees: immutable; is valid as declared in {@link #isValidInfo(String)}
 */
public class Info {
    public static final String MESSAGE_CONSTRAINTS = "Info can take any non-null values, and it should not be blank";

    public final String value;

    /**
     * Constructs an {@code Info}.
     *
     * @param info An info. Info can be left blank.
     */
    public Info(String info) {
        requireNonNull(info);
        checkArgument(isValidInfo(info), MESSAGE_CONSTRAINTS);
        value = info;
    }

    /**
     * Returns true if a given string is a valid email.
     * An info can be empty string, but cannot only be whitespace or null.
     */
    public static boolean isValidInfo(String test) {
        if (test != null && test.isBlank()) {
            return test.isEmpty();
        }
        return test != null;
    }

    public boolean isEmpty() {
        return this.value.isBlank();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Info // instanceof handles nulls
                && value.equals(((Info) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
