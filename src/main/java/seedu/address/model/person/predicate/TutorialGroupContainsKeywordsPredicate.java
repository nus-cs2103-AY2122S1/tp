package seedu.address.model.person.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Tutorial Group} matches any of the keywords given.
 */
public class TutorialGroupContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TutorialGroupContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return !keywords.isEmpty()
                && keywords.stream()
                .allMatch(keyword -> !keyword.trim().isEmpty() && person.getTutorialGroup().value.trim().toLowerCase()
                .contains(keyword.trim().toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorialGroupContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TutorialGroupContainsKeywordsPredicate) other).keywords)); // state check
    }

}
