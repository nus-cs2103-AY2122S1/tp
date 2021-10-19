package seedu.address.model.person.predicates;

import seedu.address.model.person.Person;

import java.util.List;
import java.util.function.Predicate;

public class ShnPeriodStartContainsKeysPredicate implements Predicate<Person> {
    private final List<String> keys;

    public ShnPeriodStartContainsKeysPredicate(List<String> keys) {
        this.keys = keys;
    }

    @Override
    public boolean test(Person person) {
        return keys.stream()
                .anyMatch(key -> person.getShnPeriod()
                .map(sh -> sh.startDate.toString().contains(key)).orElse(false));
    }

}
