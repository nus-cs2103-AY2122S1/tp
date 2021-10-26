package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedReservation.DATE_TIME_CONSTRAINT;
import static seedu.address.storage.JsonAdaptedReservation.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.storage.JsonAdaptedReservation.NUMBER_OF_PEOPLE_CONSTRAINT;
import static seedu.address.storage.JsonAdaptedReservation.TABLE_ID_CONSTRAINT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalReservation.ALICE_RESERVATION;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Phone;

class JsonAdaptedReservationTest {
    private static final String VALID_PHONE = "98765432";
    private static final int VALID_NUMBER_OF_PEOPLE = 5;
    private static final String VALID_DATE_TIME = "2021-11-11T17:00";
    private static final int VALID_TABLE_ID = 1;
    private static final String INVALID_PHONE = "911a";
    private static final int INVALID_NUMBER_OF_PEOPLE = -1;
    private static final String INVALID_DATE_TIME = "2021-11-11 17:00";
    private static final int INVALID_TABLE_ID = -1;
    private static final String REMARK_NONE = "";
    private static final List<JsonAdaptedTag> TAGS_EMPTY = List.of();

    @Test
    public void toModelType_validReservationDetails_returnsReservation() throws Exception {
        JsonAdaptedReservation reservation = new JsonAdaptedReservation(ALICE_RESERVATION);
        assertEquals(ALICE_RESERVATION, reservation.toModelType());
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedReservation reservation = new JsonAdaptedReservation(
                null, VALID_NUMBER_OF_PEOPLE, VALID_DATE_TIME, VALID_TABLE_ID, REMARK_NONE, TAGS_EMPTY
        );
        String expectedMessage = String.format(
                MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()
        );
        assertThrows(
                IllegalValueException.class,
                expectedMessage,
                reservation::toModelType
        );
    }

    @Test
    public void toModelType_nullDateTime_throwsIllegalValueException() {
        JsonAdaptedReservation reservation = new JsonAdaptedReservation(
                VALID_PHONE, VALID_NUMBER_OF_PEOPLE, null, VALID_TABLE_ID, REMARK_NONE, TAGS_EMPTY
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "date time");
        assertThrows(
                IllegalValueException.class,
                expectedMessage,
                reservation::toModelType
        );
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedReservation reservation = new JsonAdaptedReservation(
                INVALID_PHONE, VALID_NUMBER_OF_PEOPLE, VALID_DATE_TIME, VALID_TABLE_ID, REMARK_NONE, TAGS_EMPTY
        );
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(
                IllegalValueException.class,
                expectedMessage,
                reservation::toModelType
        );
    }

    @Test
    public void toModelType_invalidNumberOfPeople_throwsIllegalValueException() {
        JsonAdaptedReservation reservation = new JsonAdaptedReservation(
                VALID_PHONE, INVALID_NUMBER_OF_PEOPLE, VALID_DATE_TIME, VALID_TABLE_ID, REMARK_NONE, TAGS_EMPTY
        );
        String expectedMessage = NUMBER_OF_PEOPLE_CONSTRAINT;
        assertThrows(
                IllegalValueException.class,
                expectedMessage,
                reservation::toModelType
        );
    }

    @Test
    public void toModelType_invalidDateTime_throwsIllegalValueException() {
        JsonAdaptedReservation reservation = new JsonAdaptedReservation(
                VALID_PHONE, VALID_NUMBER_OF_PEOPLE, INVALID_DATE_TIME, VALID_TABLE_ID, REMARK_NONE, TAGS_EMPTY
        );
        String expectedMessage = DATE_TIME_CONSTRAINT;
        assertThrows(
                IllegalValueException.class,
                expectedMessage,
                reservation::toModelType
        );
    }

    @Test
    public void toModelType_invalidTableId_throwsIllegalValueException() {
        JsonAdaptedReservation reservation = new JsonAdaptedReservation(
                VALID_PHONE, VALID_NUMBER_OF_PEOPLE, VALID_DATE_TIME, INVALID_TABLE_ID, REMARK_NONE, TAGS_EMPTY
        );
        String expectedMessage = TABLE_ID_CONSTRAINT;
        assertThrows(
                IllegalValueException.class,
                expectedMessage,
                reservation::toModelType
        );
    }
}
