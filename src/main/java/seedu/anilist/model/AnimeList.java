package seedu.anilist.model;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.anime.Status;
import seedu.anilist.model.anime.UniqueAnimeList;
import seedu.anilist.model.genre.Genre;
import seedu.anilist.model.stats.Stats;

/**
 * Wraps all data at the anime list level
 * Duplicates are not allowed (by .isSameAnime comparison)
 */
public class AnimeList implements ReadOnlyAnimeList {

    private final UniqueAnimeList animeList;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        animeList = new UniqueAnimeList();
    }

    public AnimeList() {}

    /**
     * Creates an AnimeList using the Anime in the {@code toBeCopied}
     */
    public AnimeList(ReadOnlyAnimeList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the Anime list with {@code Anime}.
     * {@code Anime} must not contain duplicate Anime.
     */
    public void setAnimeList(List<Anime> anime) {
        this.animeList.setMultipleAnime(anime);
    }

    /**
     * Resets the existing data of this {@code AnimeList} with {@code newData}.
     */
    public void resetData(ReadOnlyAnimeList newData) {
        requireNonNull(newData);

        setAnimeList(newData.getAnimeList());
    }

    //// Anime-level operations

    /**
     * Returns true if a Anime with the same identity as {@code Anime} exists in the anime list.
     */
    public boolean hasAnime(Anime anime) {
        requireNonNull(anime);
        return animeList.contains(anime);
    }

    /**
     * Adds a Anime to the anime list.
     * The Anime must not already exist in the anime list.
     */
    public void addAnime(Anime p) {
        animeList.add(p);
    }

    /**
     * Replaces the given Anime {@code target} in the list with {@code editedAnime}.
     * {@code target} must exist in the anime list.
     * The Anime identity of {@code editedAnime} must not be the same as another existing Anime in the anime list.
     */
    public void setAnime(Anime target, Anime editedAnime) {
        requireNonNull(editedAnime);

        animeList.setAnime(target, editedAnime);
    }

    /**
     * Removes {@code key} from this {@code AnimeList}.
     * {@code key} must exist in the anime list.
     */
    public void removeAnime(Anime key) {
        animeList.remove(key);
    }

    /**
     * Fetches the user stats from the anime list
     * @return the user stats
     */
    public Stats fetchUserStats() {
        int watchingCount = 0;
        int toWatchCount = 0;
        int finishedCount = 0;
        HashMap<Genre, Integer> userGenres = new HashMap<>();

        for (Anime anime: animeList) {
            for (Genre genre: anime.getGenres()) {
                userGenres.merge(genre, 1, Integer::sum);
            }
            Status.WatchStatus watchStatus = anime.getStatus().status;
            if (watchStatus.equals(Status.WatchStatus.WATCHING)) {
                watchingCount += 1;
            } else if (watchStatus.equals(Status.WatchStatus.TOWATCH)) {
                toWatchCount += 1;
            } else {
                assert watchStatus.equals(Status.WatchStatus.FINISHED);
                finishedCount += 1;
            }
        }
        return new Stats(watchingCount, toWatchCount, finishedCount, userGenres);
    }


    //// util methods

    @Override
    public String toString() {
        return animeList.asUnmodifiableObservableList().size() + " anime";
        // TODO: refine later
    }

    @Override
    public ObservableList<Anime> getAnimeList() {
        return animeList.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AnimeList // instanceof handles nulls
                && animeList.equals(((AnimeList) other).animeList));
    }

    @Override
    public int hashCode() {
        return animeList.hashCode();
    }
}
