package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Applicant's applied role in the address book.
 */
public class Experience {

    public static final String MESSAGE_CONSTRAINTS =
            "Year of experience should be a positive integer";


    public final Integer value;

    /**
     * Constructs a {@code Experience}.
     *
     * @param experience A valid integer.
     */
    public Experience(Integer experience) {
        requireNonNull(experience);
        checkArgument(isValidExperience(experience), MESSAGE_CONSTRAINTS);
        value = experience;
    }

    /**
     * Returns true if a given number is positive.
     */
    public static boolean isValidExperience(Integer test) {
        return (test >= 0);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Experience // instanceof handles nulls
                && value.equals(((Experience) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
