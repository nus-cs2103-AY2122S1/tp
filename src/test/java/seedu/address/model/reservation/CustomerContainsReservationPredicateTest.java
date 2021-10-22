package seedu.address.model.reservation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import javafx.collections.transformation.FilteredList;
import seedu.address.model.person.Phone;
import seedu.address.model.person.customer.Customer;
import seedu.address.model.table.Table;
import seedu.address.testutil.CustomerBuilder;

class CustomerContainsReservationPredicateTest {

    @Test
    void testEquals() {
        int numberOfCustomer = 5;
        LocalDateTime dateTime = LocalDateTime.parse("2020-10-10T19:00");

        Phone phone1 = new Phone("99999999");
        Table table1 = new Table(5, 10);
        Reservation reservation1 = new Reservation(phone1, numberOfCustomer, dateTime, table1);
        ReservationList reservationList1 = new ReservationList();
        reservationList1.add(reservation1);
        FilteredList<Reservation> filteredList1 = new FilteredList<>(reservationList1.asUnmodifiableObservableList());
        CustomerContainsReservationPredicate predicate1 = new CustomerContainsReservationPredicate(filteredList1);

        Phone phone2 = new Phone("88888888");
        Table table2 = new Table(5, 11);
        Reservation reservation2 = new Reservation(phone2, numberOfCustomer, dateTime, table2);
        ReservationList reservationList2 = new ReservationList();
        reservationList2.add(reservation2);
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
        int numberOfCustomer = 5;
        LocalDateTime dateTime = LocalDateTime.parse("2020-10-10T19:00");

        Phone phone = new Phone("99999999");
        Table table = new Table(5, 10);
        Reservation reservation = new Reservation(phone, numberOfCustomer, dateTime, table);
        ReservationList reservationList = new ReservationList();
        reservationList.add(reservation);
        FilteredList<Reservation> filteredList = new FilteredList<>(reservationList.asUnmodifiableObservableList());
        CustomerContainsReservationPredicate predicate =
                new CustomerContainsReservationPredicate(filteredList);
        Customer customer = new CustomerBuilder().withPhone("99999999").build();

        // Person's phone number matches the phone number in the reservation
        assertTrue(predicate.test(customer));
    }

    @Test
    void test_customerPhoneNumberDoesNotMatchReservation_returnFalse() {
        int numberOfCustomer = 5;
        LocalDateTime dateTime = LocalDateTime.parse("2020-10-10T19:00");

        Phone phone = new Phone("99999999");
        Table table = new Table(5, 10);
        Reservation reservation = new Reservation(phone, numberOfCustomer, dateTime, table);
        ReservationList reservationList = new ReservationList();
        reservationList.add(reservation);
        FilteredList<Reservation> filteredList = new FilteredList<>(reservationList.asUnmodifiableObservableList());
        CustomerContainsReservationPredicate predicate =
                new CustomerContainsReservationPredicate(filteredList);
        Customer customer = new CustomerBuilder().withPhone("88888888").build();

        // Person's phone number does not match the phone number in the reservation
        assertFalse(predicate.test(customer));
    }
}
