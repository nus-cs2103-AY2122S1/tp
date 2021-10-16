package dash.model.task;

import static java.util.Objects.requireNonNull;

public class CompletionStatus {
    private boolean isComplete;

    /**
     * Constructs a {@code CompletionStatus}.
     *
     * @param isComplete A valid completion status.
     */
    public CompletionStatus(boolean isComplete) {
        requireNonNull(isComplete);
        this.isComplete = isComplete;
    }

    public boolean get() {
        return this.isComplete;
    }

    public void complete() {
        this.isComplete = true;
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
