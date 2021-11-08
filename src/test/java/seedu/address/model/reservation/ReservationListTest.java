package seedu.address.model.reservation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalReservation.ALICE_RESERVATION;
import static seedu.address.testutil.TypicalReservation.BENSON_RESERVATION;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.reservation.exception.ReservationNotFoundException;

class ReservationListTest {
    private ReservationList reservationList = new ReservationList();

    @Test
    public void contains_nullReservation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> reservationList.contains(null));
    }

    @Test
    public void contains_reservationNotInList_returnsFalse() {
        assertFalse(reservationList.contains(ALICE_RESERVATION));
    }

    @Test
    public void contains_reservationInList_returnsTrue() {
        reservationList.add(ALICE_RESERVATION);
        assertTrue(reservationList.contains(ALICE_RESERVATION));
    }

    @Test
    public void add_nullReservation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> reservationList.add(null));
    }

    @Test
    public void setReservation_nullTargetReservation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                reservationList.setReservation(null, ALICE_RESERVATION));
    }

    @Test
    public void setReservation_nullEditedReservation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                reservationList.setReservation(ALICE_RESERVATION, null));
    }

    @Test
    public void setReservation_targetReservationNotInList_throwsReservationNotFoundException() {
        assertThrows(ReservationNotFoundException.class, () ->
                reservationList.setReservation(ALICE_RESERVATION, BENSON_RESERVATION));
    }

    @Test
    public void setReservation_editedReservationIsSameReservation_success() {
        reservationList.add(ALICE_RESERVATION);
        reservationList.setReservation(ALICE_RESERVATION, ALICE_RESERVATION);
        ReservationList expected = new ReservationList();
        expected.add(ALICE_RESERVATION);
        assertEquals(expected, reservationList);
    }

    @Test
    public void setReservation_editedReservationOfDifferentIdentity() {
        reservationList.add(ALICE_RESERVATION);
        reservationList.setReservation(ALICE_RESERVATION, BENSON_RESERVATION);
        ReservationList expected = new ReservationList();
        expected.add(BENSON_RESERVATION);
        assertEquals(expected, reservationList);
    }

    @Test
    public void remove_nullReservation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                reservationList.remove(null));
    }

    @Test
    public void remove_reservationDoesNotExist_throwsReservationNotFoundException() {
        assertThrows(ReservationNotFoundException.class, () ->
                reservationList.remove(BENSON_RESERVATION));
    }

    @Test
    public void remove_reservationInList_success() {
        reservationList.add(ALICE_RESERVATION);
        reservationList.remove(ALICE_RESERVATION);
        ReservationList expected = new ReservationList();
        assertEquals(expected, reservationList);
    }

    @Test
    public void setReservations_nullReservationList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                reservationList.setReservations((ReservationList) null));
    }

    @Test
    public void setReservations_validReservationList_success() {
        reservationList.add(ALICE_RESERVATION);
        ReservationList expected = new ReservationList();
        expected.add(BENSON_RESERVATION);
        reservationList.setReservations(expected);
        assertEquals(expected, reservationList);
    }

    @Test
    public void setReservations_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                reservationList.setReservations((List<Reservation>) null));
    }

    @Test
    public void setReservations_validList_success() {
        reservationList.add(ALICE_RESERVATION);
        List<Reservation> validList = Collections.singletonList(ALICE_RESERVATION);
        reservationList.setReservations(validList);
        ReservationList expected = new ReservationList();
        expected.add(ALICE_RESERVATION);
        assertEquals(expected, reservationList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                reservationList.asUnmodifiableObservableList().remove(0));
    }
}
