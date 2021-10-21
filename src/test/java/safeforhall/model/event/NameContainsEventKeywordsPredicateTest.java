package safeforhall.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import safeforhall.testutil.EventBuilder;

public class NameContainsEventKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsEventKeywordsPredicate firstPredicate =
                new NameContainsEventKeywordsPredicate(firstPredicateKeywordList);
        NameContainsEventKeywordsPredicate secondPredicate =
                new NameContainsEventKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsEventKeywordsPredicate firstPredicateCopy =
                new NameContainsEventKeywordsPredicate(firstPredicateKeywordList);
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
        NameContainsEventKeywordsPredicate predicate =
                new NameContainsEventKeywordsPredicate(Collections.singletonList("Football"));
        assertTrue(predicate.test(new EventBuilder().withEventName("Football").build()));

        // Multiple keywords
        predicate = new NameContainsEventKeywordsPredicate(Arrays.asList("Football", "Swim"));
        assertTrue(predicate.test(new EventBuilder().withEventName("Football Swim").build()));

        // Only one matching keyword
        predicate = new NameContainsEventKeywordsPredicate(Arrays.asList("Football", "Basketball"));
        assertTrue(predicate.test(new EventBuilder().withEventName("Football Swim").build()));

        // Mixed-case keywords
        predicate = new NameContainsEventKeywordsPredicate(Arrays.asList("fooTbAll", "swIM"));
        assertTrue(predicate.test(new EventBuilder().withEventName("Football Swim").build()));
    }


    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsEventKeywordsPredicate predicate = new NameContainsEventKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EventBuilder().withEventName("Football").build()));

        // Non-matching keyword
        predicate = new NameContainsEventKeywordsPredicate(Arrays.asList("Swim"));
        assertFalse(predicate.test(new EventBuilder().withEventName("Football Basketball").build()));

        // Keywords match phone, email, but does not match name
        predicate = new NameContainsEventKeywordsPredicate(Arrays.asList("20-10-2021", "Field", "20"));
        assertFalse(predicate.test(new EventBuilder().withEventName("Football Training").withEventDate("20-10-2021")
                .withVenue("Field").withCapacity("20").build()));
    }

}
