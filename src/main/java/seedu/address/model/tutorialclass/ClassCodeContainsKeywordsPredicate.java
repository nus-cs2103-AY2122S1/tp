package seedu.address.model.tutorialclass;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Student}'s {@code Name} matches any of the keywords given.
 */
public class ClassCodeContainsKeywordsPredicate implements Predicate<TutorialClass> {
    private final List<String> keywords;

    public ClassCodeContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(TutorialClass tutorialClass) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        tutorialClass.getClassCode().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassCodeContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ClassCodeContainsKeywordsPredicate) other).keywords)); // state check
    }

}
