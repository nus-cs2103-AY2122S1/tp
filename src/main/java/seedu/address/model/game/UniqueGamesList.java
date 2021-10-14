package seedu.address.model.game;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.game.exceptions.DuplicateGameException;
import seedu.address.model.game.exceptions.GameNotFoundException;

/**
 * A list of games that enforces uniqueness between its elements and does not allow nulls.
 * <p>
 * Games is considered unique by comparing using {@code Game#equals(Object)}. As such, adding and updating
 * of games uses Game#equals(Object) for equality to ensure that the game being added or updated
 * is unique in terms of identity in the UniqueGamesList. The removal of a game also uses
 * Game#equals(Object) to ensure that the game with exactly the same gameId will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Game#equals(Object)
 */
public class UniqueGamesList implements Iterable<Game> {

    private final ObservableList<Game> internalList = FXCollections.observableArrayList();
    private final ObservableList<Game> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent game as the given argument.
     */
    public boolean contains(Game toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Returns true if the list contains a game with idToCheck.
     */
    public boolean containsId(GameId idToCheck) {
        requireNonNull(idToCheck);
        return internalList.stream().anyMatch(game -> game.getGameId().equals(idToCheck));
    }

    /**
     * Adds a game to the list.
     * The game must not already exist in the list.
     */
    public void add(Game gameToAdd) {
        requireNonNull(gameToAdd);
        if (containsId(gameToAdd.getGameId())) {
            throw new DuplicateGameException();
        }
        internalList.add(gameToAdd);
    }

    /**
     * Replaces the game {@code gameToEdit} in the list with {@code editedGame}.
     * {@code gameToEdit} must exist in the list.
     * The game identity of {@code editedGame} must not be the same as another existing game in the list.
     */
    public void setGame(Game gameToEdit, Game editedGame) {
        requireAllNonNull(gameToEdit, editedGame);

        int index = internalList.indexOf(gameToEdit);
        if (index == -1) {
            throw new GameNotFoundException();
        }

        // guard against if editedGame already exists in the UniqueGamesList.
        if (!gameToEdit.isSameGameId(editedGame) && containsId(editedGame.getGameId())) {
            throw new DuplicateGameException();
        }

        internalList.set(index, editedGame);
    }

    /**
     * Removes the equivalent game from the list.
     * The game must exist in the list.
     */
    public void remove(Game toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new GameNotFoundException();
        }
    }

    public void setGames(UniqueGamesList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code games}.
     * {@code games} must not contain duplicate games.
     */
    public void setGames(List<Game> games) {
        requireAllNonNull(games);
        if (!gamesAreUnique(games)) {
            throw new DuplicateGameException();
        }

        internalList.setAll(games);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Game> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Game> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueGamesList // instanceof handles nulls
                && internalList.equals(((UniqueGamesList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code games} contains only unique games.
     */
    private boolean gamesAreUnique(List<Game> games) {
        for (int i = 0; i < games.size() - 1; i++) {
            for (int j = i + 1; j < games.size(); j++) {
                if (games.get(i).equals(games.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
