package safeforhall.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static safeforhall.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static safeforhall.testutil.Assert.assertThrows;
import static safeforhall.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

import safeforhall.commons.exceptions.IllegalValueException;
import safeforhall.model.person.Email;
import safeforhall.model.person.Name;
import safeforhall.model.person.Phone;

// TODO: Add all test cases for new fields
public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_ROOM = BENSON.getRoom().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_VACC = BENSON.getVaccStatus().toString();
    private static final String VALID_FACULTY = BENSON.getFaculty().toString();
    private static final String VALID_FETDATE = BENSON.getLastFetDate().toString();
    private static final String VALID_COLLECTIONDATE = BENSON.getLastCollectionDate().toString();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_ROOM, VALID_PHONE, VALID_EMAIL,
                        VALID_VACC, VALID_FACULTY, VALID_FETDATE, VALID_COLLECTIONDATE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_ROOM, VALID_PHONE, VALID_EMAIL,
                VALID_VACC, VALID_FACULTY, VALID_FETDATE, VALID_COLLECTIONDATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_ROOM, INVALID_PHONE, VALID_EMAIL,
                        VALID_VACC, VALID_FACULTY, VALID_FETDATE, VALID_COLLECTIONDATE);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_ROOM, null, VALID_EMAIL,
                VALID_VACC, VALID_FACULTY, VALID_FETDATE, VALID_COLLECTIONDATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_ROOM, VALID_PHONE, INVALID_EMAIL,
                        VALID_VACC, VALID_FACULTY, VALID_FETDATE, VALID_COLLECTIONDATE);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_ROOM, VALID_PHONE, null,
                VALID_VACC, VALID_FACULTY, VALID_FETDATE, VALID_COLLECTIONDATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

}
