package nustracker.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class YearContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<Year> firstPredicateKeywordList = Collections.singletonList("1").stream().map(
            x -> new Year(x)).collect(Collectors.toUnmodifiableList());
        List<Year> secondPredicateKeywordList = Arrays.asList("1", "2").stream().map(
            x -> new Year(x)).collect(Collectors.toUnmodifiableList());

        YearContainsKeywordsPredicate firstPredicate = new YearContainsKeywordsPredicate(firstPredicateKeywordList);
        YearContainsKeywordsPredicate secondPredicate = new YearContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        YearContainsKeywordsPredicate firstPredicateCopy = new YearContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicates -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
