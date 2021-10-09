package seedu.address.model.person.customer;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class LoyaltyPoints {

    public static final String MESSAGE_CONSTRAINTS =
            "Loyalty points should only contain numbers, and in multiples of 1000";
    public static final String VALIDATION_REGEX = "\\d{4,}";
    public final String value;

    /**
     * Constructs a {@code LoyaltyPoints}.
     *
     * @param value A valid value for points.
     */
    public LoyaltyPoints(String value) {
        requireNonNull(value);
        checkArgument(isValidLoyaltyPoints(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given value is a valid point value.
     */
    public static boolean isValidLoyaltyPoints(String value) {
        return value.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LoyaltyPoints // instanceof handles nulls
                && value.equals(((LoyaltyPoints) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
