package seedu.address.model.person.predicates;

import seedu.address.model.person.Person;

import java.util.List;
import java.util.function.Predicate;

public class PhoneContainsKeysPredicate implements Predicate<Person> {
    private final List<String> keys;

    public PhoneContainsKeysPredicate(List<String> keys) {
        this.keys = keys;
    }

    @Override
    public boolean test(Person person) {
        return keys.stream()
                .anyMatch(key -> person.getPhone().value.contains(String.valueOf(key)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneContainsKeysPredicate // instanceof handles nulls
                && keys.equals(((PhoneContainsKeysPredicate) other).keys)); // state check
    }
}
