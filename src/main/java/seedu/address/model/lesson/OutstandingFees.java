package seedu.address.model.lesson;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents the Outstanding Fees for the lesson in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMonetaryField(String)} (String)
 */
public class OutstandingFees extends Money {
    public static final String MESSAGE_PAY_TOO_MUCH = "Payment amount exceeds current "
            + "Outstanding Fees. Invalid transcation.";

    /**
     * Constructs a {@code OutstandingFees}.
     * This constructor is called when users are modifying lesson.
     *
     * @param outstandingFees Valid Outstanding Fees.
     */
    public OutstandingFees(String outstandingFees) {
        super(outstandingFees);
    }

    /**
     * Returns true if outstandingFee value is empty.
     *
     * @return True if outstandingFee value is empty.
     */
    public boolean isEmpty() {
        return value.isEmpty();
    }

    /**
     * Returns the outstanding fees after payment.
     *
     * @param amount Amount paid.
     * @return Updated OutstandingFees.
     */
    public OutstandingFees pay(Money amount) throws IllegalValueException {
        float newOutstandingFees = getMonetaryValueInFloat() - amount.getMonetaryValueInFloat();

        if (newOutstandingFees < 0) {
            throw new IllegalValueException(MESSAGE_PAY_TOO_MUCH);
        }
        String parseValueToString = Float.toString(newOutstandingFees);
        return new OutstandingFees(parseValueToString);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OutstandingFees // instanceof handles nulls
                && value.equals(((OutstandingFees) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
