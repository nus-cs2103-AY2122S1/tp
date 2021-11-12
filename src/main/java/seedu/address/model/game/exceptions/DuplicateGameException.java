package seedu.address.model.game.exceptions;

/**
 * Signals that the operation will result in duplicate Games (Games are considered duplicates if they have the same
 * gameId).
 */
public class DuplicateGameException extends RuntimeException {

    public DuplicateGameException() {
        super("Operation would result in duplicate games");
    }
}
