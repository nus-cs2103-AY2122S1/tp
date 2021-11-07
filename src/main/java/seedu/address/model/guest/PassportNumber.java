package seedu.address.model.guest;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class PassportNumber {

    public static final String MESSAGE_CONSTRAINTS = "Passport Number must be alphanumeric.";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs an {@code RoomNumber}.
     *
     * @param passportNumber A valid room number.
     */
    public PassportNumber(String passportNumber) {
        requireNonNull(passportNumber);
        checkArgument(isValidPassportNumber(passportNumber), MESSAGE_CONSTRAINTS);
        this.value = passportNumber;
    }

    /**
     * Returns true if a given string is a valid room number.
     */
    public static boolean isValidPassportNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PassportNumber// instanceof handles nulls
                && value.equals(((PassportNumber) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}



