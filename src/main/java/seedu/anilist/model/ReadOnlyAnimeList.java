package seedu.anilist.model;

import javafx.collections.ObservableList;
import seedu.anilist.model.anime.Anime;

/**
 * Unmodifiable view of an anime list
 */
public interface ReadOnlyAnimeList {

    /**
     * Returns an unmodifiable view of the Anime list.
     * This list will not contain any duplicate Anime.
     */
    ObservableList<Anime> getAnimeList();

}
