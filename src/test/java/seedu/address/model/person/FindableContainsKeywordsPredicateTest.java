package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class FindableContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        FindableContainsKeywordsPredicate firstPredicate =
                new FindableContainsKeywordsPredicate(firstPredicateKeywordList);
        FindableContainsKeywordsPredicate secondPredicate =
                new FindableContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FindableContainsKeywordsPredicate firstPredicateCopy =
                new FindableContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_findableContainsKeywords_returnsTrue() {
        // One keyword
        FindableContainsKeywordsPredicate predicate =
                new FindableContainsKeywordsPredicate(Collections.singletonList("Marina"));
        assertTrue(predicate.test(new PersonBuilder().withName("Marina Bay Sands").build()));

        // Multiple keywords
        predicate = new FindableContainsKeywordsPredicate(Arrays.asList("Bayfront", "Ave"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("10 Bayfront Ave").build()));

        // Only one matching keyword
        predicate = new FindableContainsKeywordsPredicate(Arrays.asList("amazing", "meh"));
        assertTrue(predicate.test(new PersonBuilder().withReview("amazing").build()));

        // Mixed-case keywords
        predicate = new FindableContainsKeywordsPredicate(Arrays.asList("harbourfront", "St"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("1 HarbourFront Walk").build()));

        // Number keywords
        predicate = new FindableContainsKeywordsPredicate(Arrays.asList("886", "908"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("6688868").build()));
    }

    @Test
    public void test_findableDoesNotContainKeywords_returnsFalse() {
        // NAME
        // Zero keywords
        FindableContainsKeywordsPredicate predicate = new FindableContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Marina Bay Sands").build()));

        // Non-matching keyword
        predicate = new FindableContainsKeywordsPredicate(Arrays.asList("VivoCity"));
        assertFalse(predicate.test(new PersonBuilder().withName("Marina Bay Sands").build()));

        // ADDRESS
        // Zero keywords
        predicate = new FindableContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withAddress("10 Bayfront Ave").build()));

        // Non-matching keyword
        predicate = new FindableContainsKeywordsPredicate(Arrays.asList("Walk"));
        assertFalse(predicate.test(new PersonBuilder().withAddress("10 Bayfront Ave").build()));

        // PHONE
        // Zero keywords
        predicate = new FindableContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withPhone("6376860").build()));

        // Non-matching keyword
        predicate = new FindableContainsKeywordsPredicate(Arrays.asList("908"));
        assertFalse(predicate.test(new PersonBuilder().withPhone("6376860").build()));

        // EMAIL
        // Zero keywords
        predicate = new FindableContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withEmail("marinabaysands@example.com").build()));

        // Non-matching keyword
        predicate = new FindableContainsKeywordsPredicate(Arrays.asList("gmail"));
        assertFalse(predicate.test(new PersonBuilder().withEmail("marinabaysands@example.com").build()));

        // REVIEW
        // Zero keywords
        predicate = new FindableContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withReview("amazing").build()));

        // Non-matching keyword
        predicate = new FindableContainsKeywordsPredicate(Arrays.asList("awesome"));
        assertFalse(predicate.test(new PersonBuilder().withReview("amazing").build()));
    }
}
