package seedu.address.model.person;

import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {


    public static final String MESSAGE_CONSTRAINTS =
    "Phone numbers should only contain numbers, and it should be at least 3 digits long";

    public static final String VALIDATION_REGEX = "\\d{3,}";
    public final Optional<String> value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        if (phone.isEmpty()) {
            value = Optional.empty();
        } else {
            requireNonNull(phone);
            checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
            value = Optional.of(phone);
        }

    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {

        if (value.isEmpty()) {

            return "No Phone Number yet";

        } else {

            return value.get();

        }


    }

    @Override
    public boolean equals(Object other) {

        return other == this // short circuit if same object
                || ( value.isEmpty() ? false : //returns false if not same object and current phone number is empty
                    (other instanceof Phone // instanceof handles nulls
                    && value.equals(((Phone) other).value))); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
