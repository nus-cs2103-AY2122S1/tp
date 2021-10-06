package seedu.address.model.game;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Game in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidGameName(String)}
 */
public class Game {

    // TODO: update game names to accept non-alphanumeric values
    //  (currently set this way since currently its skeleton code)
    public static final String MESSAGE_CONSTRAINTS = "Names should only contain alphanumeric characters and spaces, "
            + "and it should not be blank";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String gameName;

    /**
     * Constructs a {@code Game}.
     *
     * @param tagName A valid game name.
     */
    public Game (String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidGameName(tagName), MESSAGE_CONSTRAINTS);
        this.gameName = tagName;
    }

    /**
     * Returns true if a given string is a valid game name.
     */
    public static boolean isValidGameName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Game // instanceof handles nulls
                && gameName.equals(((Game) other).gameName)); // state check
    }

    @Override
    public int hashCode() {
        return gameName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + gameName + ']';
    }

}
