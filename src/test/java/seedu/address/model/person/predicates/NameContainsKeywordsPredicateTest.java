package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(
                firstPredicateKeywordList, false
        );
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(
                secondPredicateKeywordList, false
        );

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy = new NameContainsKeywordsPredicate(
                firstPredicateKeywordList, false
        );
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(
                Collections.singletonList("Alice"), false
        );
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"), false);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"), false);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"), false);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList(), false);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Carol"), false);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList(
                "87654321", "alice@email.com", "Main", "Street"
        ), false);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("87654321")
                .withEmail("alice@email.com").build()));
    }

    @Test
    public void test_addressContainsKeywordsIsFindAll() {
        // One keyword match -> true
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(
                Collections.singletonList("Alice"), true
        );
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").build()));

        // One keyword no match -> false
        predicate = new NameContainsKeywordsPredicate(
                Collections.singletonList("haha"), true
        );
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Multiple keywords all match -> true
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "27"), true);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice the Legend27").build()));

        // Only one matching keyword -> false
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("the", "Carol"), true);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice the Legend27").build()));

        // Mixed-case keywords all match -> true
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("aLIce", "LeGeNd27"), true);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice the Legend27").build()));

        // Mixed-case keywords not all match -> false
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("LeGeNd28", "bOB"), true);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice the Legend27").build()));

        // Keywords match phone, email, but does not match name -> false
        predicate = new NameContainsKeywordsPredicate(Arrays.asList(
                "87654321", "alice@email.com"
        ), true);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice the Legend27").withPhone("87654321")
                .withEmail("alice@email.com").build()));
    }
}
