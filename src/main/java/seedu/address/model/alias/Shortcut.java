package seedu.address.model.alias;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.Serializable;

/**
 * Represents a Shortcut for an alias.
 * Guarantees: is valid as declared in {@link #isValidShortcut(String)}
 */
public class Shortcut implements Serializable {
    public static final String MESSAGE_CONSTRAINTS =
            "The shortcut should not be an existing command, and it should be one word";

    /*
     * The first character of the shortcut must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String shortcut;

    /**
     * Constructs a {@code Shortcut}.
     */
    public Shortcut(String shortcut) {
        requireNonNull(shortcut);
        checkArgument(isValidShortcut(shortcut), MESSAGE_CONSTRAINTS);
        this.shortcut = shortcut;
    }

    /**
     * Returns true if given string is a valid shortcut.
     */
    public static boolean isValidShortcut(String test) {
        return test.matches(VALIDATION_REGEX)
                && test.split("\\s+").length == 1 && !(CommandWord.isValidCommandWord(test));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Shortcut // instanceof handles nulls
                && shortcut.equals(((Shortcut) other).shortcut)); // state check
    }

    @Override
    public String toString() {
        return shortcut;
    }

    @Override
    public int hashCode() {
        return shortcut.hashCode();
    }
}
