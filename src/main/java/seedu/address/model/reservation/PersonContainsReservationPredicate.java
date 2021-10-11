package seedu.address.model.reservation;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Phone} matches any of the phone number of the reservations specified .
 */
public class PersonContainsReservationPredicate implements Predicate<Person> {
    private final ObservableList<Reservation> reservationsList;

    public PersonContainsReservationPredicate(ObservableList<Reservation> reservationsList) {
        this.reservationsList = reservationsList;
    }

    @Override
    public boolean test(Person person) {
        return reservationsList.stream().anyMatch(reservation -> reservation.getPhone().equals(person.getPhone()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.reservation.PersonContainsReservationPredicate
                && reservationsList.equals(((PersonContainsReservationPredicate) other).reservationsList));
    }
}

