package seedu.address.model.game;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Game's unique identifier in the gitGud game's list.
 * Guarantees: immutable; is valid as declared in {@link #isValidGameId(String)}
 */
public class GameId {

    public static final String MESSAGE_DUPLICATE_GAME_ID =
            "Game IDs should be unique for each game in the game's list.";
    public static final String MESSAGE_INVALID_CHARACTERS = "GAME_ID provided must be a single alphanumeric "
            + "word, with a maximum length of 20 characters.";

    /**
     * Non-empty, alpha-numeric, one-word string with a max limit of 20 characters.
     */
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9]{1,20}$";

    public final String value;

    /**
     * Constructs a {@code GameId}.
     *
     * @param gameId A valid gameId.
     */
    public GameId(String gameId) {
        requireNonNull(gameId);
        checkArgument(isValidGameId(gameId), MESSAGE_INVALID_CHARACTERS);
        value = gameId;
    }

    /**
     * Returns true if a given string is a valid gameId.
     */
    public static boolean isValidGameId(String gameId) {
        return gameId.matches(VALIDATION_REGEX);
    }

    @Override
    public int hashCode() {
        return value.toLowerCase().hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GameId // instanceof handles nulls
                && value.equalsIgnoreCase(((GameId) other).value)); // state check
    }

    @Override
    public String toString() {
        return value;
    }
}
