package seedu.address.model.friend;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Friend's name in the gitGud friend's list.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class FriendName {
    // constants
    public static final FriendName DEFAULT_FRIEND_NAME = new FriendName("No name assigned");
    public static final String MESSAGE_CONSTRAINTS =
            "NAME provided must be a non-empty string of maximum 20 characters, and not contain '-' (hyphen).";

    /**
     * The name must not be blank, empty, whitespace or contain a '-'. Max limit of 20 characters.
     * This is to avoid any flags being inserted in the name.
     */
    public static final String VALIDATION_REGEX = "^[^ -][^-]{0,19}$";

    public final String fullName;

    /**
     * Constructs a {@code FriendName}.
     *
     * @param name A valid friend's name.
     */
    public FriendName(String name) {
        if (name == null) {
            fullName = DEFAULT_FRIEND_NAME.fullName;
        } else {
            checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
            fullName = name;
        }
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FriendName // instanceof handles nulls
                && fullName.equals(((FriendName) other).fullName)); // state check
    }

    @Override
    public String toString() {
        return fullName;
    }
}
