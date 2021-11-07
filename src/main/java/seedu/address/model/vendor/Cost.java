package seedu.address.model.vendor;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.function.Predicate;

/**
 * Represents a cost in the vendor book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCost(Double)}
 */
public class Cost {

    public static final String MESSAGE_CONSTRAINTS =
            "Cost should be in dollars and cents (e.g. 25.30) and must be a positive cost greater than 0";

    public static final String MESSAGE_FILTER_CONSTRAINTS =
            "Invalid syntax to filter cost. Examples of valid syntax: c/<20, c/>10, c/8";

    public static final String INVALID_DOUBLE =
            "Cost should be a valid double.";

    private static final double MIN_VALUE = 0.0;

    public static final Predicate<Double> VALIDATION_PREDICATE = cost -> cost > MIN_VALUE;

    public final Double value;

    /**
     * Constructs a {@code Cost}.
     *
     * @param cost A valid cost.
     */
    public Cost(Double cost) {
        requireNonNull(cost);
        checkArgument(isValidCost(cost), MESSAGE_CONSTRAINTS);
        value = cost;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidCost(Double test) {
        return VALIDATION_PREDICATE.test(test);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Cost // instanceof handles nulls
                && value.equals(((Cost) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
