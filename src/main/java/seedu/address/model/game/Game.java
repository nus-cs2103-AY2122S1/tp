package seedu.address.model.game;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents a Game in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Game {

    // Identity field
    // used to uniquely identify each Game.
    public final GameId gameId;

    /**
     * Constructs a {@code Game}.
     *
     * @param gameId A valid game name.
     */
    public Game(GameId gameId) {
        requireNonNull(gameId);
        this.gameId = gameId;
    }

    public GameId getGameId() {
        return gameId;
    }

    /**
     * Returns true if both games have same gameId.
     * @return boolean result of equals.
     */
    public boolean isSameGameId(Game game) {
        return this.gameId.equals(game.getGameId());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Game)) {
            return false;
        }

        Game otherGame = (Game) other;
        return otherGame.getGameId().equals(getGameId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId);
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Game ID: ")
                .append(getGameId());

        return builder.toString();
    }

}
