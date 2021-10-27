package seedu.address.model.reservation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.reservation.exception.ReservationNotFoundException;

/**
 * Represents a list of reservation.
 * Supports a minimal set of list operations.
 */
public class ReservationList implements Iterable<Reservation> {
    private static final Comparator<Reservation> DATE_TIME_ASCENDING =
            Comparator.comparing(Reservation::getDateTime).reversed();

    private final ObservableList<Reservation> internalList = FXCollections.observableArrayList();
    private final ObservableList<Reservation> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent reservation as the given argument
     */
    public boolean contains(Reservation toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a reservation to the list
     */
    public void add(Reservation toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
        internalList.sort(DATE_TIME_ASCENDING);
    }

    /**
     * Replaces the reservation {@code target} in the list with {@code editedReservation}.
     * {@code target} must exist in the list.
     */
    public void setReservation(Reservation target, Reservation editedReservation) {
        requireAllNonNull(target, editedReservation);
        int index = internalList.indexOf(target);
        if (index < 0) {
            throw new ReservationNotFoundException();
        }
        internalList.set(index, editedReservation);
        internalList.sort(DATE_TIME_ASCENDING);
    }

    /**
     * Removes the equivalent reservation from the list.
     * The reservation must exist in the list.
     */
    public void remove(Reservation toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ReservationNotFoundException();
        }
    }

    public void setReservations(ReservationList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        internalList.sort(DATE_TIME_ASCENDING);
    }

    /**
     * Replaces the contents of this list with {@code reservations}
     */
    public void setReservations(List<Reservation> reservations) {
        requireNonNull(reservations);
        internalList.setAll(reservations);
        internalList.sort(DATE_TIME_ASCENDING);
    }

    /**
     * Replaces the contents of this list with {@code reservations}
     */
    public void resetReservations() {
        setReservations(new ReservationList());
    }

    /**
     * Return the backing list as an unmodifiable {@code ObservableList}
     */
    public ObservableList<Reservation> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Reservation> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object o) {
        return o == this
                || (o instanceof ReservationList
                        && internalList.equals(((ReservationList) o).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
