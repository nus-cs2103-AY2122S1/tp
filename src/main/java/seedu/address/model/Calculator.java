package seedu.address.model;

/**
 * This singleton class deals with calculating the rejection rate of a specified position only.
 */
public class Calculator {
    public static int calculateRejRate(int totalApplied, int totalRejected) {
        if (totalApplied == 0) {
            return -1;
        }
        return totalRejected / totalApplied;
    }
}
