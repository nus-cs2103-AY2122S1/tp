package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class EventDateTimePredicateTest {
    private final EventDateTimePredicate firstPredicate = new EventDateTimePredicate(
            new ArrayList<>(List.of("2020-11-11", "1000")));
    private final EventDateTimePredicate firstPredicateCopy = new EventDateTimePredicate(
            new ArrayList<>(List.of("2020-11-11", "1000")));
    private final EventDateTimePredicate secondPredicate = new EventDateTimePredicate(
            new ArrayList<>(List.of("2020-11-12", "1001")));
    private final EventDateTimePredicate thirdPredicate = new EventDateTimePredicate(
            new ArrayList<>(List.of("2020-11-12")));

    @Test
    public void testEquals() {

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // different objects, same values -> returns true
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // date and time present, different values -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        // one Event with date only  -> returns false
        assertFalse(firstPredicate.equals(thirdPredicate));
    }
}
