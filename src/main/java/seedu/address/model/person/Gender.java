package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's gender in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGender(String)}
 */
public class Gender {

    public static final String MESSAGE_CONSTRAINTS =
            "Gender should only be 'M' for male, 'F' for female and 'O' for others.\n"
                    + "Leaving it blank will remove the Gender field.";

    /*
     * The gender must be one of the following character: F, M or O.
     */
    public static final String VALIDATION_REGEX = "[FMOfmo]";

    public final String gender;

    /**
     * Constructs a {@code Gender}.
     *
     * @param gender A valid gender.
     */
    public Gender(String gender) {
        requireNonNull(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
        this.gender = gender.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid gender.
     */
    public static boolean isValidGender(String test) {
        if (test.isEmpty()) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the gender is a male.
     */
    public boolean isMale() {
        return gender.equals("M");
    }

    /**
     * Returns true if the gender is a female.
     */
    public boolean isFemale() {
        return gender.equals("F");
    }

    /**
     * Returns true if the gender is others.
     */
    public boolean isOther() {
        return gender.equals("O");
    }

    @Override
    public String toString() {
        return gender;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Gender // instanceof handles nulls
                && gender.equals(((Gender) other).gender)); // state check
    }

    @Override
    public int hashCode() {
        return gender.hashCode();
    }

}
