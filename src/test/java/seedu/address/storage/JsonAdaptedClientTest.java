package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedClient.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.Address;
import seedu.address.model.client.DisposableIncome;
import seedu.address.model.client.Email;
import seedu.address.model.client.LastMet;
import seedu.address.model.client.Name;
import seedu.address.model.client.NextMeeting;
import seedu.address.model.client.Phone;
import seedu.address.model.client.RiskAppetite;

public class JsonAdaptedClientTest {
    private static final String INVALID_CLIENTID = "SEWQOJEWOQ";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_RISKAPPETITE = "7";
    private static final String INVALID_DISPOSABLEINCOME = "-329";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_LASTMET = "40-01-1999";
    private static final String EXAMPLE_CURRENT_PLAN = "Prudential PRUShield";
    private static final String OUTDATED_NEXTMEETING_BEFORELASTMET = "24-11-2020 (10:00~12:00), SOC";
    private static final String OUTDATED_NEXTMEETING_AFTERLASTMET = "01-11-2021 (10:00~12:00), SOC";
    private static final String UPDATED_LASTMET = "01-11-2021";

    private static final String VALID_CLIENTID = "10";
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_RISKAPPETITE = BENSON.getRiskAppetite().toString();
    private static final String VALID_DISPOSABLEINCOME = BENSON.getDisposableIncome().toString();
    private static final String VALID_CURRENTPLAN = BENSON.getCurrentPlan().toString();
    private static final String VALID_LASTMET = BENSON.getLastMet().toString();
    private static final String VALID_NEXTMEETING = BENSON.getNextMeeting().toString();

    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validClientDetails_returnsClient() throws Exception {
        JsonAdaptedClient client = new JsonAdaptedClient(BENSON);
        assertEquals(BENSON, client.toModelType());
    }

    @Test
    public void toModelType_validClientDetailsBuildClient_returnsClient() throws Exception {
        JsonAdaptedClient client =
            new JsonAdaptedClient(BENSON.getClientId().toString(), VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_RISKAPPETITE, VALID_DISPOSABLEINCOME, VALID_CURRENTPLAN, VALID_LASTMET,
                VALID_NEXTMEETING, VALID_TAGS);
        assertEquals(BENSON, client.toModelType());
    }

    @Test
    public void toModelType_validClientDetailsWithOutdatedNextMeeting_returnsClientLastMetUnchanged() throws Exception {
        JsonAdaptedClient client =
            new JsonAdaptedClient(VALID_CLIENTID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_RISKAPPETITE, VALID_DISPOSABLEINCOME, EXAMPLE_CURRENT_PLAN, VALID_LASTMET,
                OUTDATED_NEXTMEETING_BEFORELASTMET, VALID_TAGS);
        JsonAdaptedClient expectedClient =
            new JsonAdaptedClient(VALID_CLIENTID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_RISKAPPETITE, VALID_DISPOSABLEINCOME, EXAMPLE_CURRENT_PLAN, VALID_LASTMET,
                NextMeeting.NO_NEXT_MEETING, VALID_TAGS);
        assertEquals(expectedClient.toModelType(), client.toModelType());
    }

