package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Default predicate that always return true
 */
public class TruePredicate implements Predicate<Person> {

    @Override
    public boolean test(Person person) {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TruePredicate); // state check
    }
}
