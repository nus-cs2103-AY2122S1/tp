package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Mod;

public class ModuleContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        Set<Mod> firstPredicateKeywordList = Set.of(new Mod("CS2100"));
        Set<Mod> secondPredicateKeywordList = Set.of(new Mod("CS2103T"));

        ModuleContainsKeywordsPredicate firstPredicate =
                new ModuleContainsKeywordsPredicate(firstPredicateKeywordList, null);
        ModuleContainsKeywordsPredicate secondPredicate =
                new ModuleContainsKeywordsPredicate(secondPredicateKeywordList, null);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ModuleContainsKeywordsPredicate firstPredicateCopy =
                new ModuleContainsKeywordsPredicate(firstPredicateKeywordList, null);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
