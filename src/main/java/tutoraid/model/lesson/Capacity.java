package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Lesson's capacity in TutorAid.
 * Guarantees: immutable; is valid as declared in {@link #isValidCapacity(String)}
 */
public class Capacity {

    public static final String MESSAGE_CONSTRAINTS =
            "Capacity should only contain numbers, and it should be at least 1 digit long";
    public static final String VALIDATION_REGEX = "\\d+";
    public final String value;

    /**
     * Constructs a {@code Capacity}.
     *
     * @param capacity A valid capacity.
     */
    public Capacity(String capacity) {
        requireNonNull(capacity);
        checkArgument(isValidCapacity(capacity), MESSAGE_CONSTRAINTS);
        value = capacity;
    }

    /**
     * Returns true if a given string is a valid capacity.
     */
    public static boolean isValidCapacity(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Capacity // instanceof handles nulls
                && value.equals(((Capacity) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
