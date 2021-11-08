package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Role} matches all the keywords given.
 * <p>
 * If no keywords are provided, then there is no match.
 */
public class RoleContainsKeywordsPredicate implements Predicate<Person> {

    private final List<String> keywords;

    public RoleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        if (keywords.isEmpty()) {
            return false;
        } else {
            return keywords.stream()
                    .allMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getRole().role, keyword));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((RoleContainsKeywordsPredicate) other).keywords)); // state check
    }
}
