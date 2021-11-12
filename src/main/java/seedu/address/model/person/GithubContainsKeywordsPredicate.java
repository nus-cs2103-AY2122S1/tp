package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Github} matches any of the keywords given.
 */
public class GithubContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    /**
     * Creates a GithubContainsKeywordsPredicate which contains
     * a list of {@code keywords}.
     *
     * @param keywords of Github username.
     */
    public GithubContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Test if the input {@code person} matches the predicate
     * by comparing the Github usernames.
     *
     * @param person to be tested against.
     * @return Boolean representation of whether the
     * person is the same as the predicate.
     */
    @Override
    public boolean test(Person person) {
        String githubUsername = person.getGithub().value.toLowerCase();
        return keywords.stream()
                .anyMatch(keyword -> githubUsername.contains(keyword.toLowerCase()));
    }

    /**
     * Method to compare two GithubContainsKeywordsPredicate objects.
     *
     * @param other is the object that is going to be compared
     *              to the GithubContainsKeywordsPredicate object that called this method.
     * @return boolean representation of whether the GithubContainsKeywordsPredicate
     * object is equal to the other object passed as parameter.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GithubContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((GithubContainsKeywordsPredicate) other).keywords)); // state check
    }
}
