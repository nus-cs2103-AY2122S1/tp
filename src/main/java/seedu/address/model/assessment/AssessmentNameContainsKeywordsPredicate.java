package seedu.address.model.assessment;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Assessment}'s {@code AssessmentName} matches any of the keywords given.
 */
public class AssessmentNameContainsKeywordsPredicate implements Predicate<Assessment> {
    private final List<String> keywords;

    public AssessmentNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Assessment assessment) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        assessment.getAssessmentName().assessmentName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssessmentNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AssessmentNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
