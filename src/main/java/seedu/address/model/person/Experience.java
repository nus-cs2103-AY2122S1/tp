package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.util.StringUtil;


/**
 * Represents an Applicant's applied role in the address book.
 */
public class Experience {

    public static final String MESSAGE_CONSTRAINTS = "Experience should be a number with at most 1 decimal place, "
            + "should be non-negative, not larger than 67 (re-employment age in Singapore), and it should not be blank";

    /**
     * Year of Experience should be a positive number with at most 1 decimal place.
     */
    public static final String VALIDATION_REGEX = "(\\d{0,2}\\.\\d)|(\\d{0,2})";

    public static final double MAX_EXPERIENCE = 67.0;
    public final String value;

    /**
     * Constructs a {@code Experience}.
     *
     * @param experience A valid integer.
     */
    public Experience(String experience) {
        requireNonNull(experience);
        checkArgument(isValidExperience(experience), MESSAGE_CONSTRAINTS);

        String leadingZeroesRemoved = StringUtil.removeLeadingZeroes(experience);
        value = leadingZeroesRemoved.isEmpty() ? "0" : leadingZeroesRemoved;
    }

    /**
     * Returns true if a given number matches validation.
     */
    public static boolean isValidExperience(String test) {
        try {
            if (test.matches(VALIDATION_REGEX)) {
                double value = Double.parseDouble(test);
                return (value <= MAX_EXPERIENCE);
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value;
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
