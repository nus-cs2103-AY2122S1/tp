package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.anime.Anime;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAnimeList {

    /**
     * Returns an unmodifiable view of the Anime list.
     * This list will not contain any duplicate Anime.
     */
    ObservableList<Anime> getAnimeList();

}
