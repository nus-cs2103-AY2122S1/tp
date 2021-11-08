package seedu.address.model.person.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Social Handle} matches any of the keywords given.
 */
public class SocialHandleContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public SocialHandleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return !keywords.isEmpty()
                && keywords.stream()
                .map(keyword -> keyword.split(":", 2))
                .allMatch(keyword -> person.getSocialHandles().stream()
                .anyMatch(handle -> handle.isSamePlatform(keyword[0]) && handle.value.trim().toLowerCase()
                .contains(keyword[1].trim().toLowerCase())));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SocialHandleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((SocialHandleContainsKeywordsPredicate) other).keywords)); // state check
    }

}
