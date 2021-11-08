package seedu.siasa.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.siasa.commons.exceptions.IllegalValueException;
import seedu.siasa.model.policy.PaymentStructure;

/**
 * Jackson-friendly version of {@link PaymentStructure}.
 */
public class JsonAdaptedPaymentStructure {

    // payment amount in cents
    private final String paymentAmount;
    // number of payments in a year
    private final String paymentFrequency;
    private final String numberOfPayments;

    /**
     * Constructs a {@code JsonAdaptedPaymentStructure} with the given
     * {@code paymentAmount, paymentFrequency, numberOfPayments}.
     */
    @JsonCreator
    public JsonAdaptedPaymentStructure(@JsonProperty("paymentAmount") String paymentAmount,
                                       @JsonProperty("paymentFrequency") String paymentFrequency,
                                       @JsonProperty("numberOfPayments") String numberOfPayments) {
        this.paymentAmount = paymentAmount;
        this.paymentFrequency = paymentFrequency;
        this.numberOfPayments = numberOfPayments;
    }

    /**
     * Converts a given {@code PaymentStructure} into this class for Jackson use.
     */
    public JsonAdaptedPaymentStructure(PaymentStructure source) {
        paymentAmount = Integer.toString(source.paymentAmount);
        paymentFrequency = Integer.toString(source.paymentFrequency);
        numberOfPayments = Integer.toString(source.numberOfPayments);
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code PaymentStructure} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted PaymentStructure.
     */
    public PaymentStructure toModelType() throws IllegalValueException {
        try {
            int paymentAmountInt = Integer.parseInt(paymentAmount);
            int paymentFrequencyInt = Integer.parseInt(paymentFrequency);
            int numberOfPaymentsInt = Integer.parseInt(numberOfPayments);
            if (!PaymentStructure.isValidPaymentStructure(paymentAmountInt, paymentFrequencyInt, numberOfPaymentsInt)) {
                throw new IllegalValueException(PaymentStructure.MESSAGE_CONSTRAINTS);
            }

            return new PaymentStructure(paymentAmountInt, paymentFrequencyInt, numberOfPaymentsInt);
        } catch (IllegalValueException | NumberFormatException e) {
            throw new IllegalValueException(e.getMessage());
        }
    }
}

