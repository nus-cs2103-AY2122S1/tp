package seedu.address.model.facility;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class LocationContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredKeywordList = Collections.singletonList("first");
        List<String> secondPredKeywordList = Arrays.asList("first", "second");

        LocationContainsKeywordsPredicate firstPred = new LocationContainsKeywordsPredicate(firstPredKeywordList);
        LocationContainsKeywordsPredicate secondPred = new LocationContainsKeywordsPredicate(secondPredKeywordList);

        // same object -> return true
        assertTrue(firstPred.equals(firstPred));

        // same values -> return true
        LocationContainsKeywordsPredicate firstPredCopy = new LocationContainsKeywordsPredicate(firstPredKeywordList);
        assertTrue(firstPred.equals(firstPredCopy));

        // different types -> return false
        assertFalse(firstPred.equals(1));

        // null -> returns false
        assertFalse(firstPred.equals(null));

        // different facility -> returns false
        assertFalse(firstPred.equals(secondPred));

    }
}
