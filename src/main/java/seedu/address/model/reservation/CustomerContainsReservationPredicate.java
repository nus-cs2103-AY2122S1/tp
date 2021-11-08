package seedu.address.model.reservation;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.person.customer.Customer;

/**
 * Tests that a {@code Person}'s {@code Phone} matches any of the phone number of the reservations specified .
 */
public class CustomerContainsReservationPredicate implements Predicate<Customer> {
    private final ObservableList<Reservation> reservationsList;

    public CustomerContainsReservationPredicate(ObservableList<Reservation> reservationsList) {
        this.reservationsList = reservationsList;
    }

    @Override
    public boolean test(Customer customer) {
        return reservationsList.stream().anyMatch(reservation -> reservation.getPhone().equals(customer.getPhone()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CustomerContainsReservationPredicate
                && reservationsList.equals(((CustomerContainsReservationPredicate) other).reservationsList));
    }
}

