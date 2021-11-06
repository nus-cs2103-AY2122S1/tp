package seedu.address.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an amount associated with a Order.
 */
public class Amount implements Comparable<Amount> {

    public static final String MESSAGE_CONSTRAINTS =
            "Sales order is any valid real number. Note that there may be small rounding errors for arbitrary "
            + "real numbers, and the maximum amount for any single order is capped at 1 billion.";
    private static final double MAXIMUM_AMOUNT = 1000000000;
    public final String amount;

    /**
     * Constructs an {@code Amount}
     *
     * @param amount A valid Order amount.
     */
    public Amount(String amount) {
        requireNonNull(amount);
        checkArgument(isValidAmount(amount), MESSAGE_CONSTRAINTS);
        this.amount = amount;
    }

    /**
     * Returns true if a given string is a valid amount.
     * An amount is valid if it is less than 10^9 and non-negative.
     */
    public static boolean isValidAmount(String test) {
        try {
            double amount = Double.parseDouble(test);
            return amount <= MAXIMUM_AMOUNT && amount >= 0;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    @Override
    public String toString() {
        return amount;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Amount
                && amount.equals(((Amount) other).amount));
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }


    @Override
    public int compareTo(Amount a) {
        return Float.valueOf(amount).compareTo(Float.valueOf(a.amount));
    }
}
