package seedu.track2gather.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.track2gather.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Case Number} equals any of the keywords given.
 */
public class CaseNumberEqualsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public CaseNumberEqualsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> person.getCaseNumber().value.equals(keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CaseNumberEqualsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((CaseNumberEqualsKeywordsPredicate) other).keywords)); // state check
    }
}
