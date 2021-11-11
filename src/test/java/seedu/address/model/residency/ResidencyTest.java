package seedu.address.model.residency;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.PERSON_LIST_ONE;
import static seedu.address.testutil.TypicalPersons.PERSON_LIST_TWO;
import static seedu.address.testutil.TypicalRooms.ROOM_ONE;
import static seedu.address.testutil.TypicalRooms.ROOM_TWO;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class ResidencyTest {

    public static final LocalDateTime CHECK_IN_TIME_ONE = LocalDateTime.of(2021, 10, 31, 12, 0);
    public static final LocalDateTime CHECK_OUT_TIME_ONE = LocalDateTime.of(2021, 11, 1, 12, 0);
    public static final LocalDateTime CHECK_IN_TIME_TWO = LocalDateTime.of(2021, 11, 2, 12, 0);
    public static final LocalDateTime CHECK_OUT_TIME_TWO = LocalDateTime.of(2021, 11, 3, 12, 0);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Residency(null, null));
    }

    @Test
    public void isSameResidency() {

        Residency residencyOne = new Residency(ROOM_ONE, PERSON_LIST_ONE, CHECK_IN_TIME_ONE, CHECK_OUT_TIME_ONE);
        Residency residencyOneSame = new Residency(ROOM_ONE, PERSON_LIST_ONE, CHECK_IN_TIME_ONE, CHECK_OUT_TIME_ONE);
        Residency residencyOneDifferent =
                new Residency(ROOM_ONE, PERSON_LIST_TWO, CHECK_IN_TIME_TWO, CHECK_OUT_TIME_TWO);
        Residency residencyTwo = new Residency(ROOM_TWO, PERSON_LIST_TWO, CHECK_IN_TIME_TWO, CHECK_OUT_TIME_TWO);

        assertFalse(residencyOne.equals(residencyTwo));
        assertTrue(residencyOne.equals(residencyOneSame));
        assertTrue(residencyOne.equals(residencyOne));
        assertFalse(residencyOne.equals(residencyOneDifferent));
    }

    @Test
    public void toStringTest() {
        Residency residencyOne = new Residency(ROOM_ONE, PERSON_LIST_ONE, CHECK_IN_TIME_ONE, CHECK_OUT_TIME_ONE);
        Residency residencyOneSame = new Residency(ROOM_ONE, PERSON_LIST_ONE, CHECK_IN_TIME_ONE, CHECK_OUT_TIME_ONE);
        Residency residencyTwo = new Residency(ROOM_TWO, PERSON_LIST_TWO, CHECK_IN_TIME_TWO, CHECK_OUT_TIME_TWO);

        assertFalse(residencyOne.toString().equals(residencyTwo.toString()));
        assertTrue(residencyOne.toString().equals(residencyOneSame.toString()));
    }

    @Test
    public void hashcodeTest() {
        Residency residencyOne = new Residency(ROOM_ONE, PERSON_LIST_ONE, CHECK_IN_TIME_ONE, CHECK_OUT_TIME_ONE);
        Residency residencyOneSame = new Residency(ROOM_ONE, PERSON_LIST_ONE, CHECK_IN_TIME_ONE, CHECK_OUT_TIME_ONE);
        Residency residencyTwo = new Residency(ROOM_TWO, PERSON_LIST_TWO, CHECK_IN_TIME_TWO, CHECK_OUT_TIME_TWO);

        assertFalse(residencyOne.hashCode() == residencyTwo.hashCode());
        assertTrue(residencyOne.hashCode() == residencyOneSame.hashCode());
    }

}
