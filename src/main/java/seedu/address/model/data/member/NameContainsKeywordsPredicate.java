package seedu.address.model.data.member;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.data.Data;

/**
 * Tests that a {@code Data}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate<T extends Data> implements Predicate<T> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(T data) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(data.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
