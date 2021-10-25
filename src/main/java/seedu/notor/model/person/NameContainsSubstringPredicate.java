package seedu.notor.model.person;

import java.util.Locale;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} contains the substring given.
 */
public class NameContainsSubstringPredicate implements Predicate<Person> {
    private final String query;

    public NameContainsSubstringPredicate(String query) {
        this.query = query;
    }

    @Override
    public boolean test(Person person) {
        return person.getName().fullName.toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsSubstringPredicate // instanceof handles nulls
                && query.equals(((NameContainsSubstringPredicate) other).query)); // state check
    }

}
