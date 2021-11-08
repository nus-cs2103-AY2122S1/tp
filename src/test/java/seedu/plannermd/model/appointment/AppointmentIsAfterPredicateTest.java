package seedu.plannermd.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.plannermd.testutil.appointment.AppointmentBuilder;

public class AppointmentIsAfterPredicateTest {

    @Test
    public void equals() {
        LocalDate firstDate = LocalDate.now();
        LocalDate secondDate = LocalDate.now().minusDays(1);

        AppointmentIsAfterPredicate firstPredicate =
                new AppointmentIsAfterPredicate(firstDate);
        AppointmentIsAfterPredicate secondPredicate =
                new AppointmentIsAfterPredicate(secondDate);

        // same object -> equals
        assertEquals(firstPredicate, firstPredicate);

        // Same values -> equals
        AppointmentIsAfterPredicate firstPredicateCopy =
                new AppointmentIsAfterPredicate(firstDate);
        assertEquals(firstPredicate, firstPredicateCopy);

        // Different values -> not equals
        assertNotEquals(firstPredicate, secondPredicate);

        // Different types -> not equals
        assertNotEquals(firstPredicate, firstDate);
        assertNotEquals(firstPredicate, new AppointmentIsBeforePredicate(firstDate));

        // Null -> not equals
        assertNotEquals(firstDate, null);
    }

    @Test
    public void test_nullKeywords_throwsException() {
        assertThrows(NullPointerException.class, () -> new AppointmentIsAfterPredicate((LocalDate) null));

        assertThrows(NullPointerException.class, () -> new AppointmentIsAfterPredicate((LocalDateTime) null));
    }

    @Test
    public void test_appointmentIsAfter_returnsTrue() {
        LocalDate sampleDate = LocalDate.of(2021, 10, 20);
        LocalDateTime sampleDateTime = LocalDateTime.of(2021, 10, 20, 18, 30);

        AppointmentIsAfterPredicate datePredicate = new AppointmentIsAfterPredicate(sampleDate);
        AppointmentIsAfterPredicate dateTimePredicate = new AppointmentIsAfterPredicate(sampleDateTime);

        // Appointment is on the same date as the filter start date -> returns true
        assertTrue(datePredicate.test(new AppointmentBuilder().withDate("20/10/2021").build()));

        // Appointment is on the same date as the filter start date with same start time -> returns true
        assertTrue(datePredicate.test(new AppointmentBuilder().withDate("20/10/2021")
                .withSession("00:00", 5).build()));
        assertTrue(dateTimePredicate.test(new AppointmentBuilder().withDate("20/10/2021")
                .withSession("18:30", 15).build()));

        // Appointment is on the same date as the filter start time but is at a later timing -> returns true
        assertTrue(dateTimePredicate.test(new AppointmentBuilder().withDate("20/10/2021")
                .withSession("18:31", 5).build()));

        // Appointment is on the next day compared to the filter start date -> returns true
        assertTrue(datePredicate.test(new AppointmentBuilder().withDate("21/10/2021").build()));
    }

    @Test
    public void test_appointmentIsNotAfter_returnsFalse() {
        LocalDate sampleDate = LocalDate.of(2021, 10, 20);
        LocalDateTime sampleDateTime = LocalDateTime.of(2021, 10, 20, 18, 30);

        AppointmentIsAfterPredicate datePredicate = new AppointmentIsAfterPredicate(sampleDate);
        AppointmentIsAfterPredicate dateTimePredicate = new AppointmentIsAfterPredicate(sampleDateTime);

        // Appointment is on the previous date
        assertFalse(datePredicate.test(new AppointmentBuilder().withDate("19/10/2021").build()));

        // Appointment is on the same date as the filter start date with an earlier start time -> returns false
        assertFalse(datePredicate.test(new AppointmentBuilder().withDate("19/10/2021")
                .withSession("23:59", 5).build()));
        assertFalse(dateTimePredicate.test(new AppointmentBuilder().withDate("20/10/2021")
                .withSession("18:29", 15).build()));
    }
}
