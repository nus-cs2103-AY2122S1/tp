package seedu.fast.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.fast.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.fast.testutil.Assert.assertThrows;
import static seedu.fast.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.fast.commons.exceptions.IllegalValueException;
import seedu.fast.model.person.Address;
import seedu.fast.model.person.Appointment;
import seedu.fast.model.person.AppointmentCount;
import seedu.fast.model.person.Email;
import seedu.fast.model.person.Name;
import seedu.fast.model.person.Phone;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_APPOINTMENT = "2021-10-10";
    private static final String INVALID_APPOINTMENT_TIME = "10:00";
    private static final String INVALID_APPOINTMENT_VENUE = "test1test2test3test4test5test6test7";
    private static final String INVALID_APPOINTMENT_COUNT = "-1000";


    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_REMARK = BENSON.getRemark().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_APPOINTMENT_DATE = BENSON.getAppointment().getDate();
    private static final String VALID_APPOINTMENT_TIME = BENSON.getAppointment().getTimeFormatted();
    private static final String VALID_APPOINTMENT_VENUE = BENSON.getAppointment().getVenue();
    private static final String VALID_APPOINTMENT_COUNT = BENSON.getCount().toString();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK, VALID_TAGS,
                VALID_APPOINTMENT_DATE, VALID_APPOINTMENT_TIME, VALID_APPOINTMENT_VENUE,
                VALID_APPOINTMENT_COUNT);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK, VALID_TAGS,
                VALID_APPOINTMENT_DATE, VALID_APPOINTMENT_TIME, VALID_APPOINTMENT_VENUE,
                VALID_APPOINTMENT_COUNT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK, VALID_TAGS,
                        VALID_APPOINTMENT_DATE, VALID_APPOINTMENT_TIME, VALID_APPOINTMENT_VENUE,
                        VALID_APPOINTMENT_COUNT);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK, VALID_TAGS,
                VALID_APPOINTMENT_DATE, VALID_APPOINTMENT_TIME, VALID_APPOINTMENT_VENUE,
                VALID_APPOINTMENT_COUNT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_REMARK, VALID_TAGS,
                VALID_APPOINTMENT_DATE, VALID_APPOINTMENT_TIME, VALID_APPOINTMENT_VENUE,
                VALID_APPOINTMENT_COUNT);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_REMARK, VALID_TAGS,
                VALID_APPOINTMENT_DATE, VALID_APPOINTMENT_TIME, VALID_APPOINTMENT_VENUE,
                VALID_APPOINTMENT_COUNT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_REMARK, VALID_TAGS,
                        VALID_APPOINTMENT_DATE, VALID_APPOINTMENT_TIME, VALID_APPOINTMENT_VENUE,
                        VALID_APPOINTMENT_COUNT);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_REMARK, VALID_TAGS,
                VALID_APPOINTMENT_DATE, VALID_APPOINTMENT_TIME, VALID_APPOINTMENT_VENUE,
                VALID_APPOINTMENT_COUNT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        invalidTags, VALID_APPOINTMENT_DATE, VALID_APPOINTMENT_TIME, VALID_APPOINTMENT_VENUE,
                        VALID_APPOINTMENT_COUNT);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidAppointmentDate_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK, VALID_TAGS,
                INVALID_APPOINTMENT, VALID_APPOINTMENT_TIME, VALID_APPOINTMENT_VENUE,
                VALID_APPOINTMENT_COUNT);
        String expectedMessage = Appointment.INVALID_DATE_INPUT;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAppointmentDate_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK, VALID_TAGS,
                null, VALID_APPOINTMENT_TIME, VALID_APPOINTMENT_VENUE,
                VALID_APPOINTMENT_COUNT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Appointment.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAppointmentTime_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK, VALID_TAGS,
                VALID_APPOINTMENT_DATE, INVALID_APPOINTMENT_TIME, VALID_APPOINTMENT_VENUE,
                VALID_APPOINTMENT_COUNT);
        String expectedMessage = Appointment.INVALID_TIME_INPUT;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAppointmentTime_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK, VALID_TAGS,
                VALID_APPOINTMENT_DATE, null, VALID_APPOINTMENT_VENUE,
                VALID_APPOINTMENT_COUNT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Appointment.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAppointmentVenue_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK, VALID_TAGS,
                VALID_APPOINTMENT_DATE, VALID_APPOINTMENT_TIME, INVALID_APPOINTMENT_VENUE,
                VALID_APPOINTMENT_COUNT);
        String expectedMessage = Appointment.INVALID_VENUE_INPUT;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAppointmentVenue_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK, VALID_TAGS,
                VALID_APPOINTMENT_DATE, VALID_APPOINTMENT_TIME, null,
                VALID_APPOINTMENT_COUNT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Appointment.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAppointmentCount_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK, VALID_TAGS,
                VALID_APPOINTMENT_DATE, VALID_APPOINTMENT_TIME, VALID_APPOINTMENT_VENUE,
                INVALID_APPOINTMENT_COUNT);
        String expectedMessage = AppointmentCount.INVALID_COUNT_INPUT;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAppointmentCount_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK, VALID_TAGS,
                VALID_APPOINTMENT_DATE, VALID_APPOINTMENT_TIME, VALID_APPOINTMENT_VENUE,
                null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                AppointmentCount.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

}
