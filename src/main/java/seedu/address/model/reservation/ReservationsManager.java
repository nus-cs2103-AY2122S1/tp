package seedu.address.model.reservation;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.reservation.exception.ReservationException;
import seedu.address.model.table.Table;

/**
 * Represents the manager class for all reservations related data
 */
public class ReservationsManager {
    private final ReservationList reservations;

    public ReservationsManager() {
        this.reservations = new ReservationList();
    }

    public ReservationsManager(ReservationList reservations) {
        this.reservations = reservations;
    }

    public Table getAvailableTable(Model model, int numberOfPeople, LocalDateTime dateTime)
            throws ReservationException {
        return model.getTableManager().getAvailableTable(numberOfPeople, filterReservationsOnDateTime(dateTime));
    }

    private List<Reservation> filterReservationsOnDateTime(LocalDateTime dateTime) {
        return reservations
                .asUnmodifiableObservableList()
                .stream()
                .filter(reservation -> dateTime.equals(reservation.getDateTime()))
                .collect(Collectors.toList());
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations.setReservations(reservations);
    }

    public void resetReservations() {
        reservations.resetReservations();
    }

    /**
     * Replaces the reservation {@code target} in the list with {@code editedReservation}
     */
    public void setReservation(Reservation target, Reservation editedReservation) {
        requireNonNull(editedReservation);
        reservations.setReservation(target, editedReservation);
    }

    /**
     * Check if {@code reservation} exists in the database
     */
    public boolean hasReservation(Reservation reservation) {
        requireNonNull(reservation);
        return reservations.contains(reservation);
    }

    /**
     * Adds a new reservation to the list
     */
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    /**
     * Removes {@code key} from the database
     * {@code key} must exist in the list
     */
    public void removeReservation(Reservation key) {
        reservations.remove(key);
    }

    /**
     * Return the backing list as an unmodifiable {@code ObservableList}
     */
    public ObservableList<Reservation> getUnmodifiableObservableList() {
        return reservations.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReservationsManager // instanceof handles nulls
                && reservations.equals(((ReservationsManager) other).reservations));
    }
}
