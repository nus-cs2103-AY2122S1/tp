package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Transaction's billing in the Transaction.
 * Guarantees: immutable; is valid as declared in {@link #isValidBilling(String)}.
 */
public class Billing {

    /**
     * Stands for the max transaction billing number.
     */
    public static final double MAX = 9999.99;

    /**
     * Stands for message constraints of transaction billing.
     */
    public static final String MESSAGE_CONSTRAINTS =
            "Billings should be non-negative numeric with 2 decimal places, and max amount is " + MAX;

    /**
     * Stands for trim leading zero regex.
     */
    public static final String TRIM_LEADING_ZERO_REGEX = "^0+(?!$)";

    /**
     * Stands for validation regex of transaction billing.
     */
    public static final String VALIDATION_REGEX = "\\d*\\.\\d{2}$";

    /**
     * Stands for transaction billing max amount length.
     */
    public static final int LENGTH = 7; // Max amount is 9999.99

    /**
     * Stands for transaction billing value.
     */
    public final String value;

    /**
     * Constructs a {@code Billing}.
     *
     * @param billing a valid billing amount.
     */
    public Billing(String billing) {
        requireNonNull(billing);
        checkArgument(isValidBilling(billing), MESSAGE_CONSTRAINTS);
        value = billing;
    }

    /**
     * Returns whether is valid billing.
     *
     * @param test input string test.
     * @return boolean true if a given string is a valid billing amount.
     */
    public static boolean isValidBilling(String test) {
        test = test.replaceFirst(TRIM_LEADING_ZERO_REGEX, "");
        try {
            return test.length() <= LENGTH && Double.parseDouble(test) <= MAX && test.matches(VALIDATION_REGEX);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns double value of billing amount.
     */
    public double getDoubleValue() {
        return Double.parseDouble(value);
    }

    /**
     * Overrides toString method.
     *
     * @return transaction billing value.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Overrides the equals method.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Billing // instanceof handles nulls
                && value.equals(((Billing) other).value)); // state check
    }

    /**
     * Overrides the hashCode method.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
