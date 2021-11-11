package seedu.address.model.application;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Application}'s {@code Status} matches any of the keywords given.
 */
public class StatusContainsKeywordsPredicate implements Predicate<Application> {
    private final List<String> keywords;

    public StatusContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Application application) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        application.getStatus().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StatusContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((StatusContainsKeywordsPredicate) other).keywords)); // state check
    }

}
