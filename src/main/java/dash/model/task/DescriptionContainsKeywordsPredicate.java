package dash.model.task;

import java.util.List;
import java.util.function.Predicate;

import dash.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code TaskDescription} matches any of the keywords given.
 */
public class DescriptionContainsKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public DescriptionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getTaskDescription().description, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DescriptionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DescriptionContainsKeywordsPredicate) other).keywords)); // state check
    }

}
