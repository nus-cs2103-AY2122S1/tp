package seedu.address.model.reservation;

import seedu.address.logic.parser.enums.EnumTypeOfCheck;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.function.Predicate;

public class ListContainsReservationPredicate implements Predicate<Reservation> {
    private final LocalDate date;
    private final LocalTime time;
    private final EnumTypeOfCheck typeOfCheck;

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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListContainsReservationPredicate // instanceof handles nulls
                && date.equals(((ListContainsReservationPredicate) other).date) // state check
                && time.equals(((ListContainsReservationPredicate) other).time) // state check
                && typeOfCheck.equals(((ListContainsReservationPredicate) other).typeOfCheck)); // state check
    }
}
