package seedu.times.model.tuitionclass;

import static java.util.Objects.requireNonNull;
import static seedu.times.commons.util.AppUtil.checkArgument;
import static seedu.times.commons.util.AppUtil.isWithinLength;
import static seedu.times.model.util.Commons.MAX_CHAR_COUNT;

/**
 * Represents the name given to a {@code TuitionClass} by a user
 * Guarantees: immutable; is valid as declared in {@link #isValidClassName(String)}
 */
public class ClassName {
    public static final String MESSAGE_CONSTRAINTS =
            "Class name contains characters that is not alphanumeric/space, or the name is not between 1 and "
            + MAX_CHAR_COUNT + " characters long!";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String className;

    /**
     * Constructs a {@code ClassName}.
     *
     * @param className A valid class name.
     */
    public ClassName(String className) {
        requireNonNull(className);
        checkArgument(isValidClassName(className), MESSAGE_CONSTRAINTS);
        this.className = className;
    }

    /**
     * Returns true if a given string is a valid class name.
     */
    public static boolean isValidClassName(String test) {
        return test.matches(VALIDATION_REGEX) && isWithinLength(test, MAX_CHAR_COUNT);
    }

    @Override
    public String toString() {
        return className;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassName // instanceof handles nulls
                && className.equals(((ClassName) other).className)); // state check
    }
}
