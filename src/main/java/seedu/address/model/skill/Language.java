package seedu.address.model.skill;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Language in the skill data field.
 * Guarantees: immutable; name is valid as declared in {@link #isValidLanguageName(String)}
 */
public class Language {

    public static final String MESSAGE_CONSTRAINTS = "Languages should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String languageName;

    public Language(String languageName) {
        requireNonNull(languageName);
        checkArgument(isValidLanguageName(languageName), MESSAGE_CONSTRAINTS);
        this.languageName = languageName;
    }

    /**
     * Returns true if a given string is a valid language name.
     */
    public static boolean isValidLanguageName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Language // instanceof handles nulls
                && languageName.equals(((Language) other).languageName)); // state check
    }

    @Override
    public int hashCode() {
        return languageName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + languageName + ']';
    }
}
