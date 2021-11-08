package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Language;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_LANGUAGE = " ";
    private static final String INVALID_HEALTH_CONDITION = "#diabetes";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_LANGUAGE = BENSON.getLanguage().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_LAST_VISIT = BENSON.getLastVisit().toString();
    private static final String VALID_VISIT = BENSON.getVisit().toString();
    private static final String VALID_FREQUENCY = BENSON.getFrequency().toString();
    private static final String VALID_OCCURRENCE = BENSON.getOccurrence().toString();
    private static final List<JsonAdaptedHealthCondition> VALID_HEALTH_CONDITIONS = BENSON.getHealthConditions()
            .stream().map(JsonAdaptedHealthCondition::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_LANGUAGE, VALID_ADDRESS, VALID_LAST_VISIT,
                        VALID_VISIT, VALID_FREQUENCY, VALID_OCCURRENCE, VALID_HEALTH_CONDITIONS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(null, VALID_PHONE, VALID_LANGUAGE, VALID_ADDRESS, VALID_LAST_VISIT,
                        VALID_VISIT, VALID_FREQUENCY, VALID_OCCURRENCE, VALID_HEALTH_CONDITIONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_LANGUAGE, VALID_ADDRESS, VALID_LAST_VISIT,
                        VALID_VISIT, VALID_FREQUENCY, VALID_OCCURRENCE, VALID_HEALTH_CONDITIONS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, null, VALID_LANGUAGE, VALID_ADDRESS, VALID_LAST_VISIT,
                        VALID_VISIT, VALID_FREQUENCY, VALID_OCCURRENCE, VALID_HEALTH_CONDITIONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidLanguage_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_LANGUAGE, VALID_ADDRESS, VALID_LAST_VISIT,
                        VALID_VISIT, VALID_FREQUENCY, VALID_OCCURRENCE, VALID_HEALTH_CONDITIONS);
        String expectedMessage = Language.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullLanguage_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_LAST_VISIT,
                        VALID_VISIT, VALID_FREQUENCY, VALID_OCCURRENCE, VALID_HEALTH_CONDITIONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Language.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_LANGUAGE, INVALID_ADDRESS, VALID_LAST_VISIT,
                        VALID_VISIT, VALID_FREQUENCY, VALID_OCCURRENCE, VALID_HEALTH_CONDITIONS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_LANGUAGE, null, VALID_LAST_VISIT,
                        VALID_VISIT, VALID_FREQUENCY, VALID_OCCURRENCE, VALID_HEALTH_CONDITIONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidHealthConditions_throwsIllegalValueException() {
        List<JsonAdaptedHealthCondition> invalidTags = new ArrayList<>(VALID_HEALTH_CONDITIONS);
        invalidTags.add(new JsonAdaptedHealthCondition(INVALID_HEALTH_CONDITION));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_LANGUAGE, VALID_ADDRESS, VALID_LAST_VISIT,
                        VALID_VISIT, VALID_FREQUENCY, VALID_OCCURRENCE, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
