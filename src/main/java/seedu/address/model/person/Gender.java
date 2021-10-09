package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

enum GenderType {
    MALE, FEMALE
}

/**
 * Represents a Person's gender in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGender(String)}
 */
public class Gender {

    public static final String MESSAGE_CONSTRAINTS =
            "Gender should only be M(ale) or F(emale), and it should not be blank";

    public final GenderType value;

    /**
     * Constructs a {@code Gender}.
     *
     * @param gender A valid gender.
     */
    public Gender(String gender) {
        requireNonNull(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
        value = getGenderType(gender);
    }
    
    public String getSymbol() {
        if (value.equals(GenderType.MALE)) {
            return "M";
        } else if (value.equals(GenderType.FEMALE)) {
            return "F";
        }
        assert false : "value should be one of the GenderTypes";
        return "";
    }

    /**
     * Returns true if a given string is a valid gender.
     */
    public static boolean isValidGender(String test) {
        String gender = test.toLowerCase();
        return gender.equals("f") || gender.equals("female") || gender.equals("m") || gender.equals("male");
    }

    private GenderType getGenderType(String input) {
        String gender = input.toLowerCase();
        if (gender.equals("m") || gender.equals("male")) {
            return GenderType.MALE;
        } else if (gender.equals("f") || gender.equals("female")) {
            return GenderType.FEMALE;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return value.name();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Gender // instanceof handles nulls
                && value.name().equals(((Gender) other).value.name())); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
