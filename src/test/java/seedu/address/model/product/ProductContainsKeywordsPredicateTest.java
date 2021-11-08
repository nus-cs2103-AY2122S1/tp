package seedu.address.model.product;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ProductBuilder;

public class ProductContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ProductContainsKeywordsPredicate firstPredicate =
                new ProductContainsKeywordsPredicate(firstPredicateKeywordList);
        ProductContainsKeywordsPredicate secondPredicate =
                new ProductContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ProductContainsKeywordsPredicate firstPredicateCopy =
                new ProductContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different Product -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_productContainsKeywords_returnsTrue() {
        // One keyword
        ProductContainsKeywordsPredicate predicate =
                new ProductContainsKeywordsPredicate(Collections.singletonList("IPad"));
        assertTrue(predicate.test(new ProductBuilder().withName("IPad Pro").build()));

        // Multiple keywords
        predicate = new ProductContainsKeywordsPredicate(Arrays.asList("Mask", "Panadol"));
        assertTrue(predicate.test(new ProductBuilder().withName("Mask Panadol").build()));

        // Only one matching keyword
        predicate = new ProductContainsKeywordsPredicate(Arrays.asList("Mask", "IPad"));
        assertTrue(predicate.test(new ProductBuilder().withName("IPad Pro").build()));

        // Mixed-case keywords
        predicate = new ProductContainsKeywordsPredicate(Arrays.asList("MaSk", "pRO"));
        assertTrue(predicate.test(new ProductBuilder().withName("IPad Pro").build()));
    }

    @Test
    public void test_productDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ProductContainsKeywordsPredicate predicate = new ProductContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ProductBuilder().withName("Mask").build()));

        // Non-matching keyword
        predicate = new ProductContainsKeywordsPredicate(Arrays.asList("Mask"));
        assertFalse(predicate.test(new ProductBuilder().withName("IPad Pro").build()));

        // Keywords match phone, email and address, but does not match Product
        predicate = new ProductContainsKeywordsPredicate(Arrays.asList("500", "5000"));
        assertFalse(predicate.test(new ProductBuilder().withName("Mask").withUnitPrice("500")
                                           .withQuantity("5000").build()));
    }
}
