package seedu.plannermd.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class AppointmentIsAfterPredicateTest {

    @Test
    public void equals() {
        LocalDateTime firstDate = LocalDateTime.now();
        LocalDateTime secondDate = LocalDateTime.now().minusHours(1);

        AppointmentIsAfterPredicate firstPredicate =
                new AppointmentIsAfterPredicate(firstDate);
        AppointmentIsAfterPredicate secondPredicate =
                new AppointmentIsAfterPredicate(secondDate);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // Same values -> return true
        AppointmentIsAfterPredicate firstPredicateCopy =
                new AppointmentIsAfterPredicate(firstDate);
        assertEquals(firstPredicate, firstPredicateCopy);

        // Different values -> return false
        assertNotEquals(firstPredicate, secondPredicate);

        // Different types -> return false
        assertNotEquals(firstPredicate, firstDate);
        assertNotEquals(firstPredicate, new AppointmentIsBeforePredicate(firstDate));

        // Null -> returns false
        assertNotEquals(firstDate, null);
    }

    @Test
    public void test_nullKeywords_throwsException() {
        assertThrows(NullPointerException.class, () -> new AppointmentIsAfterPredicate(null));
    }
}