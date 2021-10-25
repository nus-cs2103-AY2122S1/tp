package seedu.address.model.lesson;

/**
 * Represents the Outstanding Fees for the lesson in the address book.
 */
public class OutstandingFees extends Money {

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


    public OutstandingFees pay(Money amount) {
        float newOutstandingFees = getMonetaryValueInFloat() - amount.getMonetaryValueInFloat();
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
