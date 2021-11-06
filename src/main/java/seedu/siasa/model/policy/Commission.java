package seedu.siasa.model.policy;

import static seedu.siasa.commons.util.AppUtil.checkArgument;
import static seedu.siasa.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

public class Commission {
    public static final String MESSAGE_CONSTRAINTS =
            "Commission percentage should be a number from 0 to 100 representing the percentage;\n"
                    + "Num of payments with commission should be non-negative and not larger than the "
                    + "num of payments in the payment structure and 2147483647";
    public final int commissionPercentage;
    // number of payments with commission
    public final int numberOfPayments;

    /**
     * Constructs an {@code Commission}.
     *
     * @param percentage A valid commission.
     */
    public Commission(int percentage, int numberOfPayments) {
        requireAllNonNull(percentage, numberOfPayments);
        checkArgument(isValidCommission(percentage, numberOfPayments), MESSAGE_CONSTRAINTS);
        this.commissionPercentage = percentage;
        this.numberOfPayments = numberOfPayments;
    }

    /**
     * Returns if {@code percentage} and {@code numberOfPayments} are valid for Commission.
     */
    public static boolean isValidCommission(int percentage, int numberOfPayments) {
        boolean percentageWithinRange = 0 <= percentage && percentage <= 100;
        boolean validNumberOfPayments = numberOfPayments >= 0;
        return percentageWithinRange && validNumberOfPayments;
    }

    /**
     * Returns if {@code percentage}, {@code numberOfPayments} and {@code paymentStructure} are valid for Commission.
     */
    public static boolean isValidCommission(int percentage, int numberOfPayments, PaymentStructure paymentStructure) {
        return isValidCommission(percentage, numberOfPayments) && numberOfPayments <= paymentStructure.numberOfPayments;
    }

    @Override
    public String toString() {
        String numberOfPaymentsStr = " [" + numberOfPayments + " payment(s)]";
        return commissionPercentage + "%" + numberOfPaymentsStr;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Commission // instanceof handles nulls
                && commissionPercentage == ((Commission) other).commissionPercentage
                && numberOfPayments == ((Commission) other).numberOfPayments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commissionPercentage, numberOfPayments);
    }
}
