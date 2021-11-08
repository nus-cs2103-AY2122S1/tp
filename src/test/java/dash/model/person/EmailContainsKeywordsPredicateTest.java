package dash.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import dash.testutil.PersonBuilder;

public class EmailContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("dummymail1@email.com");
        List<String> secondPredicateKeywordList = Arrays.asList("dummymail1@email.com", "dummymail2@email.com");

        EmailContainsKeywordsPredicate firstPredicate = new EmailContainsKeywordsPredicate(
                firstPredicateKeywordList);
        EmailContainsKeywordsPredicate secondPredicate = new EmailContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EmailContainsKeywordsPredicate firstPredicateCopy = new EmailContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        // One keyword
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate(Collections.singletonList(
                "dummymail1@email.com"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("dummymail1@email.com").build()));
    }

    @Test
    public void test_emailDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate(Arrays.asList(
                "dummymail1@email.com"));
        assertFalse(predicate.test(new PersonBuilder().withEmail("dummymail2@email.com").build()));

        // Keyword only matches part of email address
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("dummymail"));
        assertFalse(predicate.test(new PersonBuilder().withEmail("dummymail1@email.com").build()));

        // Keywords match name, phone and address, but does not match email
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("Alice", "12345", "alice@email.com", "Main",
                "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withAddress("Main Street").build()));
    }
}
