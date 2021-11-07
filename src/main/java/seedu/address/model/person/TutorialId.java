package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Tutorial ID in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTutorialId(String)}
 */
public class TutorialId {

    public static final String MESSAGE_CONSTRAINTS = "Tutorial ID must be valid ie a 2 digits, and it should not be"
            + " blank";

    /*
     * Regex for the Tutorial ID.
     */
    public static final String VALIDATION_REGEX = "[0-9]{2}";

    public final String value;

    /**
     * Constructs an {@code TutorialId}.
     *
     * @param tutorialId A valid Tutorial ID.
     */
    public TutorialId(String tutorialId) {
        requireNonNull(tutorialId);
        checkArgument(isValidTutorialId(tutorialId), MESSAGE_CONSTRAINTS);
        value = tutorialId;
    }

    /**
     * Returns true if a given string is a valid Tutorial ID.
     */
    public static boolean isValidTutorialId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Compares this value with another TutorialId using Java's compareTo function
     */
    public int compareTo(TutorialId t) {
        return value.compareTo(t.value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TutorialId)) {
            return false;
        }

        TutorialId otherTutorialId = (TutorialId) other;

        return value.equals(otherTutorialId.value); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
