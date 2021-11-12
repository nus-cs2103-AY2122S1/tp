package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class InterestsContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        InterestContainsKeywordsPredicate firstPredicate = new InterestContainsKeywordsPredicate(
                firstPredicateKeywordList, false
        );
        InterestContainsKeywordsPredicate secondPredicate = new InterestContainsKeywordsPredicate(
                secondPredicateKeywordList, false
        );
        InterestContainsKeywordsPredicate thirdPredicate = new InterestContainsKeywordsPredicate(
                secondPredicateKeywordList, true
        );



        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        InterestContainsKeywordsPredicate firstPredicateCopy = new InterestContainsKeywordsPredicate(
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
        InterestContainsKeywordsPredicate predicate = new InterestContainsKeywordsPredicate(
                Collections.singletonList("Alice"), false
        );
        assertTrue(predicate.test(new PersonBuilder().withInterest("Alice").build()));

        // Multiple keywords
        predicate = new InterestContainsKeywordsPredicate(Arrays.asList("Alice", "house", "run"), false);
        assertTrue(predicate.test(new PersonBuilder().withInterest("Alice house", "running").build()));

        // Only one matching keyword
        predicate = new InterestContainsKeywordsPredicate(Arrays.asList("eat", "house"), false);
        assertTrue(predicate.test(new PersonBuilder().withInterest("eating", "sleeping").build()));

        // Mixed-case keywords
        predicate = new InterestContainsKeywordsPredicate(Arrays.asList("eaTInG", "sLeEpIng"), false);
        assertTrue(predicate.test(new PersonBuilder().withInterest("eating", "sleeping").build()));

        // Substring keyword
        predicate = new InterestContainsKeywordsPredicate(Collections.singletonList("eep"), false);
        assertTrue(predicate.test(new PersonBuilder().withInterest("eating", "sleeping").build()));
    }

    @Test
    public void test_addressDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        InterestContainsKeywordsPredicate predicate = new InterestContainsKeywordsPredicate(
                Collections.emptyList(), false
        );
        assertFalse(predicate.test(new PersonBuilder().withInterest("eating", "sleeping").build()));

        // Non-matching keyword
        predicate = new InterestContainsKeywordsPredicate(Arrays.asList("Carol"), false);
        assertFalse(predicate.test(new PersonBuilder().withInterest("eating", "sleeping").build()));

        // Keywords match name, phone, email, but does not match interest
        predicate = new InterestContainsKeywordsPredicate(Arrays.asList(
                "Alice", "87654321", "alice@email.com"
        ), false);
        assertFalse(predicate.test(new PersonBuilder().withName("ALice").withPhone("87654321")
                .withEmail("alice@email.com").withInterest("eating", "sleeping").build()));
    }

    @Test
    public void test_addressContainsKeywordsIsFindAll() {
        // One keyword match -> true
        InterestContainsKeywordsPredicate predicate = new InterestContainsKeywordsPredicate(
                Collections.singletonList("eating"), true
        );
        assertTrue(predicate.test(new PersonBuilder().withInterest("eating", "sleeping").build()));

        // One keyword no match -> false
        predicate = new InterestContainsKeywordsPredicate(
                Collections.singletonList("haha"), true
        );
        assertFalse(predicate.test(new PersonBuilder().withInterest("eating", "sleeping").build()));

        // Multiple keywords all match -> true
        predicate = new InterestContainsKeywordsPredicate(Arrays.asList("eat", "sleep"), true);
        assertTrue(predicate.test(new PersonBuilder().withInterest("eating", "sleeping").build()));

        // Only one matching keyword -> false
        predicate = new InterestContainsKeywordsPredicate(Arrays.asList("eat", "Carol"), true);
        assertFalse(predicate.test(new PersonBuilder().withInterest("eating", "sleeping").build()));

        // Mixed-case keywords all match -> true
        predicate = new InterestContainsKeywordsPredicate(Arrays.asList("eaTINg", "sLeEpInG"), true);
        assertTrue(predicate.test(new PersonBuilder().withInterest("eating", "sleeping").build()));

        // Mixed-case keywords not all match -> false
        predicate = new InterestContainsKeywordsPredicate(Arrays.asList("sLeEpInG", "bOB"), true);
        assertFalse(predicate.test(new PersonBuilder().withInterest("eating", "sleeping").build()));

        // Keywords match name, phone, email, but does not match interests -> false
        predicate = new InterestContainsKeywordsPredicate(Arrays.asList(
                "Alice", "87654321", "alice@email.com"
        ), true);
        assertFalse(predicate.test(new PersonBuilder().withName("ALice").withPhone("87654321")
                .withEmail("alice@email.com").withInterest("eating", "sleeping").build()));
    }
}

