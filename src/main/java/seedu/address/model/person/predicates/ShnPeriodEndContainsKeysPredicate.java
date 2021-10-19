package seedu.address.model.person.predicates;

import seedu.address.model.person.Person;

import java.util.List;
import java.util.function.Predicate;

public class ShnPeriodEndContainsKeysPredicate implements Predicate<Person> {
    private final List<String> keys;

    public ShnPeriodEndContainsKeysPredicate(List<String> keys) {
        this.keys = keys;
    }

    @Override
    public boolean test(Person person) {
        return keys.stream()
                .anyMatch(key -> person.getShnPeriod()
                .map(sh -> sh.endDate.toString().contains(key)).orElse(false));
    }

}