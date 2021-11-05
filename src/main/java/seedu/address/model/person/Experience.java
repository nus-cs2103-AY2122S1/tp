package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.util.StringUtil;


/**
 * Represents an Applicant's applied role in the address book.
 */
public class Experience {

    public static final String MESSAGE_CONSTRAINTS = "Year of Experience should be a non-negative number "
            + "no larger than 67 (e-employment age in Singapore), "
            + "which can be either an integer or a double with only 0 or 5 behind decimal point"
            + ", and it should not be blank. "
            + "E.g. y/1, y/0.5, y/3.0";

    /**
     * Check that experience is an integer or a double with only 0 or 5 behind decimal point.
     */
    public static final String VALIDATION_REGEX = "(\\d{0,2}\\.5)|(\\d{0,2}\\.0)|(\\d{0,2})";

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
