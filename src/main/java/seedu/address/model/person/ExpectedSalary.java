package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.util.StringUtil;

/**
 * Represents a Person's expected salary in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidExpectedSalary(String)}
 */
public class ExpectedSalary {

    public static final String MESSAGE_CONSTRAINTS = "Expected salary should only be a non-negative integer "
            + "(ranges from 0 to 2^(31) - 1 inclusive), and it should not be blank";

    /**
     * The first character of the salary must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\d+";

    public final String value;

    /**
     * Constructs an {@code ExpectedSalary}.
     *
     * @param expectedSalary A valid expected salary.
     */
    public ExpectedSalary(String expectedSalary) {
        requireNonNull(expectedSalary);
        checkArgument(isValidExpectedSalary(expectedSalary), MESSAGE_CONSTRAINTS);

        String leadingZeroesRemoved = StringUtil.removeLeadingZeroes(expectedSalary);
        value = leadingZeroesRemoved.isEmpty() ? "0" : leadingZeroesRemoved;
    }

    /**
     * Returns true if a given string represents a valid expected salary, false otherwise.
     *
     * @param test {@code String} representing an {@code ExpectedSalary} input to validate.
     * @return true if the given {@code String} represents a valid expected salary, false otherwise.
     */
    public static boolean isValidExpectedSalary(String test) {
        try {
            if (test.matches(VALIDATION_REGEX)) {
                Integer.parseInt(test);
                return true;
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
                || (other instanceof ExpectedSalary // instanceof handles nulls
                && value.equals(((ExpectedSalary) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
