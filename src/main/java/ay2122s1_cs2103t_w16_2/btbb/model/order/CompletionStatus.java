package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents the completion status of an order in BTBB.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class CompletionStatus implements Comparable<CompletionStatus> {
    public static final String MESSAGE_CONSTRAINTS = "Completion Status should only be either 'yes' or 'no'.";
    private final boolean isFinished;

    /**
     * Constructs a {@code CompletionStatus} object.
     *
     * @param isFinished A valid completionStatus input, either 'yes' or 'no'.
     */
    public CompletionStatus(String isFinished) {
        requireNonNull(isFinished);
        checkArgument(isValidCompletionStatus(isFinished), MESSAGE_CONSTRAINTS);
        this.isFinished = isFinished.equals("yes");
    }

    /**
     * Constructs a {@code CompletionStatus} object.
     * For internal use, does not execute check if input is valid.
     *
     * @param isFinished Order is finished (true) or not (false).
     */
    public CompletionStatus(boolean isFinished) {
        this.isFinished = isFinished;
    }

    /**
     * Returns true if a given string is a valid completionStatus.
     *
     * @param test String input to check.
     * @return boolean of whether completionStatus is valid.
     */
    public static boolean isValidCompletionStatus(String test) {
        return test.equalsIgnoreCase("yes") || test.equalsIgnoreCase("no");
    }

    /**
     * Converts completionStatus object into its String representation.
     *
     * @return String representation of completionStatus.
     */
    @Override
    public String toString() {
        return isFinished ? "yes" : "no";
    }

    @Override
    public int compareTo(CompletionStatus other) {
        return Boolean.compare(isFinished, other.isFinished);
    }

    public String getDisplayMessage() {
        return isFinished ? "Finished" : "Not Finished";
    }

    public boolean getIsFinished() {
        return isFinished;
    }

    /**
     * Returns true if object and this completionStatus are the same.
     *
     * @param other object to compare this completionStatus to.
     * @return boolean of whether completionStatus and other object match.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompletionStatus // instanceof handles nulls
                && isFinished == ((CompletionStatus) other).isFinished); // state check
    }

    /**
     * Implements hashcode for completionStatus objects.
     *
     * @return hashcode of completionStatus.
     */
    @Override
    public int hashCode() {
        return Boolean.hashCode(isFinished);
    }
}
