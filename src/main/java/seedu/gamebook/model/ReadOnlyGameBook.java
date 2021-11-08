package seedu.gamebook.model;

import javafx.collections.ObservableList;
import seedu.gamebook.model.gameentry.GameEntry;

/**
 * Unmodifiable view of an gamebook book
 */
public interface ReadOnlyGameBook {

    /**
     * Returns an unmodifiable view of the game entries list.
     */
    ObservableList<GameEntry> getGameEntryList();

}
