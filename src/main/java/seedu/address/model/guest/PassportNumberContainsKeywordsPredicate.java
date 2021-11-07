package seedu.address.model.guest;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Guest}'s {@code PassportNumber} matches any of the keywords given.
 */
public class PassportNumberContainsKeywordsPredicate implements Predicate<Guest> {
    private final List<String> keywords;

    public PassportNumberContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Guest guest) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(guest.getPassportNumber().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PassportNumberContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PassportNumberContainsKeywordsPredicate) other).keywords)); // state check
    }

}
