package seedu.address.model;

/**
 * This singleton class deals with calculating the rejection rate of a specified position only.
 */
public class Calculator {
    /**
     * Calculates the rejection rate for a given position.
     *
     * @param totalApplied Total number of applicants that applied for the position.
     * @param totalRejected Total number of applicants rejection for the position.
     * @return An integer representing the rejection rate.
     */
    public static int calculateRejRate(int totalApplied, int totalRejected) {
        if (totalApplied == 0) {
            return -1;
        }
        return totalRejected / totalApplied;
    }
}
