package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventNamePredicateTest {

    @Test
    public void equals() {
        EventNamePredicate firstPredicate = new EventNamePredicate("first");
        EventNamePredicate secondPredicate = new EventNamePredicate("second");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EventNamePredicate firstPredicateCopy = new EventNamePredicate("first");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        EventNamePredicate predicate = new EventNamePredicate("Dinner");
        assertTrue(predicate.test(new Event(new EventName("Dinner"), new EventDate("2021-11-11"))));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        EventNamePredicate predicate = new EventNamePredicate("");
        assertFalse(predicate.test(new Event(new EventName("Dinner"), new EventDate("2021-11-11"))));

        // Non-matching keyword
        predicate = new EventNamePredicate("Lunch");
        assertFalse(predicate.test(new Event(new EventName("Dinner"), new EventDate("2021-11-11"))));
    }
}
