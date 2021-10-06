package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.ContainsKeywordsPredicate.PersonField;
import seedu.address.testutil.PersonBuilder;

public class ContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ContainsKeywordsPredicate firstPredicate = new ContainsKeywordsPredicate(firstPredicateKeywordList,
                PersonField.NAME);
        ContainsKeywordsPredicate secondPredicate = new ContainsKeywordsPredicate(secondPredicateKeywordList,
                PersonField.NAME);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ContainsKeywordsPredicate firstPredicateCopy = new ContainsKeywordsPredicate(firstPredicateKeywordList,
                PersonField.NAME);
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
        ContainsKeywordsPredicate predicate = new ContainsKeywordsPredicate(Collections.singletonList("Alice"),
                PersonField.NAME);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new ContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"), PersonField.NAME);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new ContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"), PersonField.NAME);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new ContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"), PersonField.NAME);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ContainsKeywordsPredicate predicate = new ContainsKeywordsPredicate(Collections.emptyList(), PersonField.NAME);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new ContainsKeywordsPredicate(Arrays.asList("Carol"), PersonField.NAME);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new ContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"),
                PersonField.NAME);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345").withEmail("alice@email.com")
                .withAddress("Main Street").build()));
    }
}
