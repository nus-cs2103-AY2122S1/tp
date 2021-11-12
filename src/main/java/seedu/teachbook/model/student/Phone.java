package seedu.teachbook.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.teachbook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's phone number in the TeachBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}.
 */
public class Phone {

    public static final String MESSAGE_CONSTRAINTS = "Phone numbers should only contain numbers, and it should be at "
            + "least 3 digits long";

    public static final String VALIDATION_REGEX = "\\d{3,}";

    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone a valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    /**
     * Checks if the given string is a valid phone number.
     *
     * @param test phone number string to be tested.
     * @return {@code true} is the given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.equals("") || test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Phone // instanceof handles nulls
                && value.equals(((Phone) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
