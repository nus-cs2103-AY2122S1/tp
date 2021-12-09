package seedu.times.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.times.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public static final int MAX_TAG_LENGTH = 15;
    public static final int MAX_TAG_NUMBER = 5;
    public static final String MESSAGE_CONSTRAINTS_TOO_LONG =
            "Tags names should be at most " + MAX_TAG_LENGTH + " characters";
    public static final String MESSAGE_CONSTRAINTS_TOO_MANY =
            "A student should have at most " + MAX_TAG_NUMBER + " tags";

    public final String tagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
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
     * Returns true if {@code tagName} of this {@code Tag} matches exactly with {@code keyword} ignoring case.
     */
    public boolean isNameMatchingIgnoreCase(String keyword) {
        return tagName.toLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && tagName.equals(((Tag) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName + ']';
    }

}
