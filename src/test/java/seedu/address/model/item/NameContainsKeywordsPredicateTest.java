package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ItemBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different item -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.singletonList("Apple"));
        assertTrue(predicate.test(new ItemBuilder().withName("Apple Pie").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Banana", "Pie"));
        assertTrue(predicate.test(new ItemBuilder().withName("Apple Pie").build()));
        // Multiple word predicate
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Banana Pie"));
        assertTrue(predicate.test(new ItemBuilder().withName("Apple Banana Pie").build()));
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Banana Pie"));
        assertTrue(predicate.test(new ItemBuilder().withName("Banana Pie").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("aPplE", "pIe"));
        assertTrue(predicate.test(new ItemBuilder().withName("Apple Pie").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ItemBuilder().withName("Apple Pie").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Chocochip", "Cookie"));
        assertFalse(predicate.test(new ItemBuilder().withName("Apple Pie Banana Muffin").build()));

        // Keywords match id and tag, but does not match name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("123475", "baked"));
        assertFalse(predicate.test(new ItemBuilder().withName("Apple Pie").withId("123475").withTags("baked").build()));
    }
}
