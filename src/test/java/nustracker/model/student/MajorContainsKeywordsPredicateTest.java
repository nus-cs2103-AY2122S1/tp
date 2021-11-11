package nustracker.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class MajorContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<Major> firstPredicateKeywordList = Collections.singletonList("cs").stream().map(
            x -> new Major(x)).collect(Collectors.toUnmodifiableList());
        List<Major> secondPredicateKeywordList = Arrays.asList("cs", "is").stream().map(
            x -> new Major(x)).collect(Collectors.toUnmodifiableList());

        MajorContainsKeywordsPredicate firstPredicate = new MajorContainsKeywordsPredicate(firstPredicateKeywordList);
        MajorContainsKeywordsPredicate secondPredicate = new MajorContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MajorContainsKeywordsPredicate firstPredicateCopy = new MajorContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
