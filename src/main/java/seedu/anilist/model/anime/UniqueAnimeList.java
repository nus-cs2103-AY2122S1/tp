package seedu.anilist.model.anime;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.anilist.model.anime.exceptions.AnimeNotFoundException;
import seedu.anilist.model.anime.exceptions.DuplicateAnimeException;

/**
 * A list of animes that enforces uniqueness between its elements and does not allow nulls.
 * An anime is considered unique by comparing using {@code Anime#isSameAnime()}. As such, adding and updating of
 * Anime uses Anime#isSameAnime(Anime) for equality so as to ensure that the Anime being added or updated is
 * unique in terms of identity in the UniqueAnimeList. However, the removal of a Anime uses Anime#equals(Object) so
 * as to ensure that the Anime with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Anime#isSameAnime(Anime)
 */
public class UniqueAnimeList implements Iterable<Anime> {

    private final ObservableList<Anime> internalList = FXCollections.observableArrayList();
    private final ObservableList<Anime> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Anime as the given argument.
     */
    public boolean contains(Anime toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameAnime);
    }

    /**
     * Adds a Anime to the list.
     * The Anime must not already exist in the list.
     */
    public void add(Anime toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAnimeException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the Anime {@code target} in the list with {@code editedAnime}.
     * {@code target} must exist in the list.
     * The Anime identity of {@code editedAnime} must not be the same as another existing Anime in the list.
     */
    public void setAnime(Anime target, Anime editedAnime) {
        requireAllNonNull(target, editedAnime);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new AnimeNotFoundException();
        }

        if (!target.isSameAnime(editedAnime) && contains(editedAnime)) {
            throw new DuplicateAnimeException();
        }

        internalList.set(index, editedAnime);
    }

    /**
     * Removes the equivalent Anime from the list.
     * The Anime must exist in the list.
     */
    public void remove(Anime toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AnimeNotFoundException();
        }
    }

    public void setMultipleAnime(UniqueAnimeList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code Anime}.
     * {@code Anime} must not contain duplicate Anime.
     */
    public void setMultipleAnime(List<Anime> anime) {
        requireAllNonNull(anime);
        if (!animeAreUnique(anime)) {
            throw new DuplicateAnimeException();
        }

        internalList.setAll(anime);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Anime> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Anime> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueAnimeList // instanceof handles nulls
                        && internalList.equals(((UniqueAnimeList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code Anime} contains only unique Anime.
     */
    private boolean animeAreUnique(List<Anime> anime) {
        for (int i = 0; i < anime.size() - 1; i++) {
            for (int j = i + 1; j < anime.size(); j++) {
                if (anime.get(i).isSameAnime(anime.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
