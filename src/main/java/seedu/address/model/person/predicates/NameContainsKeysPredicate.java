package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeysPredicate implements Predicate<Person> {
    private final List<String> keys;

    public NameContainsKeysPredicate(List<String> keys) {
        this.keys = keys;
    }

    @Override
    public boolean test(Person person) {
        return keys.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeysPredicate // instanceof handles nulls
                && keys.equals(((NameContainsKeysPredicate) other).keys)); // state check
    }

}
