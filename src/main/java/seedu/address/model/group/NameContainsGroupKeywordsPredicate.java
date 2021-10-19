package seedu.address.model.group;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Student}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsGroupKeywordsPredicate implements Predicate<Group> {
    private final List<String> keywords;

    public NameContainsGroupKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Group group) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(group.getName().name, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsGroupKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsGroupKeywordsPredicate) other).keywords)); // state check
    }

}
