package seedu.address.model.chargeable;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.function.Predicate;

/**
 * Represents a quantity for the service
 * Guarantees: immutable; is valid as declared in {@link #isValidQuantity(Integer)}
 */
public class Quantity {

    public static final String MESSAGE_CONSTRAINTS =
            "Quantity should be in whole number (e.g. 1, 2, 3...) and must be a positive number "
                    + "greater than or equals to 0";

    public static final String INVALID_INTEGER =
            "Quantity should be a valid integer.";

    private static final int MIN_VALUE = 0;

    public static final Predicate<Integer> VALIDATION_PREDICATE = qty -> qty >= MIN_VALUE;

    public final Integer value;

    /**
     * Constructs a {@code Quantity}.
     *
     * @param quantity A valid quantity.
     */
    public Quantity(Integer quantity) {
        requireNonNull(quantity);
        checkArgument(isValidQuantity(quantity), MESSAGE_CONSTRAINTS);
        value = quantity;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidQuantity(Integer test) {
        return VALIDATION_PREDICATE.test(test);
    }

    @Override
    public String toString() {
        return value.toString();
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

}
