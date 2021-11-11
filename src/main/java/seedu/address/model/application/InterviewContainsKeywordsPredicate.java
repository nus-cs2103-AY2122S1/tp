package seedu.address.model.application;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Application}'s {@code Interview} matches any of the keywords given.
 */
public class InterviewContainsKeywordsPredicate implements Predicate<Application> {
    private final List<String> keywords;

    public InterviewContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Application application) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        application.getInterviewDateAndTime().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InterviewContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((InterviewContainsKeywordsPredicate) other).keywords)); // state check
    }

}
