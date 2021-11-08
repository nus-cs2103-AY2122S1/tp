package nurseybook.model.person;

import java.util.List;
import java.util.function.Predicate;

import nurseybook.commons.util.StringUtil;

/**
 * Tests that a {@code Elderly}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Elderly> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Elderly elderly) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(elderly.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
