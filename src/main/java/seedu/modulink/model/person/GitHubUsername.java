package seedu.modulink.model.person;

import static seedu.modulink.commons.util.AppUtil.checkArgument;

public class GitHubUsername {

    public static final String MESSAGE_CONSTRAINTS =
            "GitHub username should start with an alphanumeric character, "
                    + "can only contain alphanumeric characters and hyphens, "
                    + "be longer than one character, and must not contain any spaces.";
    public static final String VALIDATION_REGEX = "^[A-Za-z0-9]+[A-Za-z0-9-]+$";
    public final String value;

    /**
     * Constructs an {@code GitHub username}.
     *
     * @param username A valid GitHub username.
     */
    public GitHubUsername(String username) {
        checkArgument(isValidUsername(username), MESSAGE_CONSTRAINTS);
        value = username;
    }

    /**
     * Returns true if a given string is a valid GitHub username.
     */
    public static boolean isValidUsername(String test) {
        if (test == null) {
            return true;
        } else {
            return test.matches(VALIDATION_REGEX);
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GitHubUsername // instanceof handles nulls
                && value.equals(((GitHubUsername) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isNull() {
        return value == null;
    }
}
