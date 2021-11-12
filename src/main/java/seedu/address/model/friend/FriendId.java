package seedu.address.model.friend;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Friend's unique friend identifier in the gitGud friend's list.
 * Guarantees: immutable; is valid as declared in {@link #isValidFriendId(String)}
 */
public class FriendId {

    public static final String MESSAGE_EMPTY_FRIEND_ID =
            "FRIEND_ID cannot be empty.";
    public static final String MESSAGE_INVALID_CHARACTERS = "FRIEND_ID provided must be a single alphanumeric word, "
            + "with a maximum length of 20 characters.";

    /**
     * Non-empty, alpha-numeric, one-word string with a max limit of 20 characters.
     */
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9]{1,20}$";

    public final String value;

    /**
     * Constructs a {@code FriendId}.
     *
     * @param friendId A valid friendId.
     */
    public FriendId(String friendId) {
        requireNonNull(friendId);
        checkArgument(isValidFriendId(friendId), MESSAGE_INVALID_CHARACTERS);
        value = friendId;
    }

    /**
     * Returns true if a given string is a valid friendId.
     */
    public static boolean isValidFriendId(String friendId) {
        return friendId.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FriendId // instanceof handles nulls
                && value.equalsIgnoreCase(((FriendId) other).value)); // state check
    }

    @Override
    public String toString() {
        return value;
    }
}
