package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final int MAX_TAG_LENGTH = 60;
    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String MESSAGE_TAG_LENGTH_EXCEEDED = "Tag length should be not more than "
            + MAX_TAG_LENGTH + " characters";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

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
        return test.matches(VALIDATION_REGEX) && Tag.isWithinCharLimit(test);
    }

    /**
     * Returns true if a given string is within the character limit.
     */
    public static boolean isWithinCharLimit(String trimmedTag) {
        return trimmedTag.length() <= MAX_TAG_LENGTH;
    }

    /**
     * Compares this tag with other tag with case sensitivity and case insensitivity
     *
     * @param otherTag other tag to be compared to this tag
     * @param isCaseSensitive if case sensitivity is wanted
     * @return boolean that indicates if this and other tag are equal
     */
    public boolean compareTag(Tag otherTag, boolean isCaseSensitive) {
        if (isCaseSensitive) {
            return this.equals(otherTag);
        } else {
            return this.tagName.equalsIgnoreCase(otherTag.tagName);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof Tag) {
            Tag otherTag = (Tag) other;
            return tagName.equals(otherTag.tagName);
        } else {
            return false;
        }
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
