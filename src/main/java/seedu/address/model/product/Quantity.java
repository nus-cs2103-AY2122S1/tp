package seedu.address.model.product;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Product's quantity in Sellah.
 */
public class Quantity implements Comparable<Quantity> {
    public static final String MESSAGE_CONSTRAINTS = "Quantity should only contain numbers and it should not be blank";

    /**
     * The quantity should contain digits from 0 to 9 only.
     */
    public static final String VALIDATION_REGEX = "[0-9]+";

    public static final Quantity QUANTITY_ZERO = new Quantity("0");

    public final String value;

    /**
     * Constructs a {@code Quantity}.
     *
     * @param quantity A valid quantity.
     */
    public Quantity(String quantity) {
        requireNonNull(quantity);
        checkArgument(isValidQuantity(quantity), MESSAGE_CONSTRAINTS);
        value = quantity;
    }

    /**
     * Returns true if a given string is a valid quantity.
     *
     * @param quantity Quantity to be tested.
     */
    public static boolean isValidQuantity(String quantity) {
        return quantity.matches(VALIDATION_REGEX);
    }

    public boolean lessThan(Quantity quantity) {
        return this.compareTo(quantity) < 0;
    }

    public boolean moreThan(Quantity quantity) {
        return this.compareTo(quantity) > 0;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Quantity // instanceof handles nulls
                && value.equals(((Quantity) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Quantity quantity) {
        if (quantity == null) {
            return 1;
        }

        int thisVal = Integer.parseInt(value);
        int otherVal = Integer.parseInt(quantity.value);
        return thisVal - otherVal;
    }
}
