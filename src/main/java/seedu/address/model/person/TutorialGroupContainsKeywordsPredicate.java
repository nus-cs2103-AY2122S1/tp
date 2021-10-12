package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

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
        return keywords.stream()
                .anyMatch(keyword -> person.getTutorialGroup().value.toLowerCase()
                .contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorialGroupContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TutorialGroupContainsKeywordsPredicate) other).keywords)); // state check
    }

}
