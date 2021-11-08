package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the ezFoodie.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}.
 */
public class Tag {

    /**
     * Stands for tag message constraints.
     */
    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";

    /**
     * Stands for tag validation regex.
     */
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String tagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName a valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Overrides the equals method.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && tagName.equals(((Tag) other).tagName)); // state check
    }

    /**
     * Overrides the hashCode method.
     */
    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Overrides the toString method and format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName + ']';
    }

}
