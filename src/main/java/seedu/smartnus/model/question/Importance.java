package seedu.smartnus.model.question;

import static java.util.Objects.requireNonNull;
import static seedu.smartnus.commons.util.AppUtil.checkArgument;

/**
 * Represents a Question's importance in SmartNus.
 * Guarantees: immutable; is valid as declared in {@link #isValidImportance(String)}
 */
public class Importance implements Comparable<Importance> {

    public static final String MESSAGE_CONSTRAINTS =
            "Importance should only be an integer from 1-3 inclusive";
    public static final String VALIDATION_REGEX = "[1-3]";
    public final String value;

    /**
     * Constructs a {@code Importance}.
     *
     * @param importance A valid importance number.
     */
    public Importance(String importance) {
        requireNonNull(importance);
        checkArgument(isValidImportance(importance), MESSAGE_CONSTRAINTS);
        value = importance;
    }

    /**
     * Returns true if a given string is a valid importance number.
     */
    public static boolean isValidImportance(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Importance // instanceof handles nulls
                && value.equals(((Importance) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Importance o) {
        int importance = Integer.parseInt(value);
        int otherImportance = Integer.parseInt(o.value);
        return Integer.compare(importance, otherImportance);
    }
}
