package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.game.Game;
import seedu.address.model.game.GameId;
import seedu.address.model.game.UniqueGamesList;

/**
 * Wraps all data at the games-book level
 * Duplicates are not allowed (by .isSameGame comparison)
 */
public class GamesList implements ReadOnlyGamesList {

    private final UniqueGamesList games;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        games = new UniqueGamesList();
    }

    public GamesList() {
    }

    /**
     * Creates an GamesBook using the Games in the {@code toBeCopied}
     */
    public GamesList(ReadOnlyGamesList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the games list with {@code games}.
     * {@code games} must not contain duplicate games.
     */
    public void setGames(List<Game> games) {
        this.games.setGames(games);
    }

    /**
     * Resets the existing data of this {@code GamesBook} with {@code newData}.
     */
    public void resetData(ReadOnlyGamesList newData) {
        requireNonNull(newData);

        setGames(newData.getGamesList());
    }

    //// game-level operations

    /**
     * Returns true if a game with the same identity as {@code game} exists in the games list.
     */
    public boolean hasGame(Game game) {
        requireNonNull(game);
        return games.contains(game);
    }

    /**
     * Returns true if a game with the same GameId as {@code gameId} exists in the games list.
     */
    public boolean hasGameWithId(GameId gameId) {
        requireNonNull(gameId);
        return games.containsId(gameId);
    }

    /**
     * Adds a game to the games list.
     * The game must not already exist in the games list.
     */
    public void addGame(Game p) {
        games.add(p);
    }

    /**
     * Gets a game from the games list.
     * The game with the gameId must already exist in the games list.
     */
    public Game getGame(GameId gameId) {
        return games.getGame(gameId);
    }

    /**
     * Replaces the given game {@code target} in the list with {@code editedGame}.
     * {@code target} must exist in the games list.
     * The game identity of {@code editedGame} must not be the same as another existing game in the games list.
     */
    public void setGame(Game target, Game editedGame) {
        requireNonNull(editedGame);

        games.setGame(target, editedGame);
    }

    /**
     * Removes {@code key} from this {@code GamesList}.
     * {@code key} must exist in the games list.
     */
    public void removeGame(Game key) {
        games.remove(key);
    }

    // util methods

    @Override
    public String toString() {
        return games.asUnmodifiableObservableList().size() + " games";
        // TODO: refine later
    }

    @Override
    public ObservableList<Game> getGamesList() {
        return games.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GamesList // instanceof handles nulls
                && games.equals(((GamesList) other).games));
    }

    @Override
    public int hashCode() {
        return games.hashCode();
    }
}
