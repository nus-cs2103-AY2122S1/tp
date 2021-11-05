package seedu.tuitione.model.remark;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.commons.util.AppUtil.checkArgument;

/**
 * Represents a Remark in the tuitione book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidRemarkName(String)}
 */
public class Remark {

    public static final int MAX_REMARK_CHARACTER = 25;
    public static final String MESSAGE_CONSTRAINTS = "Remarks should be alphanumeric, single worded without "
            + "spacings in between them, should contain fewer than " + MAX_REMARK_CHARACTER + " characters and "
            + "should not be empty.";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String remarkName;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remarkName A valid remark name.
     */
    public Remark(String remarkName) {
        requireNonNull(remarkName);
        checkArgument(isValidRemarkName(remarkName), MESSAGE_CONSTRAINTS);
        this.remarkName = remarkName;
    }

    /**
     * Returns true if a given string is a valid remark name.
     */
    public static boolean isValidRemarkName(String test) {
        return test.matches(VALIDATION_REGEX) && (test.length() <= MAX_REMARK_CHARACTER) && !test.isBlank();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && remarkName.equals(((Remark) other).remarkName)); // state check
    }

    @Override
    public int hashCode() {
        return remarkName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + remarkName + ']';
    }

}
