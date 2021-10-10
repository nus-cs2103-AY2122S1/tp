package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Client's phone number in Sellah.
 */
public class PhoneNumber {
    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should not be blank";

    /**
     * The phone number should contain digits from 0 to 9 only.
     */
    public static final String VALIDATION_REGEX = "[0-9]+";

    public final String phoneNumber;

    /**
     * Constructs a {@code PhoneNumber}.
     *
     * @param phoneNumber A valid phoneNumber.
     */
    public PhoneNumber(String phoneNumber) {
        requireNonNull(phoneNumber);
        checkArgument(isValidPhoneNumber(phoneNumber), MESSAGE_CONSTRAINTS);
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns true if a given string is a valid phone number.
     *
     * @param phoneNumber Phone number to be tested.
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneNumber // instanceof handles nulls
                && phoneNumber.equals(((PhoneNumber) other).phoneNumber)); // state check
    }

    @Override
    public int hashCode() {
        return phoneNumber.hashCode();
    }
}
