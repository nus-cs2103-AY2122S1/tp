package seedu.address.model.person.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's form class in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidFormClass(String)}
 */
public class FormClass {

    public static final String MESSAGE_CONSTRAINTS =
            "Form class should be alphanumeric, and be in the form of '[Number][String][Number]'.\n"
                    + "For Example: '4E1' or '3Express1'.";

    /*
     * The first character of FormClass must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[0-9]+[a-zA-Z]+[0-9]+$";

    public final String value;

    /**
     * Constructs an {@code FormClass}.
     *
     * @param value A valid form class.
     */
    public FormClass(String value) {
        requireNonNull(value);
        checkArgument(isValidFormClass(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid form class.
     */
    public static boolean isValidFormClass(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FormClass // instanceof handles nulls
                && value.equals(((FormClass) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
