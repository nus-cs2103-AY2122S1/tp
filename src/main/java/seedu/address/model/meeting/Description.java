package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a meeting-related information in NewAddressBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class Description {

    /*
     * The first character of the description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private static final String MESSAGE_CONSTRAINTS = "%s can be any values, and it should not be blank.";

    public final String value;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        value = description;
    }

    public static String getMessageConstraints(DescriptionType type) {
        return String.format(MESSAGE_CONSTRAINTS, type);
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && value.equals(((Description) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
