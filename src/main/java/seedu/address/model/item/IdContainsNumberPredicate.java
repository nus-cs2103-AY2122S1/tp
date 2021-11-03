package seedu.address.model.item;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * Tests that a {@code Item}'s {@code Id} matches any of the keywords given.
 * Only output items with id that matches exactly with the query
 */
public class IdContainsNumberPredicate implements Predicate<Item> {
    private final Collection<Integer> keynumbers;

    public IdContainsNumberPredicate(Collection<Integer> keynumbers) {
        this.keynumbers = keynumbers;
    }

    @Override
    public boolean test(Item item) {
        return keynumbers.stream()
                .anyMatch(keynumber -> keynumber.equals(item.getId()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IdContainsNumberPredicate // instanceof handles nulls
                && keynumbers.equals(((IdContainsNumberPredicate) other).keynumbers)); // state check
    }

}
