package seedu.address.model.product;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Product's quantity in Sellah.
 */
public class Quantity {
    public static final String MESSAGE_CONSTRAINTS =
            "Unit prices should only contain numbers and it should not be blank";

    /**
     * The quantity should contain digits from 0 to 9 only.
     */
    public static final String VALIDATION_REGEX = "[0-9]+";

    public final String quantity;

    /**
     * Constructs a {@code Quantity}.
     *
     * @param quantity A valid quantity.
     */
    public Quantity(String quantity) {
        requireNonNull(quantity);
        checkArgument(isValidQuantity(quantity), MESSAGE_CONSTRAINTS);
        this.quantity = quantity;
    }

    /**
     * Returns true if a given string is a valid quantity.
     *
     * @param quantity Quantity to be tested.
     */
    public static boolean isValidQuantity(String quantity) {
        return quantity.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return quantity;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Quantity // instanceof handles nulls
                && quantity.equals(((Quantity) other).quantity)); // state check
    }

    @Override
    public int hashCode() {
        return quantity.hashCode();
    }
}
