package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedOrder.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalOrders.ORDER1;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Date;
import seedu.address.model.Label;
import seedu.address.model.order.Amount;
import seedu.address.model.order.Customer;


public class JsonAdapterOrderTest {
    private static final String INVALID_LABEL = "TAS$%$#";
    private static final String INVALID_DATE = "28@nextmonth";
    private static final String INVALID_AMOUNT = "not amount";
    private static final String INVALID_CUSTOMER = "ALEX##";
    private static final String INVALID_IS_COMPLETE = "true and false";


    private static final String VALID_LABEL = ORDER1.getLabel().toString();
    private static final String VALID_DATE = ORDER1.getDate().toString();
    private static final String VALID_AMOUNT = ORDER1.getAmount().toString();
    private static final String VALID_ID = String.valueOf(ORDER1.getId());
    private static final String VALID_CUSTOMER = ORDER1.getCustomer().toString();
    private static final String VALID_IS_COMPLETE = String.valueOf(ORDER1.getIsComplete());

    @Test
    public void toModelType_validOrderDetails_returnsOrder() throws Exception {
        JsonAdaptedOrder order = new JsonAdaptedOrder(ORDER1);
        assertEquals(ORDER1, order.toModelType());
    }

    @Test
    public void toModelType_invalidOrderLabel_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_ID, VALID_DATE, VALID_AMOUNT,
                        VALID_CUSTOMER, VALID_IS_COMPLETE, INVALID_LABEL);
        String expectedMessage = Label.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullOrderLabel_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_ID, VALID_DATE, VALID_AMOUNT,
                        VALID_CUSTOMER, VALID_IS_COMPLETE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Label.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidOrderDate_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_ID, INVALID_DATE, VALID_AMOUNT,
                        VALID_CUSTOMER, VALID_IS_COMPLETE, VALID_LABEL);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_ID, null, VALID_AMOUNT,
                        VALID_CUSTOMER, VALID_IS_COMPLETE, VALID_LABEL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }


    @Test
    public void toModelType_nullOrderId_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(null, VALID_DATE, INVALID_AMOUNT,
                        VALID_CUSTOMER, VALID_IS_COMPLETE, VALID_LABEL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "id");
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidAmount_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_ID, VALID_DATE, INVALID_AMOUNT,
                        VALID_CUSTOMER, VALID_IS_COMPLETE, VALID_LABEL);
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullAmount_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_ID, VALID_DATE, null,
                        VALID_CUSTOMER, VALID_IS_COMPLETE, VALID_LABEL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidCustomer_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_ID, VALID_DATE, VALID_AMOUNT,
                        INVALID_CUSTOMER, VALID_IS_COMPLETE, VALID_LABEL);
        String expectedMessage = Customer.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullCustomer_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_ID, VALID_DATE, VALID_AMOUNT,
                        null, VALID_IS_COMPLETE, VALID_LABEL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Customer.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidIsComplete_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_ID, VALID_DATE, VALID_AMOUNT,
                        VALID_CUSTOMER, INVALID_IS_COMPLETE, VALID_LABEL);
        String expectedMessage = "isComplete field is not in the correct format";
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullIsComplete_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_ID, VALID_DATE, VALID_AMOUNT,
                        VALID_CUSTOMER, null, VALID_LABEL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "isComplete");
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }
}
