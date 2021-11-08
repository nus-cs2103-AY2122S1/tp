package seedu.docit.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.docit.testutil.PatientBuilder;

public class PatientContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PatientContainsKeywordsPredicate firstPredicate =
            new PatientContainsKeywordsPredicate(firstPredicateKeywordList);
        PatientContainsKeywordsPredicate secondPredicate =
            new PatientContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PatientContainsKeywordsPredicate firstPredicateCopy =
            new PatientContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different patient -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        PatientContainsKeywordsPredicate predicate =
            new PatientContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new PatientContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new PatientContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new PatientContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PatientContainsKeywordsPredicate predicate = new PatientContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PatientBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new PatientContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PatientBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and docit, but does not match name
        predicate = new PatientContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PatientBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
