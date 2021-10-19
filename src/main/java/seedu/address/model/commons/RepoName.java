package seedu.address.model.commons;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class RepoName {
    public static final String MESSAGE_CONSTRAINTS =
            "Repo name should contain only alphanumeric characters, underscores, "
             + "tilde, dots and dashes, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}._\\-~][\\p{Alnum}._\\-~]*";

    public final String repoName;

    /**
     * Constructs a {@code RepoName}.
     *
     * @param repoName A valid repo name to be used in the link.
     */
    public RepoName(String repoName) {
        requireNonNull(repoName);
        checkArgument(isValidRepoName(repoName), MESSAGE_CONSTRAINTS);
        this.repoName = repoName;
    }

    public RepoName() {
        this.repoName = null;
    }

    public boolean isNull() {
        return repoName == null ? true : false;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidRepoName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return repoName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RepoName // instanceof handles nulls
                && repoName.equals(((RepoName) other).repoName)); // state check
    }

    @Override
    public int hashCode() {
        return isNull() ? 0 : repoName.hashCode();
    }
}
