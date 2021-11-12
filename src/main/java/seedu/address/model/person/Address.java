package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EMPTY_FIELD = "";
    public static final String MESSAGE_CONSTRAINTS = "Addresses can take any value with maximum of 150 characters,"
            + " and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].{0,150}";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid address.
     */
    public Address(String address) {
        requireNonNull(address);
        if (!address.equals(EMPTY_FIELD)) {
            checkArgument(isValidAddress(address), MESSAGE_CONSTRAINTS);
        }
        value = address;
    }

    /**
     * Returns true if a given string is a valid email.
     *
     * @param test String of address to be tested against the validation regex.
     * @return Boolean representation of validity of String of address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the String representation of Address.
     *
     * @return String representation of Address.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Method to compare two Address objects.
     *
     * @param other is the object that is going to be compared
     *              to the Address object that called this method.
     * @return boolean representation of whether the Address
     * object is equal to the other object passed as parameter.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && value.equals(((Address) other).value)); // state check
    }

    /**
     * Returns the {@code hashCode} of Address.
     *
     * @return hashCode of Address.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
