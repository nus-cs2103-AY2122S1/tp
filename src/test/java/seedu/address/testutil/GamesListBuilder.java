package seedu.address.testutil;

import seedu.address.model.GamesList;
import seedu.address.model.game.Game;

/**
 * A utility class to help with building GamesList objects.
 * Example usage: <br>
 *     {@code GamesList fl = new GamesList().withGame(new Game(gameId).build();}
 */
public class GamesListBuilder {

    private GamesList gamesList;

    public GamesListBuilder() {
        gamesList = new GamesList();
    }

    public GamesListBuilder(GamesList gamesList) {
        this.gamesList = gamesList;
    }

    /**
     * Adds a new {@code Game} to the {@code GamesList} that we are building.
     */
    public GamesListBuilder withGame(Game game) {
        gamesList.addGame(game);
        return this;
    }

    public GamesList build() {
        return gamesList;
    }
}
