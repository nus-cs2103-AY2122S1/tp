package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.model.person.Person;
import seedu.address.model.person.Price;

/**
 * Tests that a {@code Person}'s {@code Price} is less than to a certain price
 */
public class PriceLessThanNumberPredicate implements Predicate<Person> {

    private Price price;

    /**
     * Creates new PriceLessThanNumberPredicate with price
     * @param price Price to be compared
     */
    public PriceLessThanNumberPredicate(Price price) {
        this.price = price;
    }

    @Override
    public boolean test(Person person) {
        return price.greaterThan(person.getPrice());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PriceLessThanNumberPredicate
                && price.equals(((PriceLessThanNumberPredicate) other).price)); // state check
    }
}
