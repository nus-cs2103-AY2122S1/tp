package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Locale;

/**
 * Represents a Person's Github username in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGithub(String)}
 */
public class Github {

    public static final String MESSAGE_CONSTRAINTS =
            "Github username may only contain alphanumeric characters or hyphens,"
                    + " cannot have multiple consecutive hyphens,"
                    + " cannot begin or end with a hyphen,"
                    + " and it should be between 4 to 39 characters long ";
    public static final String VALIDATION_REGEX = "[a-zA-Z\\d](?:[a-zA-Z\\d]|-(?=[a-zA-Z\\d])){0,38}";
    public final String value;

    /**
     * Constructs a {@code Github}.
     *
     * @param github A valid Github username.
     */
    public Github(String github) {
        requireNonNull(github);
        checkArgument(isValidGithub(github), MESSAGE_CONSTRAINTS);
        value = github;
    }

    /**
     * Returns true if a given string is a valid Github username.
     */
    public static boolean isValidGithub(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Github // instanceof handles nulls
                && value.toLowerCase(Locale.ROOT).equals((
                        (Github) other).value.toLowerCase(Locale.ROOT))); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
