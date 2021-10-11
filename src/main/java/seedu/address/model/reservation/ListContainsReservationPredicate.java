package seedu.address.model.reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.function.Predicate;

import seedu.address.logic.parser.enums.EnumTypeOfCheck;

/**
 * Tests that a {@code Reservation}'s {@code DateTime} matches the user input DateTime given.
 */
public class ListContainsReservationPredicate implements Predicate<Reservation> {
    private final LocalDate date;
    private final LocalTime time;
    private final EnumTypeOfCheck typeOfCheck;

    /**
     * Constructs a new ListContainsReservationPredicate
     * @param date date parsed from user's input
     * @param time time parsed from user's input
     * @param typeOfCheck enum specifying if user is checking for date, time or both
     */
    public ListContainsReservationPredicate(LocalDate date, LocalTime time, EnumTypeOfCheck typeOfCheck) {
        this.date = date;
        this.time = time;
        this.typeOfCheck = typeOfCheck;
    }

    @Override
    public boolean test(Reservation reservation) {
        LocalDate reservationDate = reservation.getDateTime().toLocalDate();
        LocalTime reservationTime = reservation.getDateTime().toLocalTime();
        boolean result = false;
        switch (typeOfCheck) {
        case Date:
            result = reservationDate.isEqual(date);
            break;
        case Time:
            result = reservationDate.isEqual(LocalDate.now()) && reservationTime.equals(time);
            break;
        case DateTime:
            result = reservationDate.isEqual(date) && reservationTime.equals(time);
            break;
        default:
            assert false;
        }
        return result;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public EnumTypeOfCheck getTypeOfCheck() {
        return typeOfCheck;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListContainsReservationPredicate // instanceof handles nulls
                && date.equals(((ListContainsReservationPredicate) other).date) // state check
                && time.equals(((ListContainsReservationPredicate) other).time) // state check
                && typeOfCheck.equals(((ListContainsReservationPredicate) other).typeOfCheck)); // state check
    }
}
