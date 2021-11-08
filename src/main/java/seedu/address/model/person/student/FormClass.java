package seedu.address.model.person.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's form class in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidFormClass(String)}
 */
public class FormClass {

    public static final String MESSAGE_CONSTRAINTS =
            "Form class should not be blank and must be of the format '|LEVEL|STRING|[ALPHANUMERIC]|'.\n"
                    + "  - 'LEVEL' must be a digit from 1 to 5\n"
                    + "  - 'STRING' should not be blank and can only contain alphabets\n"
                    + "  - 'ALPHANUMERIC' is optional and can only contain alphanumeric characters\n"
                    + "For Example: '4E1' or '3Hope'.";

    /*
     * The first character of FormClass must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[1-5][a-zA-Z]\\p{Alnum}*";

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
