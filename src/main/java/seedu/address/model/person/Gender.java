package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's gender in NewAddressBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidGender(String)}
 */

public class Gender {

    public static final String MESSAGE_CONSTRAINTS =
            "Gender class should only contain one of M (male), F (female), N (non binary)";

    public final String value;

    /**
     * Constructs a gender.
     *
     * @param gender A valid formClass.
     */
    public Gender(String gender) {
        requireNonNull(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
        this.value = gender;
    }

    /**
     * Returns true if a given string is a valid form class.
     */
    public static boolean isValidGender(String test) {
        return test.matches("M") || test.matches("F") || test.matches("N");
    }

    /**
     * Gets the full string representation of the gender
     *
     * @return the full string representation of the gender
     */

    public String fullGenderString() {
        switch (value) {
        case "M":
            return "Male";
        case "F":
            return "Female";
        case "N":
            return "Non-binary";
        default:
            return "";
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Gender // instanceof handles nulls
                && value.equals(((Gender) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
