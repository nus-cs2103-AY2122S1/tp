package seedu.address.model.application;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Application}'s {@code Position} matches any of the keywords given.
 */
public class PositionContainsKeywordsPredicate implements Predicate<Application> {
    private final List<String> keywords;

    public PositionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Application application) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        application.getPosition().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PositionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PositionContainsKeywordsPredicate) other).keywords)); // state check
    }

}
