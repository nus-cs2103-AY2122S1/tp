package seedu.address.model.reservation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import javafx.collections.transformation.FilteredList;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.testutil.PersonBuilder;

class PersonContainsReservationPredicateTest {

    @Test
    void testEquals() {
        int numberOfPeople = 5;
        LocalDateTime dateTime = LocalDateTime.parse("2020-10-10T19:00");

        Phone phone1 = new Phone("99999999");
        Reservation reservation1 = new Reservation(phone1, numberOfPeople, dateTime);
        ReservationList reservationList1 = new ReservationList();
        reservationList1.add(reservation1);
        FilteredList<Reservation> filteredList1 = new FilteredList<>(reservationList1.asUnmodifiableObservableList());
        PersonContainsReservationPredicate predicate1 = new PersonContainsReservationPredicate(filteredList1);

        Phone phone2 = new Phone("88888888");
        Reservation reservation2 = new Reservation(phone2, numberOfPeople, dateTime);
        ReservationList reservationList2 = new ReservationList();
        reservationList2.add(reservation2);
        FilteredList<Reservation> filteredList2 = new FilteredList<>(reservationList2.asUnmodifiableObservableList());
        PersonContainsReservationPredicate predicate2 = new PersonContainsReservationPredicate(filteredList2);

        // same object -> returns true
        assertTrue(predicate1.equals(predicate1));

        // same values -> returns true
        PersonContainsReservationPredicate predicate1Copy = new PersonContainsReservationPredicate(filteredList1);
        assertTrue(predicate1.equals(predicate1Copy));

        // different types -> returns false
        assertFalse(predicate1.equals(1));

        // null -> returns false
        assertFalse(predicate1.equals(null));

        // different predicate -> returns false
        assertFalse(predicate1.equals(predicate2));
    }

    @Test
    void test_personPhoneNumberMatchReservation_returnTrue() {
        int numberOfPeople = 5;
        LocalDateTime dateTime = LocalDateTime.parse("2020-10-10T19:00");

        Phone phone = new Phone("99999999");
        Reservation reservation = new Reservation(phone, numberOfPeople, dateTime);
        ReservationList reservationList = new ReservationList();
        reservationList.add(reservation);
        FilteredList<Reservation> filteredList = new FilteredList<>(reservationList.asUnmodifiableObservableList());
        PersonContainsReservationPredicate predicate = new PersonContainsReservationPredicate(filteredList);
        Person person = new PersonBuilder().withPhone("99999999").build();

        // Person's phone number matches the phone number in the reservation
        assertTrue(predicate.test(person));
    }

    @Test
    void test_personPhoneNumberDoesNotMatchReservation_returnFalse() {
        int numberOfPeople = 5;
        LocalDateTime dateTime = LocalDateTime.parse("2020-10-10T19:00");

        Phone phone = new Phone("99999999");
        Reservation reservation = new Reservation(phone, numberOfPeople, dateTime);
        ReservationList reservationList = new ReservationList();
        reservationList.add(reservation);
        FilteredList<Reservation> filteredList = new FilteredList<>(reservationList.asUnmodifiableObservableList());
        PersonContainsReservationPredicate predicate = new PersonContainsReservationPredicate(filteredList);
        Person person = new PersonBuilder().withPhone("88888888").build();

        // Person's phone number does not match the phone number in the reservation
        assertFalse(predicate.test(person));
    }
}
