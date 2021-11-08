package seedu.address.model.member;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Member}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Member> {
    private final List<String> keywords;

    /**
     * Constructs {@code NameContainsKeywordsPredicate} with input {@code List<String>}.
     */
    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Overrides the test method.
     * Evaluates this predicate on the given argument.
     */
    @Override
    public boolean test(Member member) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(member.getName().fullName, keyword));
    }

    /**
     * Overrides the equals method.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
