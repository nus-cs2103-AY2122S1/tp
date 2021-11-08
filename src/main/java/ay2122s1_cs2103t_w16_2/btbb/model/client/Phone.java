package ay2122s1_cs2103t_w16_2.btbb.model.client;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Client's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {
    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should have minimally 3 digits and maximally 20 digits";
    private static final String VALIDATION_REGEX = "\\d{3,20}";
    private final String phoneNumber;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        phoneNumber = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Phone // instanceof handles nulls
                && phoneNumber.equals(((Phone) other).phoneNumber)); // state check
    }

    @Override
    public int hashCode() {
        return phoneNumber.hashCode();
    }
}
