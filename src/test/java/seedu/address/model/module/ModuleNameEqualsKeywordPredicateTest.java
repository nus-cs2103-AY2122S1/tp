package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ModuleBuilder;

class ModuleNameEqualsKeywordPredicateTest {

    @Test
    void equals() {
        String firstPredicateKeyword = "CS2103";
        String secondPredicateKeyword = "CS2100";

        ModuleNameEqualsKeywordPredicate firstPredicate = new ModuleNameEqualsKeywordPredicate(firstPredicateKeyword);
        ModuleNameEqualsKeywordPredicate secondPredicate = new ModuleNameEqualsKeywordPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ModuleNameEqualsKeywordPredicate firstPredicateCopy =
                new ModuleNameEqualsKeywordPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals("CS2103"));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different module names -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_moduleNameEqualsKeywords_returnsTrue() {
        // correct keyword
        ModuleNameEqualsKeywordPredicate predicate = new ModuleNameEqualsKeywordPredicate("CS2103");
        assertTrue(predicate.test(new ModuleBuilder().withName("CS2103").build()));
    }

    @Test
    public void test_moduleNameDoesNotEqualKeywords_returnsFalse() {
        // No keywords
        ModuleNameEqualsKeywordPredicate predicate = new ModuleNameEqualsKeywordPredicate("");
        assertFalse(predicate.test(new ModuleBuilder().withName("CS2103").build()));

        // Non-matching keyword
        ModuleNameEqualsKeywordPredicate predicate1 = new ModuleNameEqualsKeywordPredicate("CS2100");
        assertFalse(predicate1.test(new ModuleBuilder().withName("CS2103").build()));
    }
}
