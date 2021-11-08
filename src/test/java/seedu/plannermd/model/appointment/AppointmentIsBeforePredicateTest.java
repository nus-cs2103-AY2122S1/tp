package seedu.plannermd.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.plannermd.testutil.appointment.AppointmentBuilder;

public class AppointmentIsBeforePredicateTest {

    @Test
    public void equals() {
        LocalDate firstDate = LocalDate.now();
        LocalDate secondDate = LocalDate.now().minusDays(1);

        AppointmentIsBeforePredicate firstPredicate =
                new AppointmentIsBeforePredicate(firstDate);
        AppointmentIsBeforePredicate secondPredicate =
                new AppointmentIsBeforePredicate(secondDate);

        // same object -> equals
        assertEquals(firstPredicate, firstPredicate);

        // Same values -> equals
        AppointmentIsBeforePredicate firstPredicateCopy =
                new AppointmentIsBeforePredicate(firstDate);
        assertEquals(firstPredicate, firstPredicateCopy);

        // Different values -> not equals
        assertNotEquals(firstPredicate, secondPredicate);

        // Different types -> not equals
        assertNotEquals(firstPredicate, firstDate);
        assertNotEquals(firstPredicate, new AppointmentIsAfterPredicate(firstDate));

        // Null -> not equals
        assertNotEquals(firstDate, null);
    }

    @Test
    public void test_nullKeywords_throwsException() {
        assertThrows(NullPointerException.class, () -> new AppointmentIsBeforePredicate(null));
    }

    @Test
    public void test_appointmentIsBefore_returnsTrue() {
        LocalDate sampleDate = LocalDate.of(2021, 10, 20);

        AppointmentIsBeforePredicate datePredicate = new AppointmentIsBeforePredicate(sampleDate);

        // Appointment is on the same date as the filter end date -> returns true
        assertTrue(datePredicate.test(new AppointmentBuilder().withDate("20/10/2021").build()));

        // Appointment is on the same date as the filter end date and has start time 23:59 -> returns true
        assertTrue(datePredicate.test(new AppointmentBuilder().withDate("20/10/2021")
                .withSession("23:59", 16).build()));

        // Appointment is on the previous day as compared to the filter end date -> returns true
        assertTrue(datePredicate.test(new AppointmentBuilder().withDate("19/10/2021").build()));
    }

    @Test
    public void test_appointmentIsNotBefore_returnsFalse() {
        LocalDate sampleDate = LocalDate.of(2021, 10, 20);

        AppointmentIsBeforePredicate datePredicate = new AppointmentIsBeforePredicate(sampleDate);

        // Appointment is on the next day as compared to the filter end date -> returns true
        assertFalse(datePredicate.test(new AppointmentBuilder().withDate("21/10/2021").build()));
        assertFalse(datePredicate.test(new AppointmentBuilder().withDate("21/10/2021")
                .withSession("00:00", 5).build()));
    }
}
