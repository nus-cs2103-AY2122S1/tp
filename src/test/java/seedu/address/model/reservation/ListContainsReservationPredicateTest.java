package seedu.address.model.reservation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalReservation.ALICE_RESERVATION;
import static seedu.address.testutil.TypicalReservation.BENSON_RESERVATION;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.enums.EnumTypeOfCheck;
import seedu.address.model.person.Phone;
import seedu.address.model.table.Table;
import seedu.address.model.tag.Tag;

class ListContainsReservationPredicateTest {
    private LocalDate date1 = ALICE_RESERVATION.getDateTime().toLocalDate();
    private LocalDate date2 = BENSON_RESERVATION.getDateTime().toLocalDate();
    private LocalTime time = ALICE_RESERVATION.getDateTime().toLocalTime();
    private Phone phone = ALICE_RESERVATION.getPhone();
    private EnumTypeOfCheck typeOfCheck = EnumTypeOfCheck.DateTime;
    private int numberOfPeople = ALICE_RESERVATION.getNumberOfPeople();
    private Table table = new Table(numberOfPeople, ALICE_RESERVATION.getTableId());
    private Remark remark = new Remark("");
    private Set<Tag> tags = Set.of();

    @Test
    void testEquals() {
        ListContainsReservationPredicate predicate1 = new ListContainsReservationPredicate(date1, time, typeOfCheck);
        ListContainsReservationPredicate predicate2 = new ListContainsReservationPredicate(date2, time, typeOfCheck);

        // same object -> returns true
        assertTrue(predicate1.equals(predicate1));

        // same values -> returns true
        ListContainsReservationPredicate predicate1Copy =
                new ListContainsReservationPredicate(date1, time, typeOfCheck);
        assertTrue(predicate1.equals(predicate1Copy));

        // different types -> returns false
        assertFalse(predicate1.equals(1));

        // null -> returns false
        assertFalse(predicate1.equals(null));

        // different predicate -> returns false
        assertFalse(predicate1.equals(predicate2));
    }

    @Test
    void test_reservationDateTimeMatch_returnsTrue() {
        ListContainsReservationPredicate predicate;

        predicate = new ListContainsReservationPredicate(date1, time,
                EnumTypeOfCheck.DateTime);
        assertTrue(predicate.test(ALICE_RESERVATION));

        predicate = new ListContainsReservationPredicate(date1, time,
                EnumTypeOfCheck.Date);
        assertTrue(predicate.test(ALICE_RESERVATION));
    }

    @Test
    void test_reservationDateTimeNoMatch_returnsFalse() {
        ListContainsReservationPredicate predicate;

        // Not matching date and time in query containing both date and time
        predicate = new ListContainsReservationPredicate(LocalDate.parse("2000-12-12"), LocalTime.parse("23:00"),
                EnumTypeOfCheck.DateTime);
        assertFalse(predicate.test(ALICE_RESERVATION));

        // Not matching date and matching time in query containing both date and time
        predicate = new ListContainsReservationPredicate(LocalDate.parse("2000-12-12"),
                ALICE_RESERVATION.getDateTime().toLocalTime(),
                EnumTypeOfCheck.DateTime);
        assertFalse(predicate.test(ALICE_RESERVATION));

        // Matching date and not matching time in query containing both date and time
        predicate = new ListContainsReservationPredicate(ALICE_RESERVATION.getDateTime().toLocalDate(),
                LocalTime.parse("23:00"),
                EnumTypeOfCheck.DateTime);
        assertFalse(predicate.test(ALICE_RESERVATION));

        // Not matching date in query containing only date
        predicate = new ListContainsReservationPredicate(LocalDate.parse("2000-01-02"), LocalTime.parse("23:00"),
                EnumTypeOfCheck.Date);
        assertFalse(predicate.test(ALICE_RESERVATION));
    }
}
