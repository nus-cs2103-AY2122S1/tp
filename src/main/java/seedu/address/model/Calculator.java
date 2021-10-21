package seedu.address.model;

/**
 * This singleton class deals with calculating the rejection rate of a specified position only.
 */
public class Calculator {
    public static final float INVALID_REJECTION_RATE = 1;

    public static float calculateRejectionRate(int totalApplied, int totalRejected) {
        if (totalApplied == 0) {
            return INVALID_REJECTION_RATE;
        }
        return (float)totalRejected / totalApplied;
    }
}
