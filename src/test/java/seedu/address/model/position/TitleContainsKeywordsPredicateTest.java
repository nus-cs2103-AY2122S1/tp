package seedu.address.model.position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PositionBuilder;

public class TitleContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TitleContainsKeywordsPredicate firstPredicate = new TitleContainsKeywordsPredicate(firstPredicateKeywordList);
        TitleContainsKeywordsPredicate secondPredicate = new TitleContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        TitleContainsKeywordsPredicate firstPredicateCopy =
                new TitleContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_titleContainsKeywords_returnsTrue() {
        // One keyword
        TitleContainsKeywordsPredicate predicate =
                new TitleContainsKeywordsPredicate(Collections.singletonList("Administrative"));
        assertTrue(predicate.test(new PositionBuilder().withTitle("Administrative Assistant").build()));

        // Multiple keywords
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("Administrative", "Assistant"));
        assertTrue(predicate.test(new PositionBuilder().withTitle("Administrative Assistant").build()));

        // Only one matching keyword
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("Assistant", "Bookkeeper"));
        assertTrue(predicate.test(new PositionBuilder().withTitle("Administrative Assistant").build()));

        // Mixed-case keywords
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("AdminIsTrAtiVe", "asSIsTaNt"));
        assertTrue(predicate.test(new PositionBuilder().withTitle("Administrative Assistant").build()));
    }

    @Test
    public void test_positionTitleDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TitleContainsKeywordsPredicate predicate =
                new TitleContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PositionBuilder().withTitle("Administrative Assistant").build()));

        // Non-matching keyword
        predicate = new TitleContainsKeywordsPredicate(Collections.singletonList("Bookkeeper"));
        assertFalse(predicate.test(new PositionBuilder().withTitle("Administrative Assistant").build()));

    }
}
