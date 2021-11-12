package seedu.notor.model.tag;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;
import static seedu.notor.commons.util.AppUtil.checkArgument;

import seedu.notor.model.person.Person;
import seedu.notor.model.util.Unique;
import seedu.notor.model.util.UniqueList;

/**
 * Represents a Tag in Notor. Tags have unique names & can be used to filter Notor.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag implements Unique<Tag> {

    public static final String MESSAGE_CONSTRAINTS = "Tags' names should be alphanumeric and can have spaces";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String tagName;
    // TODO: populate tagged with all the people who are tagged, w/ appropriate accessors
    // Not implemented due to time constraints

    private final UniqueList<Person> tagged;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
        this.tagged = new UniqueList<>();
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override public boolean isSame(Tag other) {
        return other == this // short circuit if same object
                || nonNull(other)
                && tagName.equals(other.tagName); // state check
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