    @Test
    public void toModelType_validClientDetailsWithOutdatedNextMeeting_returnsClientLastMetUpdated() throws Exception {
        JsonAdaptedClient client =
            new JsonAdaptedClient(VALID_CLIENTID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_RISKAPPETITE, VALID_DISPOSABLEINCOME, EXAMPLE_CURRENT_PLAN, VALID_LASTMET,
                OUTDATED_NEXTMEETING_AFTERLASTMET, VALID_TAGS);
        JsonAdaptedClient expectedClient =
            new JsonAdaptedClient(VALID_CLIENTID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_RISKAPPETITE, VALID_DISPOSABLEINCOME, EXAMPLE_CURRENT_PLAN, UPDATED_LASTMET,
                NextMeeting.NO_NEXT_MEETING, VALID_TAGS);
        assertEquals(expectedClient.toModelType(), client.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_CLIENTID, INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_RISKAPPETITE, VALID_DISPOSABLEINCOME, EXAMPLE_CURRENT_PLAN, VALID_LASTMET,
                        VALID_NEXTMEETING, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_CLIENTID, null, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_RISKAPPETITE, VALID_DISPOSABLEINCOME,
                EXAMPLE_CURRENT_PLAN, VALID_LASTMET, VALID_NEXTMEETING, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_CLIENTID, VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_RISKAPPETITE, VALID_DISPOSABLEINCOME, EXAMPLE_CURRENT_PLAN,
                        VALID_LASTMET, VALID_NEXTMEETING, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_CLIENTID, VALID_NAME, null,
                VALID_EMAIL, VALID_ADDRESS, VALID_RISKAPPETITE, VALID_DISPOSABLEINCOME,
                EXAMPLE_CURRENT_PLAN, VALID_LASTMET, VALID_NEXTMEETING, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_CLIENTID, VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                        VALID_LASTMET, VALID_RISKAPPETITE, VALID_DISPOSABLEINCOME, EXAMPLE_CURRENT_PLAN,
                        VALID_NEXTMEETING, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_CLIENTID, VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_RISKAPPETITE, VALID_DISPOSABLEINCOME, EXAMPLE_CURRENT_PLAN, VALID_LASTMET,
                VALID_NEXTMEETING, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_CLIENTID, VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                        VALID_RISKAPPETITE, VALID_DISPOSABLEINCOME, EXAMPLE_CURRENT_PLAN, VALID_LASTMET,
                        VALID_NEXTMEETING, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_CLIENTID, VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_RISKAPPETITE, VALID_DISPOSABLEINCOME, EXAMPLE_CURRENT_PLAN, VALID_LASTMET,
                VALID_NEXTMEETING, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test

    public void toModelType_invalidLastMet_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_CLIENTID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_RISKAPPETITE, VALID_DISPOSABLEINCOME, EXAMPLE_CURRENT_PLAN, INVALID_LASTMET,
                        VALID_NEXTMEETING, VALID_TAGS);
        String expectedMessage = LastMet.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidRiskAppetite_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_CLIENTID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        INVALID_RISKAPPETITE, VALID_DISPOSABLEINCOME, EXAMPLE_CURRENT_PLAN, VALID_LASTMET,
                        VALID_NEXTMEETING, VALID_TAGS);
        String expectedMessage = RiskAppetite.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullLastMet_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_CLIENTID, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_RISKAPPETITE, VALID_DISPOSABLEINCOME,
                EXAMPLE_CURRENT_PLAN, null, VALID_NEXTMEETING, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LastMet.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullRiskAppetite_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_CLIENTID, VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, null, VALID_DISPOSABLEINCOME, EXAMPLE_CURRENT_PLAN, VALID_LASTMET,
                VALID_NEXTMEETING, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, RiskAppetite.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidDisposableIncome_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_CLIENTID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_RISKAPPETITE, INVALID_DISPOSABLEINCOME, EXAMPLE_CURRENT_PLAN, VALID_LASTMET,
                        VALID_NEXTMEETING, VALID_TAGS);
        String expectedMessage = DisposableIncome.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullDisposableIncome_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_CLIENTID, VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_RISKAPPETITE, null, EXAMPLE_CURRENT_PLAN, VALID_LASTMET,
                VALID_NEXTMEETING, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DisposableIncome.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_CLIENTID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_RISKAPPETITE, VALID_DISPOSABLEINCOME, EXAMPLE_CURRENT_PLAN,
                        VALID_LASTMET, VALID_NEXTMEETING, invalidTags);
        assertThrows(IllegalValueException.class, client::toModelType);
    }

    @Test
    public void toModelType_nullCurrentPlan_throwsIllegalValueException() {
        JsonAdaptedClient client =
            new JsonAdaptedClient(VALID_CLIENTID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_RISKAPPETITE, VALID_DISPOSABLEINCOME, null,
                VALID_LASTMET, VALID_NEXTMEETING, VALID_TAGS);
        assertThrows(IllegalValueException.class, client::toModelType);
    }

    @Test
    public void toModelType_nullNextMeeting_throwsIllegalValueException() {
        JsonAdaptedClient client =
            new JsonAdaptedClient(VALID_CLIENTID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_RISKAPPETITE, VALID_DISPOSABLEINCOME, EXAMPLE_CURRENT_PLAN,
                VALID_LASTMET, null, VALID_TAGS);
        assertThrows(IllegalValueException.class, client::toModelType);
    }

}
