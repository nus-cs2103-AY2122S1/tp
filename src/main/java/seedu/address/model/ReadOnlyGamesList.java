package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.game.Game;

/**
 * Unmodifiable view of a games-book
 */
public interface ReadOnlyGamesList {

    /**
     * Returns an unmodifiable view of the games list.
     * This list will not contain any duplicate games.
     */
    ObservableList<Game> getGamesList();

}
