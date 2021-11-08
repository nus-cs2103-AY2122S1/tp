package seedu.address.model.person.supplier;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_DELIVERY_DETAIL_AMY;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_DELIVERY_DETAIL_BOB;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeliveryDetailsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeliveryDetails(null));
    }

    @Test
    public void constructor_invalidDeliveryDetail_throwsIllegalArgumentException() {
        String invalidDeliveryDetail = "";
        assertThrows(IllegalArgumentException.class, () -> new DeliveryDetails(invalidDeliveryDetail));
    }

    @Test
    public void isValidDeliveryDetail() {
        // null name
        assertThrows(NullPointerException.class, () -> DeliveryDetails.isValidDeliveryDetail(null));

        // invalid delivery details
        assertFalse(DeliveryDetails.isValidDeliveryDetail("")); // empty string
        assertFalse(DeliveryDetails.isValidDeliveryDetail(" ")); // spaces only
        assertFalse(DeliveryDetails.isValidDeliveryDetail("Hello")); // Invalid characters to parse
        // only enter date in yyyy-MM-dd format
        assertFalse(DeliveryDetails.isValidDeliveryDetail("2021-12-25"));
        //only enter date in dd-MM-yyyy format
        assertFalse(DeliveryDetails.isValidDeliveryDetail("26-12-2021"));
        //enter date in some other format
        assertFalse(DeliveryDetails.isValidDeliveryDetail("26-2021-21"));
        // only enter time in 12hr format
        assertFalse(DeliveryDetails.isValidDeliveryDetail("12:00 AM"));
        //only enter time in 24hr format with colon
        assertFalse(DeliveryDetails.isValidDeliveryDetail("12:00"));
        //only enter time in 24hr format without colon
        assertFalse(DeliveryDetails.isValidDeliveryDetail("1200"));
        // invalid time format
        assertFalse(DeliveryDetails.isValidDeliveryDetail("12:00 am 2021-12-25"));
        //invalid date
        assertFalse(DeliveryDetails.isValidDeliveryDetail("12:00 AM 2021-13-25"));
        // invalid 12 hr clock format followed by valid yyyy-MM-dd date format
        assertFalse(DeliveryDetails.isValidDeliveryDetail("12:00 AM 2021-12-25"));
        // invalid 12 hr clock format followed by valid dd-MM-yyyy date format
        assertFalse(DeliveryDetails.isValidDeliveryDetail("1:00 PM 26-12-2021"));
        // yyyy-MM-dd date format followed by invalid 12 hr clock format
        assertFalse(DeliveryDetails.isValidDeliveryDetail("2021-12-25 12:00 AM"));
        // dd-MM-yyyy date format followed by invalid 12 hr clock format
        assertFalse(DeliveryDetails.isValidDeliveryDetail("26-12-2021 1:00 PM"));

        // valid delivery details
        // valid 24 hr clock format followed by valid yyyy-MM-dd date format
        assertTrue(DeliveryDetails.isValidDeliveryDetail("0800 2021-01-15"));
        // valid 24 hr clock format followed by valid dd-MM-yyyy date format
        assertTrue(DeliveryDetails.isValidDeliveryDetail("0630 14-02-2021"));
        // valid yyyy-MM-dd date format followed by valid 24 hr clock format
        assertTrue(DeliveryDetails.isValidDeliveryDetail("2021-01-15 0800"));
        // valid dd-MM-yyyy date format followed by valid 24 hr clock format
        assertTrue(DeliveryDetails.isValidDeliveryDetail("14-02-2021 0630"));
        // valid 24 hr clock format with colon followed by valid yyyy-MM-dd date format
        assertTrue(DeliveryDetails.isValidDeliveryDetail("12:00 2021-12-25"));
        // valid 24 hr clock format with colon followed by valid dd-MM-yyyy date format
        assertTrue(DeliveryDetails.isValidDeliveryDetail("13:00 26-12-2021"));
        // yyyy-MM-dd date format followed by valid 24 hr clock format with colon
        assertTrue(DeliveryDetails.isValidDeliveryDetail("2021-12-25 12:00"));
        // dd-MM-yyyy date format followed by valid 24 hr clock format with colon
        assertTrue(DeliveryDetails.isValidDeliveryDetail("26-12-2021 13:00"));
    }

    @Test
    public void equals() {
        DeliveryDetails deliveryDetails = new DeliveryDetails(VALID_DELIVERY_DETAIL_AMY);

        // same values -> returns true
        DeliveryDetails toCopy = new DeliveryDetails(VALID_DELIVERY_DETAIL_AMY);
        assertTrue(deliveryDetails.equals(toCopy));

        // same object -> returns true
        assertTrue(deliveryDetails.equals(deliveryDetails));

        // null -> returns false
        assertFalse(deliveryDetails.equals(null));

        // different type -> returns false
        assertFalse(deliveryDetails.equals(5));

        // different DeliveryDetails -> returns false
        DeliveryDetails different = new DeliveryDetails(VALID_DELIVERY_DETAIL_BOB);
        assertFalse(deliveryDetails.equals(different));

    }
}
