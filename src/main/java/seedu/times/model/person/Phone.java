package seedu.times.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.times.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's phone number in the Timestable.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {


    public static final int MAX_DIGIT_COUNT = 25;
    public static final int MIN_DIGIT_COUNT = 3;
    public static final String MESSAGE_CONSTRAINTS =
            "Phone number contains non-number characters or its length is not between "
                    + MIN_DIGIT_COUNT + " and "
                    + MAX_DIGIT_COUNT + " digits!";
    public static final String VALIDATION_REGEX = "\\d{3,25}";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
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
