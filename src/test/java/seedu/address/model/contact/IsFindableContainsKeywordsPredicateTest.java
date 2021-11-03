package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ContactBuilder;

public class IsFindableContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        IsFindableContainsKeywordsPredicate firstPredicate =
                new IsFindableContainsKeywordsPredicate(firstPredicateKeywordList);
        IsFindableContainsKeywordsPredicate secondPredicate =
                new IsFindableContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        IsFindableContainsKeywordsPredicate firstPredicateCopy =
                new IsFindableContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different contact -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_findableContainsKeywords_returnsTrue() {
        // One keyword
        IsFindableContainsKeywordsPredicate predicate =
                new IsFindableContainsKeywordsPredicate(Collections.singletonList("Marina"));
        assertTrue(predicate.test(new ContactBuilder().withName("Marina Bay Sands").build()));

        // Multiple keywords
        predicate = new IsFindableContainsKeywordsPredicate(Arrays.asList("Bayfront", "Ave"));
        assertTrue(predicate.test(new ContactBuilder().withAddress("10 Bayfront Ave").build()));

        // Only one matching keyword
        predicate = new IsFindableContainsKeywordsPredicate(Arrays.asList("amazing", "meh"));
        assertTrue(predicate.test(new ContactBuilder().withReview("amazing").build()));

        // Mixed-case keywords
        predicate = new IsFindableContainsKeywordsPredicate(Arrays.asList("harbourfront", "St"));
        assertTrue(predicate.test(new ContactBuilder().withAddress("1 HarbourFront Walk").build()));

        // Number keywords
        predicate = new IsFindableContainsKeywordsPredicate(Arrays.asList("886", "908"));
        assertTrue(predicate.test(new ContactBuilder().withPhone("6688868").build()));
    }

    @Test
    public void test_findableDoesNotContainKeywords_returnsFalse() {
        // NAME
        // Zero keywords
        IsFindableContainsKeywordsPredicate predicate =
            new IsFindableContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ContactBuilder().withName("Marina Bay Sands").build()));

        // Non-matching keyword
        predicate = new IsFindableContainsKeywordsPredicate(Arrays.asList("VivoCity"));
        assertFalse(predicate.test(new ContactBuilder().withName("Marina Bay Sands").build()));

        // ADDRESS
        // Zero keywords
        predicate = new IsFindableContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ContactBuilder().withAddress("10 Bayfront Ave").build()));

        // Non-matching keyword
        predicate = new IsFindableContainsKeywordsPredicate(Arrays.asList("Walk"));
        assertFalse(predicate.test(new ContactBuilder().withAddress("10 Bayfront Ave").build()));

        // PHONE
        // Zero keywords
        predicate = new IsFindableContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ContactBuilder().withPhone("6376860").build()));

        // Non-matching keyword
        predicate = new IsFindableContainsKeywordsPredicate(Arrays.asList("908"));
        assertFalse(predicate.test(new ContactBuilder().withPhone("6376860").build()));

        // EMAIL
        // Zero keywords
        predicate = new IsFindableContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ContactBuilder().withEmail("marinabaysands@example.com").build()));

        // Non-matching keyword
        predicate = new IsFindableContainsKeywordsPredicate(Arrays.asList("gmail"));
        assertFalse(predicate.test(new ContactBuilder().withEmail("marinabaysands@example.com").build()));

        // REVIEW
        // Zero keywords
        predicate = new IsFindableContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ContactBuilder().withReview("amazing").build()));

        // Non-matching keyword
        predicate = new IsFindableContainsKeywordsPredicate(Arrays.asList("awesome"));
        assertFalse(predicate.test(new ContactBuilder().withReview("amazing").build()));
    }
}
