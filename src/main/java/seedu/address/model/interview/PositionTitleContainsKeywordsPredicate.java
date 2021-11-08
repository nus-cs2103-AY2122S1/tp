package seedu.address.model.interview;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Position}'s {@code Title} of a {@code Interview} matches any of the keywords given.
 */
public class PositionTitleContainsKeywordsPredicate implements Predicate<Interview> {
    private final List<String> keywords;

    public PositionTitleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Interview interview) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(interview.getPositionTitle().fullTitle, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                // instanceof handles nulls
                || (other instanceof PositionTitleContainsKeywordsPredicate
                // keywords check
                && keywords.equals(((PositionTitleContainsKeywordsPredicate) other).keywords));
    }

}

