package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's outstanding fees in TAB.
 * Guarantees: immutable; is valid as declared in {@link #isValidFee(String)}
 */
public class Fee {

    public static final String MESSAGE_CONSTRAINTS =
            "Fees should only contain numbers and at most one decimal point";
    public static final String VALIDATION_REGEX = "^[0-9]+(\\.[0-9]{1,2})?$";
    public final String value;


    /**
     * Constructs an outstanding {@code Fee}.
     *
     * @param fee A valid outstanding fee.
     */
    public Fee(String fee) {
        requireNonNull(fee);
        checkArgument(isValidFee(fee), MESSAGE_CONSTRAINTS);
        value = fee.isEmpty() ? "0" : fee;
    }

    /**
     * Returns true if a given string is a valid fee.
     */
    public static boolean isValidFee(String test) {
        return test.isEmpty() || test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Fee // instanceof handles nulls
                && value.equals(((Fee) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
