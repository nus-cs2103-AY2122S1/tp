package seedu.siasa.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.siasa.storage.JsonAdaptedPolicy.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.siasa.testutil.Assert.assertThrows;
import static seedu.siasa.testutil.TypicalPersons.ALICE;
import static seedu.siasa.testutil.TypicalPolicies.CRITICAL_ILLNESS;
import static seedu.siasa.testutil.TypicalPolicies.FULL_LIFE;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.siasa.commons.exceptions.IllegalValueException;
import seedu.siasa.model.policy.Commission;
import seedu.siasa.model.policy.CoverageExpiryDate;
import seedu.siasa.model.policy.PaymentStructure;
import seedu.siasa.model.policy.Title;

public class JsonAdaptedPolicyTest {
    private static final String INVALID_TITLE = "Policy#";
    private static final String INVALID_PRICE = "-10";
    private static final String INVALID_EXPIRY_DATE = "2020-12-10";
    private static final String INVALID_COMMISSION = "1000";

    private static final String VALID_TITLE = FULL_LIFE.getTitle().toString();
    private static final String VALID_EXPIRY_DATE = FULL_LIFE.getCoverageExpiryDate().toString();
    private static final JsonAdaptedPerson VALID_OWNER = new JsonAdaptedPerson(FULL_LIFE.getOwner());
    private static final JsonAdaptedCommission VALID_COMMISSION = new JsonAdaptedCommission(FULL_LIFE.getCommission());
    private static final JsonAdaptedPaymentStructure VALID_PAYMENT_STRUCTURE =
            new JsonAdaptedPaymentStructure(FULL_LIFE.getPaymentStructure());
    private static final List<JsonAdaptedTag> VALID_TAGS = CRITICAL_ILLNESS.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    @Test
    public void toModelType_validPolicyDetails_returnsPolicy() throws Exception {
        JsonAdaptedPolicy policy = new JsonAdaptedPolicy(FULL_LIFE);
        assertEquals(FULL_LIFE, policy.toModelType(VALID_OWNER.toModelType()));
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedPolicy policy = new JsonAdaptedPolicy(INVALID_TITLE, VALID_PAYMENT_STRUCTURE,
                VALID_EXPIRY_DATE, VALID_COMMISSION, VALID_OWNER, VALID_TAGS);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> policy.toModelType(ALICE));
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedPolicy policy =
                new JsonAdaptedPolicy(null, VALID_PAYMENT_STRUCTURE, VALID_EXPIRY_DATE, VALID_COMMISSION, VALID_OWNER,
                        VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> policy.toModelType(ALICE));
    }

    /*
    @Test
    public void toModelType_invalidPrice_throwsIllegalValueException() {
        JsonAdaptedPolicy policy = new JsonAdaptedPolicy(VALID_TITLE, INVALID_PAYMENT_STRUCTURE,
                VALID_EXPIRY_DATE, VALID_COMMISSION, VALID_OWNER, VALID_TAGS);
        String expectedMessage = PaymentStructure.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> policy.toModelType(ALICE));
    }
     */

    @Test
    public void toModelType_nullPrice_throwsIllegalValueException() {
        JsonAdaptedPolicy policy =
                new JsonAdaptedPolicy(VALID_TITLE, null, VALID_EXPIRY_DATE, VALID_COMMISSION, VALID_OWNER, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PaymentStructure.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> policy.toModelType(ALICE));
    }

    @Test
    public void toModelType_nullExpiryDate_throwsIllegalValueException() {
        JsonAdaptedPolicy policy =
                new JsonAdaptedPolicy(VALID_TITLE, VALID_PAYMENT_STRUCTURE, null, VALID_COMMISSION, VALID_OWNER,
                        VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, CoverageExpiryDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> policy.toModelType(ALICE));
    }

    /*
    @Test
    public void toModelType_invalidCommission_throwsIllegalValueException() {
        JsonAdaptedPolicy policy = new JsonAdaptedPolicy(VALID_TITLE, VALID_PAYMENT_STRUCTURE,
                VALID_EXPIRY_DATE, INVALID_COMMISSION, VALID_OWNER, VALID_TAGS);
        String expectedMessage = Commission.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> policy.toModelType(ALICE));
    }
     */

    @Test
    public void toModelType_nullCommission_throwsIllegalValueException() {
        JsonAdaptedPolicy policy =
                new JsonAdaptedPolicy(VALID_TITLE, VALID_PAYMENT_STRUCTURE, VALID_EXPIRY_DATE, null, VALID_OWNER,
                        VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Commission.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> policy.toModelType(ALICE));
    }

    // TODO: Add tags test
}
