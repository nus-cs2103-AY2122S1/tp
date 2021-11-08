package seedu.address.model.reservation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Phone;
import seedu.address.model.table.Table;
import seedu.address.model.tag.Tag;

class ReservationTest {
    public static final String DUMMY_PHONE_NUMBER = "98765432";
    public static final int DUMMY_NUMBER_OF_PEOPLE = 10;
    public static final LocalDateTime DUMMY_DATE_TIME = LocalDateTime.parse("2021-11-11T20:00");
    public static final Table DUMMY_TABLE = new Table(5, 10);
    public static final Remark DUMMY_REMARK = new Remark("test remark");
    public static final Set<Tag> DUMMY_TAGS = Set.of(new Tag("test tag"));

    public static final String DIFFERENT_PHONE_NUMBER = "12345678";
    public static final int DIFFERENT_NUMBER_OF_PEOPLE = 5;
    public static final LocalDateTime DIFFERENT_DATE_TIME = LocalDateTime.parse("2021-11-11T21:00");
    public static final Table DIFFERENT_TABLE = new Table(6, 11);
    public static final Remark DIFFERENT_REMARK = new Remark("different remark");
    public static final Set<Tag> DIFFERENT_TAGS = Set.of(new Tag("different tag"));

    @Test
    public void equals() {
        Reservation reservation = new Reservation(
                new Phone(DUMMY_PHONE_NUMBER),
                DUMMY_NUMBER_OF_PEOPLE,
                DUMMY_DATE_TIME,
                DUMMY_TABLE,
                DUMMY_REMARK,
                DUMMY_TAGS
        );
        Reservation reservationCopied = new Reservation(
                new Phone(DUMMY_PHONE_NUMBER),
                DUMMY_NUMBER_OF_PEOPLE,
                DUMMY_DATE_TIME,
                DUMMY_TABLE,
                DUMMY_REMARK,
                DUMMY_TAGS
        );

        // same values -> returns true
        assertTrue(reservation.equals(reservationCopied));

        // null -> returns false
        assertFalse(reservation.equals(null));

        // different types -> returns false
        assertFalse(reservation.equals(5));

        // different phone numbers -> returns false
        assertFalse(reservation.equals(new Reservation(
                new Phone(DIFFERENT_PHONE_NUMBER),
                DUMMY_NUMBER_OF_PEOPLE,
                DUMMY_DATE_TIME,
                DUMMY_TABLE,
                DUMMY_REMARK,
                DUMMY_TAGS
        )));

        // different number of people -> returns false
        assertFalse(reservation.equals(new Reservation(
                new Phone(DUMMY_PHONE_NUMBER),
                DIFFERENT_NUMBER_OF_PEOPLE,
                DUMMY_DATE_TIME,
                DUMMY_TABLE,
                DUMMY_REMARK,
                DUMMY_TAGS
        )));

        // different date time -> returns false
        assertFalse(reservation.equals(new Reservation(
                new Phone(DUMMY_PHONE_NUMBER),
                DUMMY_NUMBER_OF_PEOPLE,
                DIFFERENT_DATE_TIME,
                DUMMY_TABLE,
                DUMMY_REMARK,
                DUMMY_TAGS
        )));

        // different table -> returns false
        assertFalse(reservation.equals(new Reservation(
                new Phone(DUMMY_PHONE_NUMBER),
                DUMMY_NUMBER_OF_PEOPLE,
                DUMMY_DATE_TIME,
                DIFFERENT_TABLE,
                DUMMY_REMARK,
                DUMMY_TAGS
        )));

        // different remarks
        assertFalse(reservation.equals(new Reservation(
                new Phone(DUMMY_PHONE_NUMBER),
                DUMMY_NUMBER_OF_PEOPLE,
                DUMMY_DATE_TIME,
                DUMMY_TABLE,
                DIFFERENT_REMARK,
                DUMMY_TAGS
        )));

        // different tags
        assertFalse(reservation.equals(new Reservation(
                new Phone(DUMMY_PHONE_NUMBER),
                DUMMY_NUMBER_OF_PEOPLE,
                DUMMY_DATE_TIME,
                DUMMY_TABLE,
                DUMMY_REMARK,
                DIFFERENT_TAGS
        )));
    }
}
