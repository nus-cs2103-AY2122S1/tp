package seedu.address.model.person.predicates;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s fields matches any of the respective fields provided
 */
public class CombinedPredicate implements Predicate<Person> {
    private final List<Predicate<Person>> predicates;
    private final boolean isFindAll;

    /**
     * Constructor for the CombinedPredicate class
     * @param predicates The predicates to compare against the {@code Person}
     * @param isFindAll True if all predicates need to return true, false otherwise
     */
    public CombinedPredicate(ArrayList<Predicate<Person>> predicates, boolean isFindAll) {
        requireNonNull(predicates);
        this.predicates = predicates;
        this.isFindAll = isFindAll;
    }

    @Override
    public boolean test(Person person) {
        if (isFindAll) {
            return predicates.stream()
                    .allMatch(predicate -> predicate.test(person));
        }
        return predicates.stream()
                .anyMatch(predicate -> predicate.test(person));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CombinedPredicate // instanceof handles nulls
                && predicates.equals(((CombinedPredicate) other).predicates))
                && isFindAll == ((CombinedPredicate) other).isFindAll; // state check
    }
}
