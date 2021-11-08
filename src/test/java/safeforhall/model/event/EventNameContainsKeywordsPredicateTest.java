package safeforhall.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import safeforhall.testutil.EventBuilder;

public class EventNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        EventNameContainsKeywordsPredicate firstPredicate =
                new EventNameContainsKeywordsPredicate(firstPredicateKeywordList);
        EventNameContainsKeywordsPredicate secondPredicate =
                new EventNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EventNameContainsKeywordsPredicate firstPredicateCopy =
                new EventNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    //TODO
    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        EventNameContainsKeywordsPredicate predicate =
                new EventNameContainsKeywordsPredicate(Collections.singletonList("Football"));
        assertTrue(predicate.test(new EventBuilder().withEventName("Football").build()));

        // Multiple keywords
        predicate = new EventNameContainsKeywordsPredicate(Arrays.asList("Football", "Swim"));
        assertTrue(predicate.test(new EventBuilder().withEventName("Football Swim").build()));

        // Only one matching keyword
        predicate = new EventNameContainsKeywordsPredicate(Arrays.asList("Football", "Basketball"));
        assertTrue(predicate.test(new EventBuilder().withEventName("Football Swim").build()));

        // Mixed-case keywords
        predicate = new EventNameContainsKeywordsPredicate(Arrays.asList("fooTbAll", "swIM"));
        assertTrue(predicate.test(new EventBuilder().withEventName("Football Swim").build()));
    }


    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        EventNameContainsKeywordsPredicate predicate = new EventNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EventBuilder().withEventName("Football").build()));

        // Non-matching keyword
        predicate = new EventNameContainsKeywordsPredicate(Arrays.asList("Swim"));
        assertFalse(predicate.test(new EventBuilder().withEventName("Football Basketball").build()));

        // Keywords match phone, email, but does not match name
        predicate = new EventNameContainsKeywordsPredicate(Arrays.asList("20-10-2021", "Field", "20"));
        assertFalse(predicate.test(new EventBuilder().withEventName("Football Training").withEventDate("20-10-2021")
                .withVenue("Field").withCapacity("20").build()));
    }

}
