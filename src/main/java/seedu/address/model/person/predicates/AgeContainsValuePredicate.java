package seedu.address.model.person.predicates;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Age} matches the value given.
 */
public class AgeContainsValuePredicate implements Predicate<Person> {
    private final List<String> ages;
    private final boolean isFindAll;

    /**
     * Constructor for the AgeContainsValuePredicate class
     * @param ages The keywords to compare against the {@code Person}'s {@code Age}
     * @param isFindAll True if all keywords need to match, false otherwise
     */
    public AgeContainsValuePredicate(List<String> ages, boolean isFindAll) {
        requireNonNull(ages);
        this.ages = ages;
        this.isFindAll = isFindAll;
    }

    @Override
    public boolean test(Person person) {
        if (isFindAll) {
            return ages.stream()
                    .allMatch(age -> person.getAge().value.equals(age));
        }
        return ages.stream()
                .anyMatch(age -> person.getAge().value.equals(age));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AgeContainsValuePredicate // instanceof handles nulls
                && ages.equals(((AgeContainsValuePredicate) other).ages))
                && isFindAll == ((AgeContainsValuePredicate) other).isFindAll; // state check
    }
}

