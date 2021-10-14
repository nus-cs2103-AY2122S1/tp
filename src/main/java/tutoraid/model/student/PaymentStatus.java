package tutoraid.model.student;

/**
 * Represents a student's payment status in TutorAid.
 */
public class PaymentStatus {

    public final boolean hasPaid;

    /**
     * Constructs a {@code PaymentStatus}.
     *
     * @param hasPaid Payment status for the current month
     */
    public PaymentStatus(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }

    @Override
    public String toString() {
        if (hasPaid) {
            return "Paid for the current month";
        } else {
            return "Has not paid for the current month";
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PaymentStatus // instanceof handles nulls
                && hasPaid == ((PaymentStatus) other).hasPaid); // state check
    }

    @Override
    public int hashCode() {
        return hasPaid ? 1 : 0;
    }
}
