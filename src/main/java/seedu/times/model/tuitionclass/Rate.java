package seedu.times.model.tuitionclass;

import static java.util.Objects.requireNonNull;
import static seedu.times.commons.util.AppUtil.checkArgument;
import static seedu.times.model.util.Commons.MAX_CHAR_COUNT;

/**
 * Represents a {@code TuitionClass}'s rate in the Timestable.
 */
public class Rate {

    public static final String MESSAGE_CONSTRAINTS =
            "Rate should satisfy the following constraints:\n"
            + "1. Not exceed a million\n"
            + "2. Not be negative\n"
            + "3. Not be empty\n"
            + "4. At most be up to 2 decimal places\n"
            + "5. You also cannot enter a decimal point without following it up with at least one digit.\n";
    public static final String VALIDATION_REGEX = "\\d{1,6}(\\.\\d{1,2})?";

    public final String value;

    /**
     * Constructs a {@code Rate}
     *
     * @param rate A valid tuition per hour rate.
     */
    public Rate(String rate) {
        requireNonNull(rate);
        checkArgument(isValidRate(rate), MESSAGE_CONSTRAINTS);
        value = rate;
    }

    /**
     * Returns true if a given string is a valid tuition rate.
     *
     * @param test String representing rate to be tested.
     * @return A boolean value.
     */
    public static boolean isValidRate(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() < MAX_CHAR_COUNT;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Rate // instanceof handles nulls
                && value.equals(((Rate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
