package seedu.address.model.gamefriendlink;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an in-game username of a {@code Friend} in a {@code Game} both of which are in the gitGud database.
 * Guarantees: immutable; is valid as declared in {@link #isValidUserName(String)}
 */
public class UserName {

    public static final String MESSAGE_CONSTRAINTS = "IN_GAME_USERNAME provided must be a non-empty string "
            + "of maximum 20 characters, and not contain '-' (hyphen).";

    /**
     * The username must not be blank, empty, whitespace or contain a '-'. Max limit of 20 characters.
     * This is to avoid any flags being inserted in the username.
     */
    public static final String VALIDATION_REGEX = "^[^ -][^-]{0,19}$";

    public final String value;

    /**
     * Constructs a {@code UserName}.
     *
     * @param userName A valid username.
     */
    public UserName(String userName) {
        requireNonNull(userName);
        checkArgument(isValidUserName(userName), MESSAGE_CONSTRAINTS);
        value = userName;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidUserName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UserName // instanceof handles nulls
                && value.equals(((UserName) other).value)); // state check
    }

    @Override
    public String toString() {
        return value;
    }
}
