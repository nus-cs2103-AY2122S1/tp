package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TagContainsKeywordsPredicateTest {

    @Test
    public void test_validInput_success() {
        List<String> keywords = new ArrayList<>();
        keywords.add("hackathon");

        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(keywords);

        boolean test = predicate.test(new PersonBuilder()
                .withName("Jai").withTags("hackathon", "CS2103T").build());
        assertTrue(test);
    }

    @Test
    public void test_invalidInput_falseOutput() {
        List<String> keywords = new ArrayList<>();
        keywords.add("CS2103T");

        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(keywords);

        boolean test = predicate.test(new PersonBuilder()
                .withName("Jai").withTags("hackathon").build());
        assertFalse(test);
    }

    @Test
    public void equals_twoSameObjects_success() {
        List<String> keywords = new ArrayList<>();
        keywords.add("CS2103T");

        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(keywords);

        assertTrue(predicate.equals(predicate));
    }

    @Test
    public void equals_twoDifferentObjects_falseOutput() {
        List<String> keywords = new ArrayList<>();
        keywords.add("CS2103T");

        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(keywords);
        Email email = new Email("jay@gmail.com");

        assertFalse(predicate.equals(email));
    }

    @Test
    public void equals_twoDifferentObjectsWithSameKeywords_success() {
        List<String> keywords = new ArrayList<>();
        keywords.add("CS2103T");

        TagContainsKeywordsPredicate predicate1 = new TagContainsKeywordsPredicate(keywords);
        TagContainsKeywordsPredicate predicate2 = new TagContainsKeywordsPredicate(keywords);

        assertTrue(predicate1.equals(predicate2));
    }

    @Test
    public void equals_twoDifferentObjectsWithDifferentListsButSameKeywords_success() {
        List<String> keywords1 = new ArrayList<>();
        List<String> keywords2 = new ArrayList<>();
        keywords1.add("CS2103T");
        keywords2.add("CS2103T");

        TagContainsKeywordsPredicate predicate1 = new TagContainsKeywordsPredicate(keywords1);
        TagContainsKeywordsPredicate predicate2 = new TagContainsKeywordsPredicate(keywords2);

        assertTrue(predicate1.equals(predicate2));
    }

    @Test
    public void equals_twoDifferentObjectsWithDifferentListsAndDifferentKeywords_falseOutput() {
        List<String> keywords1 = new ArrayList<>();
        List<String> keywords2 = new ArrayList<>();
        keywords1.add("CS2103T");
        keywords2.add("Hackathon");

        TagContainsKeywordsPredicate predicate1 = new TagContainsKeywordsPredicate(keywords1);
        TagContainsKeywordsPredicate predicate2 = new TagContainsKeywordsPredicate(keywords2);

        assertFalse(predicate1.equals(predicate2));
    }
}
