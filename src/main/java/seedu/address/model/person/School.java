package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's school name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSchName(String)}
 */
public class School {
    public static final String MESSAGE_CONSTRAINTS =
            "School names should only contain alphabetic characters, single quotation marks and spaces.";

    /*
     * The first character of the school name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[a-zA-Z'][a-zA-Z' ]*";

    public final String schName;

    /**
     * Constructs a {@code School}.
     *
     * @param schName A valid school name.
     */
    public School(String schName) {
        requireNonNull(schName);
        if (!schName.isEmpty()) {
            checkArgument(isValidSchName(schName), MESSAGE_CONSTRAINTS);
        }

        this.schName = schName;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidSchName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if school name is empty.
     *
     * @return True if school name is empty.
     */
    public boolean isEmpty() {
        return schName.isEmpty();
    }


    @Override
    public String toString() {
        return schName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof School // instanceof handles nulls
                && schName.equals(((School) other).schName)); // state check
    }

    @Override
    public int hashCode() {
        return schName.hashCode();
    }

}
