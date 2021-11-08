package seedu.address.model.person.customer;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class LoyaltyPoints {

    public static final String MESSAGE_CONSTRAINTS =
            "Loyalty points must be numerical, more than or equals to 0 and less than or equals to 100000";
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
        try {
            return Integer.parseInt(value) >= 0 && Integer.parseInt(value) <= 100000;
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
                || (other instanceof LoyaltyPoints // instanceof handles nulls
                && value.equals(((LoyaltyPoints) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
