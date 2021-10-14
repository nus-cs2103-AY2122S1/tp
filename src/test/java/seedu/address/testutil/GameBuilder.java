package seedu.address.testutil;

import seedu.address.model.game.Game;
import seedu.address.model.game.GameId;

/**
 * A utility class to help with building Game objects.
 */
public class GameBuilder {

    public static final String DEFAULT_GAME_ID = "Minecraft";

    private GameId gameId;

    /**
     * Creates a {@code GameBuilder} with the default details.
     */
    public GameBuilder() {
        gameId = new GameId(DEFAULT_GAME_ID);
    }

    /**
     * Initializes the {@code GameBuilder} with the data of {@code gameToCopy}.
     */
    public GameBuilder(Game gameToCopy) {
        gameId = gameToCopy.getGameId();
    }

    /**
     * Sets the {@code GameId} of the {@code Game} that we are building.
     */
    public GameBuilder withGameId(String gameId) {
        this.gameId = new GameId(gameId);
        return this;
    }

    public Game build() {
        return new Game(gameId);
    }

}
