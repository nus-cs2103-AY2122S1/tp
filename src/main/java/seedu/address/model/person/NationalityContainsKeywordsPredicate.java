package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Nationality} matches any of the keywords given.
 */
public class NationalityContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public NationalityContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> person.getNationality().value.toLowerCase()
                .contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NationalityContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NationalityContainsKeywordsPredicate) other).keywords)); // state check
    }

}
