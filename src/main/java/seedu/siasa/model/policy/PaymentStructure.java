package seedu.siasa.model.policy;

import static seedu.siasa.commons.util.AppUtil.checkArgument;
import static seedu.siasa.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.siasa.commons.util.CurrencyUtil.centsToDollars;

import java.util.Objects;

public class PaymentStructure {
    public static final String MESSAGE_CONSTRAINTS =
        "Payment amount, frequency and count should be a non-negative integer not exceeding 2147483647.";

    public static final int INDEFINITE_NUMBER_OF_PAYMENTS = Integer.MAX_VALUE;
    // payment amount in cents
    public final int paymentAmount;
    // number of payments in a year
    public final int paymentFrequency;
    public final int numberOfPayments;

    /**
     * Constructs an {@code Price}.
     *
     * @param paymentAmount A valid price in cents.
     */
    public PaymentStructure(int paymentAmount, int paymentFrequency, int numberOfPayments) {
        requireAllNonNull(paymentAmount, paymentFrequency, numberOfPayments);
        checkArgument(isValidPaymentStructure(paymentAmount, paymentFrequency, numberOfPayments), MESSAGE_CONSTRAINTS);
        this.paymentAmount = paymentAmount;
        this.paymentFrequency = paymentFrequency;
        this.numberOfPayments = numberOfPayments;
    }

    public static boolean isValidPaymentStructure(int paymentAmount, int paymentFrequency, int numberOfPayments) {
        return paymentAmount >= 0 && paymentFrequency >= 0 && numberOfPayments >= 0;
    }

    @Override
    public String toString() {
        String paymentAmountStr = centsToDollars(paymentAmount);
        if (paymentFrequency == 1 && numberOfPayments == 1) {
            return "Lump sum payment of " + paymentAmountStr;
        }
        String paymentFrequencyStr = "";
        if (paymentFrequency == 12) {
            paymentFrequencyStr = "Monthly";
        } else if (paymentFrequency == 4) {
            paymentFrequencyStr = "Quarterly";
        } else if (paymentFrequency == 1) {
            paymentFrequencyStr = "Annual";
        } else {
            paymentFrequencyStr = paymentFrequency + " payments a year";
        }

        String numberOfPaymentsStr = "";
        if (numberOfPayments != INDEFINITE_NUMBER_OF_PAYMENTS) {
            numberOfPaymentsStr = numberOfPayments + " total payments";
        }

        if (!numberOfPaymentsStr.isEmpty()) {
            return paymentAmountStr + "; " + paymentFrequencyStr + "; " + numberOfPaymentsStr;
        } else {
            return paymentAmountStr + "; " + paymentFrequencyStr;
        }

    }

    public int compareTotalPayment(PaymentStructure other) {
        return Long.compare(getTotalPayment(), other.getTotalPayment());
    }

    private long getTotalPayment() {
        return (long) paymentFrequency * (long) paymentAmount;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof PaymentStructure // instanceof handles nulls
            && paymentAmount == ((PaymentStructure) other).paymentAmount
            && paymentFrequency == ((PaymentStructure) other).paymentFrequency
            && numberOfPayments == ((PaymentStructure) other).numberOfPayments); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentAmount, paymentFrequency, numberOfPayments);
    }
}
