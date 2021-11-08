package dash.model.task;

import static java.util.Objects.requireNonNull;

import dash.commons.util.StringUtil;

public class CompletionStatus {

    public static final String MESSAGE_CONSTRAINTS = "Completion status should not be blank. "
            + "It should either be true or false (case insensitive). "
            + "A full list of available formats can be seen under the Help tab.";
    private static final String COMPLETE_ARG = "true";
    private static final String INCOMPLETE_ARG = "false";
    private final boolean isComplete;


    /**
     * Constructs a {@code CompletionStatus}.
     *
     * @param isComplete A valid completion status.
     */
    public CompletionStatus(boolean isComplete) {
        requireNonNull(isComplete);
        this.isComplete = isComplete;
    }

    /**
     * Returns positive integer if a given completion status is valid (either true or false).
     *
     * @param completionStatusString A valid formatted string of the completion status.
     * @return positive integer corresponding to truth value, negative integer if invalid format.
     */
    public static int isValidCompletionStatus(String completionStatusString) {
        if (StringUtil.containsWordIgnoreCase(completionStatusString, COMPLETE_ARG)) {
            return 0;
        } else if (StringUtil.containsWordIgnoreCase(completionStatusString, INCOMPLETE_ARG)) {
            return 1;
        } else {
            return -1;
        }
    }

    public boolean get() {
        return this.isComplete;
    }

    @Override
    public String toString() {
        return "" + isComplete;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompletionStatus // instanceof handles nulls
                && isComplete == ((CompletionStatus) other).isComplete); // state check
    }
}
