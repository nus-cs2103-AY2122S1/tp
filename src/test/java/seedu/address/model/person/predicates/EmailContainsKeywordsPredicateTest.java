package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class EmailContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        EmailContainsKeywordsPredicate firstPredicate = new EmailContainsKeywordsPredicate(
                firstPredicateKeywordList, false
        );
        EmailContainsKeywordsPredicate secondPredicate = new EmailContainsKeywordsPredicate(
                secondPredicateKeywordList, false
        );
        EmailContainsKeywordsPredicate thirdPredicate = new EmailContainsKeywordsPredicate(
                secondPredicateKeywordList, true
        );



        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EmailContainsKeywordsPredicate firstPredicateCopy = new EmailContainsKeywordsPredicate(
                firstPredicateKeywordList, false
        );
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        // different isFindAll -> returns false
        assertFalse(secondPredicate.equals(thirdPredicate));
    }

    @Test
    public void test_addressContainsKeywords_returnsTrue() {
        // One keyword
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate(
                Collections.singletonList("Alice"), false
        );
        assertTrue(predicate.test(new PersonBuilder().withEmail("Alice@email.com").build()));

        // Multiple keywords
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("Alice", "email"), false);
        assertTrue(predicate.test(new PersonBuilder().withEmail("Alice@email.com").build()));

        // Only one matching keyword
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("alice", "house"), false);
        assertTrue(predicate.test(new PersonBuilder().withEmail("Alice@email.com").build()));

        // Mixed-case keywords
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("aLIce", "eMaiL"), false);
        assertTrue(predicate.test(new PersonBuilder().withEmail("Alice@email.com").build()));

        // Substring keyword
        predicate = new EmailContainsKeywordsPredicate(Collections.singletonList("aLi"), false);
        assertTrue(predicate.test(new PersonBuilder().withEmail("Alice@email.com").build()));
    }

    @Test
    public void test_addressDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate(
                Collections.emptyList(), false
        );
        assertFalse(predicate.test(new PersonBuilder().withEmail("Alice@email.com").build()));

        // Non-matching keyword
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("Carol"), false);
        assertFalse(predicate.test(new PersonBuilder().withEmail("Alice@email.com").build()));

        // Keywords match name, phone, but does not match email
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList(
                "Alice", "87654321"
        ), false);
        assertFalse(predicate.test(new PersonBuilder().withName("ALice").withPhone("87654321")
                .withEmail("al@email.com").build()));
    }

    @Test
    public void test_addressContainsKeywordsIsFindAll() {
        // One keyword match -> true
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate(
                Collections.singletonList("Alice"), true
        );
        assertTrue(predicate.test(new PersonBuilder().withEmail("Alice@email.com").build()));

        // One keyword no match -> false
        predicate = new EmailContainsKeywordsPredicate(
                Collections.singletonList("haha"), true
        );
        assertFalse(predicate.test(new PersonBuilder().withEmail("Alice@email.com").build()));

        // Multiple keywords all match -> true
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("Alice", "email"), true);
        assertTrue(predicate.test(new PersonBuilder().withEmail("Alice@email.com").build()));

        // Only one matching keyword -> false
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("alice", "Carol"), true);
        assertFalse(predicate.test(new PersonBuilder().withEmail("Alice@email.com").build()));

        // Mixed-case keywords all match -> true
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("aLIce", "eMAil"), true);
        assertTrue(predicate.test(new PersonBuilder().withEmail("Alice@email.com").build()));

        // Mixed-case keywords not all match -> false
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"), true);
        assertFalse(predicate.test(new PersonBuilder().withEmail("Alice@email.com").build()));

        // Keywords match name, phone, but does not match enail -> false
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList(
                "Alice", "87654321"
        ), true);
        assertFalse(predicate.test(new PersonBuilder().withName("ALice").withPhone("87654321")
                .withEmail("al@email.com").build()));
    }
}
