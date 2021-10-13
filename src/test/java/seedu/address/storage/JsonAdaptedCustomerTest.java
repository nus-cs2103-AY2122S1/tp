package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedCustomer.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCustomers.CUSTOMER_BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class JsonAdaptedCustomerTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_ALLERGY = "#friend";
    private static final String INVALID_SPECIALREQUEST = "#friend";

    private static final String VALID_NAME = CUSTOMER_BENSON.getName().toString();
    private static final String VALID_PHONE = CUSTOMER_BENSON.getPhone().toString();
    private static final String VALID_EMAIL = CUSTOMER_BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = CUSTOMER_BENSON.getAddress().toString();
    private static final String VALID_LP = CUSTOMER_BENSON.getLoyaltyPoints().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = CUSTOMER_BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedAllergy> VALID_ALLERGIES =
            CUSTOMER_BENSON.getAllergies().stream()
            .map(JsonAdaptedAllergy::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedSpecialRequest> VALID_SPECIALREQUESTS =
            CUSTOMER_BENSON.getSpecialRequests().stream()
            .map(JsonAdaptedSpecialRequest::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validCustomerDetails_returnsCustomer() throws Exception {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(CUSTOMER_BENSON);
        assertEquals(CUSTOMER_BENSON, customer.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedCustomer customer =
                new JsonAdaptedCustomer(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_LP, VALID_ALLERGIES, VALID_SPECIALREQUESTS, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_LP, VALID_ALLERGIES, VALID_SPECIALREQUESTS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedCustomer customer =
                new JsonAdaptedCustomer(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_LP,
                        VALID_ALLERGIES, VALID_SPECIALREQUESTS, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_LP, VALID_ALLERGIES, VALID_SPECIALREQUESTS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedCustomer customer =
                new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                        VALID_LP, VALID_ALLERGIES, VALID_SPECIALREQUESTS, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_LP, VALID_ALLERGIES, VALID_SPECIALREQUESTS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedCustomer customer =
                new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                        VALID_LP, VALID_ALLERGIES, VALID_SPECIALREQUESTS, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_LP, VALID_ALLERGIES, VALID_SPECIALREQUESTS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedCustomer customer =
                new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_LP, VALID_ALLERGIES, VALID_SPECIALREQUESTS, invalidTags);
        assertThrows(IllegalValueException.class, customer::toModelType);
    }

    @Test
    public void toModelType_invalidAllergies_throwsIllegalValueException() {
        List<JsonAdaptedAllergy> invalidAllergies = new ArrayList<>(VALID_ALLERGIES);
        invalidAllergies.add(new JsonAdaptedAllergy(INVALID_ALLERGY));
        JsonAdaptedCustomer customer =
                new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_LP, invalidAllergies, VALID_SPECIALREQUESTS, VALID_TAGS);
        assertThrows(IllegalValueException.class, customer::toModelType);
    }

    @Test
    public void toModelType_invalidSpecialRequests_throwsIllegalValueException() {
        List<JsonAdaptedSpecialRequest> invalidSpecialRequests = new ArrayList<>(VALID_SPECIALREQUESTS);
        invalidSpecialRequests.add(new JsonAdaptedSpecialRequest(INVALID_SPECIALREQUEST));
        JsonAdaptedCustomer customer =
                new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_LP, VALID_ALLERGIES, invalidSpecialRequests, VALID_TAGS);
        assertThrows(IllegalValueException.class, customer::toModelType);
    }

}
