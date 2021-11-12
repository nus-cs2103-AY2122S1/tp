package seedu.address.model.person.predicates;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Phone} contains a substring equivalent to the numbers given.
 */
public class PhoneContainsNumberPredicate implements Predicate<Person> {
    private final List<String> numbers;
    private final boolean isFindAll;

    /**
     * Constructor for the PhoneContainsNumberPredicate class
     * @param numbers The numbers to compare against the {@code Person}'s {@code phone}
     * @param isFindAll True if all numbers need to match, false otherwise
     */
    public PhoneContainsNumberPredicate(List<String> numbers, boolean isFindAll) {
        requireNonNull(numbers);
        this.numbers = numbers;
        this.isFindAll = isFindAll;
    }

    @Override
    public boolean test(Person person) {
        if (isFindAll) {
            return numbers.stream()
                    .allMatch(number -> person.getPhone().value.contains(number));
        }
        return numbers.stream()
                .anyMatch(number -> person.getPhone().value.contains(number));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneContainsNumberPredicate // instanceof handles nulls
                && numbers.equals(((PhoneContainsNumberPredicate) other).numbers))
                && isFindAll == ((PhoneContainsNumberPredicate) other).isFindAll; // state check
    }
}
