package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's email in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class Email {

    public static final String MESSAGE_CONSTRAINTS = "Emails should be of the format <NUS Network ID>@u.nus.edu";
    public static final String VALIDATION_REGEX = "[eE][0-9]{7}@u.nus.edu{1}"; //LOCAL_PART_REGEX + "@" + DOMAIN_REGEX;
    public final String value;

    /**
     * Constructs an {@code Email}.
     *
     * @param email A valid email address.
     */
    public Email(String email) {
        requireNonNull(email);
        checkArgument(isValidEmail(email), MESSAGE_CONSTRAINTS);
        value = email.toLowerCase();
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidEmail(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Compares this value with another Email's value using Java's compareTo function
     */
    public int compareTo(Email e) {
        return value.compareTo(e.value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Email)) {
            return false;
        }

        Email otherEmail = (Email) other;

        return value.equals(otherEmail.value); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
