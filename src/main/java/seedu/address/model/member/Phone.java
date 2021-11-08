package seedu.address.model.member;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Member's phone number in the ezFoodie.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {


    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain 8 digits, and it should start with 3, 6, 8 or 9";
    public static final String VALIDATION_REGEX = "^[3689]\\d{7}";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone a valid phone number
     */
    public Phone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Overrides toString method.
     *
     * @return String of the valid phone value
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Overrides equals method.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Phone // instanceof handles nulls
                && value.equals(((Phone) other).value)); // state check
    }

    /**
     * Overrides hashCode method.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
