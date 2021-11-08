package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {


    public static final String EMPTY_FIELD = "";
    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be at least 3 digits long";
    public static final String VALIDATION_REGEX = "\\d{3,}";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        if (!phone.equals(EMPTY_FIELD)) {
            checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        }
        value = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     *
     * @param test String of phone to be tested against the validation regex.
     * @return Boolean representation of validity of String of phone.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the String representation of Phone.
     *
     * @return String representation of Phone.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Method to compare two Phone objects.
     *
     * @param other is the object that is going to be compared
     *              to the Phone object that called this method.
     * @return boolean representation of whether the Phone
     * object is equal to the other object passed as parameter.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Phone // instanceof handles nulls
                && value.equals(((Phone) other).value)); // state check
    }

    /**
     * Returns the {@code hashCode} of Phone.
     *
     * @return hashCode of Phone.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
