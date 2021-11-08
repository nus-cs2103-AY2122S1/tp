package seedu.address.model.assessment;

import java.util.function.Predicate;

/**
 * Tests that a {@code Assessment}'s {@code AssessmentName} matches the keyword given.
 */
public class AssessmentNameMatchesKeywordPredicate implements Predicate<Assessment> {
    private final String keyword;

    public AssessmentNameMatchesKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Assessment assessment) {
        return new AssessmentName(keyword).equals(assessment.getAssessmentName());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssessmentNameMatchesKeywordPredicate // instanceof handles nulls
                && keyword.equals(((AssessmentNameMatchesKeywordPredicate) other).keyword)); // state check
    }

}
