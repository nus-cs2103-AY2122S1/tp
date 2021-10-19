package seedu.fast.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.fast.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} contains any of the queries given.
 */
public class NameContainsQueriesPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public NameContainsQueriesPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsQueryIgnoreCase(person.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsQueriesPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsQueriesPredicate) other).keywords)); // state check
    }

}
