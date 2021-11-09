package seedu.modulink.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class UserProfileIsFavouriteException extends RuntimeException {
    public UserProfileIsFavouriteException() {
        super("User profile has been set as favourite");
    }
}
