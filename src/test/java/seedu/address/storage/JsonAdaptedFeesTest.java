package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lesson.OutstandingFees;
import seedu.address.model.lesson.OutstandingFees.LastAddedDate;

class JsonAdaptedFeesTest {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Outstanding Fee's %s field is missing!";

    private static final String INVALID_OUTSTANDING_FEES = "$100.00";
    private static final String INVALID_LAST_ADDED_DATE = "2021-02-29";

    private static final String VALID_OUTSTANDING_FEES = "250.00";
    private static final String VALID_LAST_ADDED_DATE = "2021-10-23";

    private static final LastAddedDate LAST_ADDED_DATE = new LastAddedDate(VALID_LAST_ADDED_DATE);
    private static final OutstandingFees OUTSTANDING_FEES =
            new OutstandingFees(VALID_OUTSTANDING_FEES, LAST_ADDED_DATE);

    @Test
    public void toModelType_validOutstandingFeesDetails_returnOutstandingFees() throws IllegalValueException {
        JsonAdaptedFees outstandingFees = new JsonAdaptedFees(OUTSTANDING_FEES);
        assertEquals(OUTSTANDING_FEES, outstandingFees.toModelType());
    }

    @Test
    public void toModelType_invalidOutstandingFees_throwsIllegalValueException() {
        JsonAdaptedFees outstandingFees = new JsonAdaptedFees(INVALID_OUTSTANDING_FEES, VALID_LAST_ADDED_DATE);
        String expectedMessage = OutstandingFees.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, outstandingFees::toModelType);
    }

    @Test
    public void toModelType_nullOutstandingFees_throwsIllegalValueException() {
        JsonAdaptedFees outstandingFees = new JsonAdaptedFees(null, VALID_LAST_ADDED_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "value");
        assertThrows(IllegalValueException.class, expectedMessage, outstandingFees::toModelType);
    }

    @Test
    public void toModelType_invalidLastAddedDate_throwsIllegalValueException() {
        JsonAdaptedFees outstandingFees = new JsonAdaptedFees(VALID_OUTSTANDING_FEES, INVALID_LAST_ADDED_DATE);
        String expectedMessage = LastAddedDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, outstandingFees::toModelType);
    }

    @Test
    public void toModelType_nullLastAddedDate_throwsIllegalValueException() {
        JsonAdaptedFees outstandingFees = new JsonAdaptedFees(VALID_OUTSTANDING_FEES, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LastAddedDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, outstandingFees::toModelType);
    }
}
