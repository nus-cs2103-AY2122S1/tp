package seedu.address.model.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPositions.ADMIN_ASSISTANT;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InterviewBuilder;

public class PositionTitleContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PositionTitleContainsKeywordsPredicate firstPredicate =
                new PositionTitleContainsKeywordsPredicate(firstPredicateKeywordList);
        PositionTitleContainsKeywordsPredicate secondPredicate =
                new PositionTitleContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        PositionTitleContainsKeywordsPredicate firstPredicateCopy =
                new PositionTitleContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_positionTitleContainsKeywords_returnsTrue() {
        // One keyword
        PositionTitleContainsKeywordsPredicate predicate =
                new PositionTitleContainsKeywordsPredicate(Collections.singletonList("Administrative"));
        assertTrue(predicate.test(new InterviewBuilder().withPosition(ADMIN_ASSISTANT).build()));

        // Multiple keywords
        predicate = new PositionTitleContainsKeywordsPredicate(Arrays.asList("Administrative", "Assistant"));
        assertTrue(predicate.test(new InterviewBuilder().withPosition(ADMIN_ASSISTANT).build()));

        // Only one matching keyword
        predicate = new PositionTitleContainsKeywordsPredicate(Arrays.asList("Assistant", "Bookkeeper"));
        assertTrue(predicate.test(new InterviewBuilder().withPosition(ADMIN_ASSISTANT).build()));

        // Mixed-case keywords
        predicate = new PositionTitleContainsKeywordsPredicate(Arrays.asList("AdminIsTrAtiVe", "asSIsTaNt"));
        assertTrue(predicate.test(new InterviewBuilder().withPosition(ADMIN_ASSISTANT).build()));
    }

    @Test
    public void test_positionTitleDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PositionTitleContainsKeywordsPredicate predicate =
                new PositionTitleContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new InterviewBuilder().withPosition(ADMIN_ASSISTANT).build()));

        // Non-matching keyword
        predicate = new PositionTitleContainsKeywordsPredicate(Collections.singletonList("Bookkeeper"));
        assertFalse(predicate.test(new InterviewBuilder().withPosition(ADMIN_ASSISTANT).build()));

    }
}
