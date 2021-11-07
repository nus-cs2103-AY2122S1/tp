package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches the keyword given exactly.
 */
public class NameEqualKeywordPredicate implements Predicate<Person> {
    private final Name keyword;

    /**
     * Creates a NameEqualsKeywordPredicate object with the keyword.
     *
     * @param keyword The keyword to be used in the predicate.
     */
    public NameEqualKeywordPredicate(Name keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return keyword.equals(person.getName());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameEqualKeywordPredicate // instanceof handles nulls
                && keyword.equals(((NameEqualKeywordPredicate) other).keyword)); // state check
    }

    @Override
    public String toString() {
        return this.keyword.toString();
    }

}
