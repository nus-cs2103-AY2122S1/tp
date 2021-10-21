package dash.model.task;

import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code TaskDescription} matches any of the keywords given.
 */
public class CompletionStatusContainsKeywordsPredicate implements Predicate<Task> {
    private final boolean value;

    public CompletionStatusContainsKeywordsPredicate(boolean value) {
        this.value = value;
    }

    @Override
    public boolean test(Task task) {
        return task.getCompletionStatus().get() == this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompletionStatusContainsKeywordsPredicate // instanceof handles nulls
                && value == (((CompletionStatusContainsKeywordsPredicate) other).value)); // state check
    }

}
