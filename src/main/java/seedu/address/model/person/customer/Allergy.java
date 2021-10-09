package seedu.address.model.person.customer;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Allergy {

    public static final String MESSAGE_CONSTRAINTS =
            "Allergies should only contain alphabetic characters and spaces, and cannot be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alpha}][\\p{Alpha} ]*";

    public final String allergicTo;

    /**
     * Constructs an {@code Allergy}.
     *
     * @param allergicTo A valid allergy.
     */
    public Allergy(String allergicTo) {
        requireNonNull(allergicTo);
        checkArgument(isValidAllergy(allergicTo), MESSAGE_CONSTRAINTS);
        this.allergicTo = allergicTo;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidAllergy(String allergyTo) {
        return allergyTo.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return allergicTo;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Allergy // instanceof handles nulls
                && allergicTo.equals(((Allergy) other).allergicTo)); // state check
    }

    @Override
    public int hashCode() {
        return allergicTo.hashCode();
    }

}
