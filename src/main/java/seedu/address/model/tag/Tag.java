package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric.";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String tagName;
    private int numDuplicates = 0;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName.toUpperCase();
    }

    /**
     * Constructs a {@code Tag} with specified number of students labelled under this tag.
     *
     * @param tagName A valid tag name.
     * @param numDuplicates Number of students labelled under this tag.
     */
    public Tag(String tagName, int numDuplicates) {
        this(tagName);
        this.numDuplicates = numDuplicates;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public void incrementNumDuplicates() {
//        return new Tag(tagName, numDuplicates + 1);
        numDuplicates++;
    }

    public String getNumDuplicatesString() {
        return Integer.toString(numDuplicates);
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
