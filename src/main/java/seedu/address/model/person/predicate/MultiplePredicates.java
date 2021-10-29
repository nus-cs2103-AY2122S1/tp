package seedu.address.model.person.predicate;

import java.util.ArrayList;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Combine two or more Predicates together.
 */
public class MultiplePredicates implements Predicate<Person> {

    private final ArrayList<Predicate<Person>> predicateList;

    public MultiplePredicates(ArrayList<Predicate<Person>> predicateList) {
        this.predicateList = predicateList;
    }

    @Override
    public boolean test(Person person) {
        return predicateList
                .stream()
                .allMatch(predicate -> predicate.test(person));
    }

    @Override
    public boolean equals(Object other) {
        ArrayList<Predicate<Person>> firstPredicate = this.predicateList;
        ArrayList<Predicate<Person>> secondPredicate = ((MultiplePredicates) other).predicateList;

        //Careful!! O(n^2) operation. Can be improved to O(nlogn) with sorting.
        boolean equalList = (firstPredicate.size() == secondPredicate.size())
                        && firstPredicate.containsAll(secondPredicate)
                        && secondPredicate.containsAll(firstPredicate);

        return other == this // short circuit if same object
                || (other instanceof MultiplePredicates // instanceof handles nulls
                && equalList); // state check
    }

}
