package seedu.address.model.tutorialgroup;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code TutorialGroup}'s {@code GroupName} matches any of the keywords given.
 */
public class TutorialGroupContainsKeywordsPredicate implements Predicate<TutorialGroup> {
    private final List<String> keywords;

    public TutorialGroupContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(TutorialGroup tutorialGroup) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        tutorialGroup.getClassCode().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorialGroupContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TutorialGroupContainsKeywordsPredicate) other).keywords)); // state check
    }

}
