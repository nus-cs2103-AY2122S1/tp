package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Github} matches any of the keywords given.
 */
public class GithubContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public GithubContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        String githubUsername = person.getGithub().value.toLowerCase();
        return keywords.stream()
                .anyMatch(keyword -> githubUsername.contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GithubContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((GithubContainsKeywordsPredicate) other).keywords)); // state check
    }
}
