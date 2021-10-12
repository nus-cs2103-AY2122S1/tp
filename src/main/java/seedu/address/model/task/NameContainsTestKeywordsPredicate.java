package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsTestKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public NameContainsTestKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getName().taskName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsTestKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsTestKeywordsPredicate) other).keywords)); // state check
    }

}
