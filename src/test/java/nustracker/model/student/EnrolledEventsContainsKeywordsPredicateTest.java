package nustracker.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nustracker.model.event.EventName;

public class EnrolledEventsContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        EventName firstEvent = new EventName("first event");
        EventName secondEvent = new EventName("seconds event");

        EnrolledEventsContainsKeywordsPredicate firstPredicate =
                new EnrolledEventsContainsKeywordsPredicate(firstEvent);
        EnrolledEventsContainsKeywordsPredicate secondPredicate =
                new EnrolledEventsContainsKeywordsPredicate(secondEvent);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EnrolledEventsContainsKeywordsPredicate firstPredicateCopy =
                new EnrolledEventsContainsKeywordsPredicate(firstEvent);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
