package seedu.address.model.reservation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalReservation.ALICE_RESERVATION;
import static seedu.address.testutil.TypicalReservation.BENSON_RESERVATION;

import org.junit.jupiter.api.Test;

import javafx.collections.transformation.FilteredList;
import seedu.address.model.person.customer.Customer;
import seedu.address.testutil.CustomerBuilder;

class CustomerContainsReservationPredicateTest {

    @Test
    void testEquals() {
        ReservationList reservationList1 = new ReservationList();
        reservationList1.add(ALICE_RESERVATION);
        FilteredList<Reservation> filteredList1 = new FilteredList<>(reservationList1.asUnmodifiableObservableList());
        CustomerContainsReservationPredicate predicate1 = new CustomerContainsReservationPredicate(filteredList1);

        ReservationList reservationList2 = new ReservationList();
        reservationList2.add(BENSON_RESERVATION);
        FilteredList<Reservation> filteredList2 = new FilteredList<>(reservationList2.asUnmodifiableObservableList());
        CustomerContainsReservationPredicate predicate2 =
                new CustomerContainsReservationPredicate(filteredList2);

        // same object -> returns true
        assertTrue(predicate1.equals(predicate1));

        // same values -> returns true
        CustomerContainsReservationPredicate predicate1Copy =
                new CustomerContainsReservationPredicate(filteredList1);
        assertTrue(predicate1.equals(predicate1Copy));

        // different types -> returns false
        assertFalse(predicate1.equals(1));

        // null -> returns false
        assertFalse(predicate1.equals(null));

        // different predicate -> returns false
        assertFalse(predicate1.equals(predicate2));
    }

    @Test
    void test_customerPhoneNumberMatchReservation_returnTrue() {
        ReservationList reservationList = new ReservationList();
        reservationList.add(ALICE_RESERVATION);
        FilteredList<Reservation> filteredList = new FilteredList<>(reservationList.asUnmodifiableObservableList());
        CustomerContainsReservationPredicate predicate =
                new CustomerContainsReservationPredicate(filteredList);
        Customer customer = new CustomerBuilder().withPhone(ALICE_RESERVATION.getPhone().toString()).build();

        // Person's phone number matches the phone number in the reservation
        assertTrue(predicate.test(customer));
    }

    @Test
    void test_customerPhoneNumberDoesNotMatchReservation_returnFalse() {
        ReservationList reservationList = new ReservationList();
        reservationList.add(ALICE_RESERVATION);
        FilteredList<Reservation> filteredList = new FilteredList<>(reservationList.asUnmodifiableObservableList());
        CustomerContainsReservationPredicate predicate =
                new CustomerContainsReservationPredicate(filteredList);
        Customer customer = new CustomerBuilder().withPhone("88888888").build();

        // Person's phone number does not match the phone number in the reservation
        assertFalse(predicate.test(customer));
    }
}
