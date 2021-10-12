package seedu.address.model.reservation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.enums.EnumTypeOfCheck;
import seedu.address.model.person.Phone;

class ListContainsReservationPredicateTest {
    private LocalDate date1 = LocalDate.parse("2021-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    private LocalDate date2 = LocalDate.parse("2021-02-02", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    private LocalTime time = LocalTime.parse("1200", DateTimeFormatter.ofPattern("HHmm"));
    private EnumTypeOfCheck typeOfCheck = EnumTypeOfCheck.DateTime;
    private Phone phone = new Phone("98765432");
    private int numberOfPeople = 5;

    @Test
    void testEquals() {
        ListContainsReservationPredicate predicate1 = new ListContainsReservationPredicate(date1, time, typeOfCheck);
        ListContainsReservationPredicate predicate2 = new ListContainsReservationPredicate(date2, time, typeOfCheck);

        // same object -> returns true
        assertTrue(predicate1.equals(predicate1));

        // same values -> returns true
        ListContainsReservationPredicate predicate1Copy =
                new ListContainsReservationPredicate(date1, time, typeOfCheck);
        assertTrue(predicate1.equals(predicate1Copy));

        // different types -> returns false
        assertFalse(predicate1.equals(1));

        // null -> returns false
        assertFalse(predicate1.equals(null));

        // different predicate -> returns false
        assertFalse(predicate1.equals(predicate2));
    }

    @Test
    void test_reservationDateTimeMatch_returnsTrue() {
        Reservation reservation = new Reservation(phone, numberOfPeople,
                LocalDateTime.parse("2021-01-01 1900", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
        ListContainsReservationPredicate predicate;

        // Matching date and time in query containing both date and time
        predicate = new ListContainsReservationPredicate(LocalDate.parse("2021-01-01"), LocalTime.parse("19:00"),
                EnumTypeOfCheck.DateTime);
        assertTrue(predicate.test(reservation));

        // Matching date in query containing only date
        predicate = new ListContainsReservationPredicate(LocalDate.parse("2021-01-01"), LocalTime.parse("23:00"),
                EnumTypeOfCheck.Date);
        assertTrue(predicate.test(reservation));
    }

    @Test
    void test_reservationDateTimeNoMatch_returnsFalse() {
        Reservation reservation = new Reservation(phone, numberOfPeople,
                LocalDateTime.parse("2021-01-01 1900", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
        ListContainsReservationPredicate predicate;

        // Not matching date and time in query containing both date and time
        predicate = new ListContainsReservationPredicate(LocalDate.parse("2021-12-12"), LocalTime.parse("23:00"),
                EnumTypeOfCheck.DateTime);
        assertFalse(predicate.test(reservation));

        // Not matching date and matching time in query containing both date and time
        predicate = new ListContainsReservationPredicate(LocalDate.parse("2021-12-12"), LocalTime.parse("19:00"),
                EnumTypeOfCheck.DateTime);
        assertFalse(predicate.test(reservation));

        // Matching date and not matching time in query containing both date and time
        predicate = new ListContainsReservationPredicate(LocalDate.parse("2021-01-01"), LocalTime.parse("23:00"),
                EnumTypeOfCheck.DateTime);
        assertFalse(predicate.test(reservation));

        // Not matching date in query containing only date
        predicate = new ListContainsReservationPredicate(LocalDate.parse("2021-01-02"), LocalTime.parse("19:00"),
                EnumTypeOfCheck.Date);
        assertFalse(predicate.test(reservation));
    }
}
