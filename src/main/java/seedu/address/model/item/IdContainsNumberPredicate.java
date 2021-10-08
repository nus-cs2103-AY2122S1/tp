package seedu.address.model.item;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Item}'s {@code Id} matches any of the keywords given.
 * Only output items with id that matches exactly with the query
 */
public class IdContainsNumberPredicate implements Predicate<Item> {
    private final List<String> keynumbers;

    public IdContainsNumberPredicate(List<String> keynumbers) {
        this.keynumbers = keynumbers;
    }

    @Override
    public boolean test(Item item) {
        return keynumbers.stream()
                .anyMatch(keynumbers -> StringUtil.containsWordIgnoreCase(item.getId(), keynumbers));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IdContainsNumberPredicate // instanceof handles nulls
                && keynumbers.equals(((IdContainsNumberPredicate) other).keynumbers)); // state check
    }

}
