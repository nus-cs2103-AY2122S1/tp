package seedu.unify.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.unify.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the UniFy app.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagTaskName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String tagTaskName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagTaskName A valid tag task name.
     */
    public Tag(String tagTaskName) {
        requireNonNull(tagTaskName);
        checkArgument(isValidTagTaskName(tagTaskName), MESSAGE_CONSTRAINTS);
        this.tagTaskName = tagTaskName;
    }

    /**
     * Returns true if a given string is a valid tag task name.
     */
    public static boolean isValidTagTaskName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && tagTaskName.equals(((Tag) other).tagTaskName)); // state check
    }

    @Override
    public int hashCode() {
        return tagTaskName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return tagTaskName;
    }

}
