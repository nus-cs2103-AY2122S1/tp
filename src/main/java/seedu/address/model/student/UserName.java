package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class UserName {
    public static final String MESSAGE_CONSTRAINTS =
            "User name should contain only alphanumeric characters, underscores, "
                    + "tilde, dots and dashes, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}._\\-~][\\p{Alnum}._\\-~]*";

    public final String userName;

    /**
     * Constructs a {@code UserName}.
     *
     * @param userName A valid user name to be used in the link.
     */
    public UserName(String userName) {
        requireNonNull(userName);
        checkArgument(isValidUserName(userName), MESSAGE_CONSTRAINTS);
        this.userName = userName;
    }

    public UserName() {
        this.userName = null;
    }

    public boolean isNull() {
        return userName == null ? true : false;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidUserName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return userName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UserName // instanceof handles nulls
                && ((isNull() && ((UserName) other).isNull())
                || userName.equals(((UserName) other).userName))); // state check
    }

    @Override
    public int hashCode() {
        return isNull() ? 0 : userName.hashCode();
    }
}
