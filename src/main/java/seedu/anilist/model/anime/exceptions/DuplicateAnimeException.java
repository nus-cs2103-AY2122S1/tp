package seedu.anilist.model.anime.exceptions;

/**
 * Signals that the operation will result in duplicate Animes (Animes are considered duplicates if they have the same
 * identity).
 */
public class DuplicateAnimeException extends RuntimeException {
    public DuplicateAnimeException() {
        super("Operation would result in duplicate animes");
    }
}
