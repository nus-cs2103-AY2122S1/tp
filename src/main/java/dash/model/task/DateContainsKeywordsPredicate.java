package dash.model.task;

import java.util.List;
import java.util.function.Predicate;

import dash.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code TaskDescription} matches any of the keywords given.
 */
public class DateContainsKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public DateContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        boolean dateCheck = keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getTaskDate().toDateString(), keyword));
        boolean timeCheck = keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getTaskDate().toTimeString(), keyword));
        return dateCheck || timeCheck;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DateContainsKeywordsPredicate) other).keywords)); // state check
    }

}
