package seedu.siasa.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.siasa.storage.JsonAdaptedPolicy.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.siasa.testutil.Assert.assertThrows;
import static seedu.siasa.testutil.TypicalPolicies.FULL_LIFE;

import org.junit.jupiter.api.Test;

import seedu.siasa.commons.exceptions.IllegalValueException;
import seedu.siasa.model.policy.Commission;
import seedu.siasa.model.policy.ExpiryDate;
import seedu.siasa.model.policy.Price;
import seedu.siasa.model.policy.Title;

public class JsonAdaptedPolicyTest {
    private static final String INVALID_TITLE = "Policy#";
    private static final String INVALID_PRICE = "$-10";
    private static final String INVALID_EXPIRY_DATE = "2020-20-10";
    private static final String INVALID_COMMISSION = "1000%";

    private static final String VALID_TITLE = FULL_LIFE.getTitle().toString();
    private static final String VALID_PRICE = FULL_LIFE.getPrice().toString();
    private static final String VALID_EXPIRY_DATE = FULL_LIFE.getExpiryDate().toString();
    private static final String VALID_COMMISSION = FULL_LIFE.getCommission().toString();
    private static final JsonAdaptedPerson VALID_OWNER = new JsonAdaptedPerson(FULL_LIFE.getOwner());

    @Test
    public void toModelType_validPolicyDetails_returnsPolicy() throws Exception {
        JsonAdaptedPolicy policy = new JsonAdaptedPolicy(FULL_LIFE);
        assertEquals(FULL_LIFE, policy.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedPolicy policy =
            new JsonAdaptedPolicy(INVALID_TITLE, VALID_PRICE, VALID_EXPIRY_DATE, VALID_COMMISSION, VALID_OWNER);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, policy::toModelType);
    }

//    @Test
//    public void toModelType_nullTitle_throwsIllegalValueException() {
//        JsonAdaptedPolicy policy =
//            new JsonAdaptedPolicy(null, VALID_PRICE,VALID_EXPIRY_DATE, VALID_COMMISSION, VALID_OWNER);
//        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
//        assertThrows(IllegalValueException.class, expectedMessage, policy::toModelType);
//    }

    @Test
    public void toModelType_invalidPrice_throwsIllegalValueException() {
        JsonAdaptedPolicy policy =
            new JsonAdaptedPolicy(VALID_TITLE, INVALID_PRICE, VALID_EXPIRY_DATE, VALID_COMMISSION, VALID_OWNER);
        String expectedMessage = Price.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, policy::toModelType);
    }

//    @Test
//    public void toModelType_nullPrice_throwsIllegalValueException() {
//        JsonAdaptedPolicy policy =
//            new JsonAdaptedPolicy(VALID_TITLE, null, VALID_EXPIRY_DATE, VALID_COMMISSION, VALID_OWNER);
//        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName());
//        assertThrows(IllegalValueException.class, expectedMessage, policy::toModelType);
//    }

    @Test
    public void toModelType_invalidExpiryDate_throwsIllegalValueException() {
        JsonAdaptedPolicy policy =
            new JsonAdaptedPolicy(VALID_TITLE, VALID_PRICE, INVALID_EXPIRY_DATE, VALID_COMMISSION, VALID_OWNER);
        String expectedMessage = ExpiryDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, policy::toModelType);
    }

//    @Test
//    public void toModelType_nullExpiryDate_throwsIllegalValueException() {
//        JsonAdaptedPolicy policy =
//            new JsonAdaptedPolicy(VALID_TITLE, VALID_PRICE, null, VALID_COMMISSION, VALID_OWNER);
//        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ExpiryDate.class.getSimpleName());
//        assertThrows(IllegalValueException.class, expectedMessage, policy::toModelType);
//    }

    @Test
    public void toModelType_invalidCommission_throwsIllegalValueException() {
        JsonAdaptedPolicy policy =
            new JsonAdaptedPolicy(VALID_TITLE, VALID_PRICE, VALID_EXPIRY_DATE, INVALID_COMMISSION, VALID_OWNER);
        String expectedMessage = Commission.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, policy::toModelType);
    }

//    @Test
//    public void toModelType_nullCommission_throwsIllegalValueException() {
//        JsonAdaptedPolicy policy =
//            new JsonAdaptedPolicy(VALID_TITLE, VALID_PRICE, VALID_EXPIRY_DATE, null, VALID_OWNER);
//        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Commission.class.getSimpleName());
//        assertThrows(IllegalValueException.class, expectedMessage, policy::toModelType);
//    }

//    @Test
//    public void toModelType_nullOwner_throwsIllegalValueException() {
//        JsonAdaptedPolicy policy =
//            new JsonAdaptedPolicy(VALID_TITLE, VALID_PRICE, VALID_EXPIRY_DATE, VALID_COMMISSION, null);
//        assertThrows(IllegalValueException.class, policy::toModelType);
//    }
}
