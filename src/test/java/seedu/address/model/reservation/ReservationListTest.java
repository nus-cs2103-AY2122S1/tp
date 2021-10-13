package seedu.address.model.reservation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Phone;
import seedu.address.model.reservation.exception.ReservationNotFoundException;

class ReservationListTest {
    public static final Reservation DUMMY_RESERVATION = new Reservation(
            new Phone("98765432"),
            5,
            LocalDateTime.parse("2021-11-11T20:00")
    );
    public static final Reservation RESERVATION_NOT_IN_LIST = new Reservation(
            new Phone("12345678"),
            2,
            LocalDateTime.parse("2021-11-11T20:00")
    );

    private ReservationList reservationList = new ReservationList();

    @Test
    public void contains_nullReservation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> reservationList.contains(null));
    }

    @Test
    public void contains_reservationNotInList_returnsFalse() {
        assertFalse(reservationList.contains(RESERVATION_NOT_IN_LIST));
    }

    @Test
    public void contains_reservationInList_returnsTrue() {
        reservationList.add(DUMMY_RESERVATION);
        assertTrue(reservationList.contains(DUMMY_RESERVATION));
    }

    @Test
    public void add_nullReservation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> reservationList.add(null));
    }

    @Test
    public void setReservation_nullTargetReservation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                reservationList.setReservation(null, RESERVATION_NOT_IN_LIST));
    }

    @Test
    public void setReservation_nullEditedReservation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                reservationList.setReservation(DUMMY_RESERVATION, null));
    }

    @Test
    public void setReservation_targetReservationNotInList_throwsReservationNotFoundException() {
        assertThrows(ReservationNotFoundException.class, () ->
                reservationList.setReservation(RESERVATION_NOT_IN_LIST, DUMMY_RESERVATION));
    }

    @Test
    public void setReservation_editedReservationIsSameReservation_success() {
        reservationList.add(DUMMY_RESERVATION);
        reservationList.setReservation(DUMMY_RESERVATION, DUMMY_RESERVATION);
        ReservationList expected = new ReservationList();
        expected.add(DUMMY_RESERVATION);
        assertEquals(expected, reservationList);
    }

    @Test
    public void setReservation_editedReservationOfDifferentIdentity() {
        reservationList.add(DUMMY_RESERVATION);
        reservationList.setReservation(DUMMY_RESERVATION, RESERVATION_NOT_IN_LIST);
        ReservationList expected = new ReservationList();
        expected.add(RESERVATION_NOT_IN_LIST);
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
                reservationList.remove(RESERVATION_NOT_IN_LIST));
    }

    @Test
    public void remove_reservationInList_success() {
        reservationList.add(DUMMY_RESERVATION);
        reservationList.remove(DUMMY_RESERVATION);
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
        reservationList.add(DUMMY_RESERVATION);
        ReservationList expected = new ReservationList();
        expected.add(RESERVATION_NOT_IN_LIST);
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
        reservationList.add(DUMMY_RESERVATION);
        List<Reservation> validList = Collections.singletonList(DUMMY_RESERVATION);
        reservationList.setReservations(validList);
        ReservationList expected = new ReservationList();
        expected.add(DUMMY_RESERVATION);
        assertEquals(expected, reservationList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                reservationList.asUnmodifiableObservableList().remove(0));
    }
}
