package seedu.address.model;

/**
 * This singleton class deals with calculating the rejection rate of a specified position only.
 */
public class Calculator {
<<<<<<< HEAD
    public static final int INVALID_REJECTION_RATE = -1;

    /**
     * Calculates the rejection rate for a given position.
     *
     * @param totalApplied Total number of applicants that applied for the position.
     * @param totalRejected Total number of applicants rejection for the position.
     * @return An integer representing the rejection rate.
     */
=======
    public static final float INVALID_REJECTION_RATE = 1;

>>>>>>> master
    public static float calculateRejectionRate(int totalApplied, int totalRejected) {
        if (totalApplied == 0) {
            return INVALID_REJECTION_RATE;
        }
        return (float)totalRejected / totalApplied;
    }
}
