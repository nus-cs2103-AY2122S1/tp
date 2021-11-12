package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class GithubContainsKeywordsPredicateTest {

    @Test
    public void test_containsFullKeyword_success() {
        List<String> keywords = new ArrayList<>();
        keywords.add("Jai2501");

        GithubContainsKeywordsPredicate predicate = new GithubContainsKeywordsPredicate(keywords);

        boolean test = predicate.test(new PersonBuilder().withName("Jai")
                .withGithub("Jai2501").build());
        assertTrue(test);
    }

    @Test
    public void test_containsKeyword_success() {
        List<String> keywords = new ArrayList<>();
        keywords.add("2501");

        GithubContainsKeywordsPredicate predicate = new GithubContainsKeywordsPredicate(keywords);

        boolean test = predicate.test(new PersonBuilder().withName("Jai")
                .withGithub("Jai2501").build());
        assertTrue(test);
    }

    @Test
    public void test_doesNotContainKeyword_falseOutput() {
        List<String> keywords = new ArrayList<>();
        keywords.add("Atin");

        GithubContainsKeywordsPredicate predicate = new GithubContainsKeywordsPredicate(keywords);

        boolean test = predicate.test(new PersonBuilder().withName("Jai")
                .withGithub("Jai2501").build());
        assertFalse(test);
    }

    @Test
    public void equals_twoSameObjects_success() {
        List<String> keywords = new ArrayList<>();
        keywords.add("Jai2501");

        GithubContainsKeywordsPredicate predicate = new GithubContainsKeywordsPredicate(keywords);

        assertTrue(predicate.equals(predicate));
    }

    @Test
    public void equals_twoDifferentObjects_falseOutput() {
        List<String> keywords = new ArrayList<>();
        keywords.add("Jai2501");

        GithubContainsKeywordsPredicate predicate = new GithubContainsKeywordsPredicate(keywords);
        Email email = new Email("jay@gmail.com");

        assertFalse(predicate.equals(email));
    }

    @Test
    public void equals_twoDifferentObjectsWithSameKeywords_success() {
        List<String> keywords = new ArrayList<>();
        keywords.add("Jai2501");

        GithubContainsKeywordsPredicate predicate1 = new GithubContainsKeywordsPredicate(keywords);
        GithubContainsKeywordsPredicate predicate2 = new GithubContainsKeywordsPredicate(keywords);

        assertTrue(predicate1.equals(predicate2));
    }

    @Test
    public void equals_twoDifferentObjectsWithDifferentListsButSameKeywords_success() {
        List<String> keywords1 = new ArrayList<>();
        List<String> keywords2 = new ArrayList<>();
        keywords1.add("Jai2501");
        keywords2.add("Jai2501");

        GithubContainsKeywordsPredicate predicate1 = new GithubContainsKeywordsPredicate(keywords1);
        GithubContainsKeywordsPredicate predicate2 = new GithubContainsKeywordsPredicate(keywords2);

        assertTrue(predicate1.equals(predicate2));
    }

    @Test
    public void equals_twoDifferentObjectsWithDifferentListsAndDifferentKeywords_falseOutput() {
        List<String> keywords1 = new ArrayList<>();
        List<String> keywords2 = new ArrayList<>();
        keywords1.add("Jai2501");
        keywords2.add("Atin");

        GithubContainsKeywordsPredicate predicate1 = new GithubContainsKeywordsPredicate(keywords1);
        GithubContainsKeywordsPredicate predicate2 = new GithubContainsKeywordsPredicate(keywords2);

        assertFalse(predicate1.equals(predicate2));
    }
}
