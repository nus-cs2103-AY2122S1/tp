package seedu.siasa.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.siasa.commons.exceptions.IllegalValueException;
import seedu.siasa.model.policy.Commission;
import seedu.siasa.model.policy.PaymentStructure;

/**
 * Jackson-friendly version of {@link Commission}.
 */
public class JsonAdaptedCommission {

    public final String commissionPercentage;
    public final String numberOfPayments;

    /**
     * Constructs a {@code JsonAdaptedCommission} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedCommission(@JsonProperty("commissionPercentage") String commissionPercentage,
                                 @JsonProperty("numberOfPayments") String numberOfPayments) {
        this.commissionPercentage = commissionPercentage;
        this.numberOfPayments = numberOfPayments;
    }

    /**
     * Converts a given {@code Commission} into this class for Jackson use.
     */
    public JsonAdaptedCommission(Commission source) {
        commissionPercentage = Integer.toString(source.commissionPercentage);
        numberOfPayments = Integer.toString(source.numberOfPayments);
    }

    /**
     * Converts this Jackson-friendly adapted Commission object into the model's {@code Commission} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Commission.
     */
    public Commission toModelType(PaymentStructure paymentStructure) throws IllegalValueException {
        try {
            int commissionPercentageInt = Integer.parseInt(commissionPercentage);
            int numberOfPaymentsInt = Integer.parseInt(numberOfPayments);
            if (!Commission.isValidCommission(commissionPercentageInt, numberOfPaymentsInt, paymentStructure)) {
                throw new IllegalValueException(Commission.MESSAGE_CONSTRAINTS);
            }

            return new Commission(commissionPercentageInt, numberOfPaymentsInt);
        } catch (IllegalValueException | NumberFormatException e) {
            throw new IllegalValueException(e.getMessage());
        }
    }
}
