package seedu.address.model.application;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Application}'s {@code Deadline} matches any of the keywords given.
 */
public class DeadlineContainsKeywordsPredicate implements Predicate<Application> {
    private final List<String> keywords;

    public DeadlineContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Application application) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        application.getDeadline().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeadlineContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DeadlineContainsKeywordsPredicate) other).keywords)); // state check
    }

}
