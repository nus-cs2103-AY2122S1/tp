package seedu.address.model.group;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Group's name in tApp.
 * Guarantees: immutable;
 */
public class GroupGithub {

    public static class LinkYear {
        public static final String MESSAGE_CONSTRAINTS =
                "Years should contain only alphanumeric characters";

        public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

        public final String year;

        /**
         * Constructs a {@code LinkYear}.
         *
         * @param year A valid academic year to be used in the link.
         */
        public LinkYear(String year) {
            requireNonNull(year);
            checkArgument(isValidYear(year), MESSAGE_CONSTRAINTS);
            this.year = year;
        }

        /**
         * Returns true if a given string is a valid name.
         */
        public static boolean isValidYear(String test) {
            return test.matches(VALIDATION_REGEX);
        }


        @Override
        public String toString() {
            return year;
        }

        @Override
        public boolean equals(Object other) {
            return other == this // short circuit if same object
                    || (other instanceof LinkYear // instanceof handles nulls
                    && year.equals(((LinkYear) other).year)); // state check
        }

        @Override
        public int hashCode() {
            return year.hashCode();
        }
    }

    public static class RepoName {
        public static final String MESSAGE_CONSTRAINTS =
                "Repo name should contain only alphanumeric characters";

        public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

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
            return repoName.hashCode();
        }
    }

    public static final String GITHUB_ADDRESS = "https://github.com/";
    public static final String MODULE_CODE = "CS2103";

    public final String link;

    /**
     * Constructs a {@code GroupGithub}.
     *
     * @param year A valid academic year.
     * @param repoName A valid repo name.
     */
    public GroupGithub(LinkYear year, RepoName repoName) {
        requireAllNonNull(year, repoName);
        this.link = GITHUB_ADDRESS + year + "-" + MODULE_CODE + "-%1$s/" + repoName;
    }

    public GroupGithub() {
        this.link = "-";
    }

    public GroupGithub(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return link;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupGithub // instanceof handles nulls
                && link.equals(((GroupGithub) other).link)); // state check
    }

    @Override
    public int hashCode() {
        return link.hashCode();
    }

}