package seedu.address.model.application;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Application}'s {@code Requirements} matches any of the keywords given.
 */
public class RequirementsContainsKeywordsPredicate implements Predicate<Application> {
    private final List<String> keywords;

    public RequirementsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Application application) {

        for (Requirement requirement: application.getRequirements()) {
            boolean found = keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(requirement.value, keyword));
            if (found) {
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RequirementsContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((RequirementsContainsKeywordsPredicate) other).keywords)); // state check
    }

}
