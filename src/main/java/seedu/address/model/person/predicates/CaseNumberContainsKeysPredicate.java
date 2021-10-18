package seedu.address.model.person.predicates;

import seedu.address.model.person.Person;

import java.util.List;
import java.util.function.Predicate;

public class CaseNumberContainsKeysPredicate implements Predicate<Person> {
    private final List<String> keys;

    public CaseNumberContainsKeysPredicate(List<String> keys) {
        this.keys = keys;
    }

    @Override
    public boolean test(Person person) {
        return keys.stream()
                .anyMatch(key -> person.getCaseNumber().value.equals(String.valueOf(key)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CaseNumberContainsKeysPredicate // instanceof handles nulls
                && keys.equals(((CaseNumberContainsKeysPredicate) other).keys)); // state check
    }
}
