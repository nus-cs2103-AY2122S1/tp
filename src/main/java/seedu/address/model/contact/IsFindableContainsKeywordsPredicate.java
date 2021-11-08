package seedu.address.model.contact;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Contact}'s {@code Name} matches any of the keywords given.
 */
public class IsFindableContainsKeywordsPredicate implements Predicate<Contact> {
    private final List<String> keywords;

    public IsFindableContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Contact contact) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsAndIgnoreCase(contact.getName().fullName, keyword)
                        || StringUtil.containsAndIgnoreCase(contact.getAddress().value, keyword)
                        || StringUtil.containsAndIgnoreCase(contact.getEmail().value, keyword)
                        || StringUtil.containsAndIgnoreCase(contact.getPhone().value, keyword)
                        || StringUtil.containsAndIgnoreCase(contact.getReview().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsFindableContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((IsFindableContainsKeywordsPredicate) other).keywords)); // state check
    }

}
