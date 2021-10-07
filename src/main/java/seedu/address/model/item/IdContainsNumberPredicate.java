package seedu.address.model.item;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Item}'s {@code Id} matches any of the keywords given.
 * Only output items with id that matches exactly with the query
 */
public class IdContainsNumberPredicate implements Predicate<Item> {
    private final List<String> keywords;

    public IdContainsNumberPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Item item) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(item.getId(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IdContainsNumberPredicate // instanceof handles nulls
                && keywords.equals(((IdContainsNumberPredicate) other).keywords)); // state check
    }

}
