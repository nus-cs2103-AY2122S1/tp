package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.lesson.OutstandingFees.LastAddedDate;

class OutstandingFeesTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new OutstandingFees(null));
        assertThrows(NullPointerException.class, () ->
                new OutstandingFees(null, null));
    }

    @Test
    public void constructor_invalidFee_throwsIllegalArgumentException() {
        String invalidOutstandingFees = " ";

        assertThrows(IllegalArgumentException.class, () -> new OutstandingFees(invalidOutstandingFees));
        assertThrows(IllegalArgumentException.class, () ->
                new OutstandingFees(invalidOutstandingFees, new LastAddedDate("2021-10-23")));
    }



    @Test
    public void isValidOutstandingFees() {
        // null fee
        assertThrows(NullPointerException.class, () -> OutstandingFees.isValidOutstandingFee(null));

        // invalid fees
        assertFalse(OutstandingFees.isValidOutstandingFee(" ")); // spaces only
        assertFalse(OutstandingFees.isValidOutstandingFee(".12")); // no dollars before decimal
        assertFalse(OutstandingFees.isValidOutstandingFee("50.")); // no cents after decimal
        assertFalse(OutstandingFees.isValidOutstandingFee("43..21")); // more than one decimal
        assertFalse(OutstandingFees.isValidOutstandingFee("3.999")); // more than two dp
        assertFalse(OutstandingFees.isValidOutstandingFee("a")); // non-numeric in dollars
        assertFalse(OutstandingFees.isValidOutstandingFee("a.a")); // non-numeric in dollars and cents
        assertFalse(OutstandingFees.isValidOutstandingFee("50cents")); // non-numeric with digits
        assertFalse(OutstandingFees.isValidOutstandingFee("99,999")); // commas within digits
        assertFalse(OutstandingFees.isValidOutstandingFee("10+10")); // non-numeric, non-period within digits
        assertFalse(OutstandingFees.isValidOutstandingFee("$50.05")); // starting with dollar sign
        assertFalse(OutstandingFees.isValidOutstandingFee("99 999")); // spaces within digits

        // valid fees
        assertTrue(OutstandingFees.isValidOutstandingFee("")); // empty string
        assertTrue(OutstandingFees.isValidOutstandingFee("0")); // zero dollars
        assertTrue(OutstandingFees.isValidOutstandingFee("0.0")); // zero dollars and zero cents
        assertTrue(OutstandingFees.isValidOutstandingFee("123")); // only dollars
        assertTrue(OutstandingFees.isValidOutstandingFee("0.50")); // zero dollars and 50 cents
        assertTrue(OutstandingFees.isValidOutstandingFee("765.4")); // dollars and cents to 1 decimal places
        assertTrue(OutstandingFees.isValidOutstandingFee("99999.99")); // dollars and cents to 2 decimal places
        assertTrue(OutstandingFees.isValidOutstandingFee("00123.45")); // leading zeroes
        assertTrue(OutstandingFees.isValidOutstandingFee("124293842033123.99")); // large fee
    }

    @Test
    public void lastAddedDate_constructorNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LastAddedDate(null));
    }

    @Test
    public void lastAdded_factoryMethod() {
        String testDate = LocalDate.now().toString();
        LastAddedDate expectedLastAdded = new LastAddedDate(testDate);
        assertEquals(expectedLastAdded, LastAddedDate.of());
    }

    @Test
    public void isValidLastAddedDate() {
        // null date
        assertThrows(NullPointerException.class, () -> LastAddedDate.isValidLastAddedDate(null));

        // invalid date
        assertFalse(LastAddedDate.isValidLastAddedDate("")); // empty string
        assertFalse(LastAddedDate.isValidLastAddedDate(" ")); // spaced only
        assertFalse(LastAddedDate.isValidLastAddedDate("2021-02-31")); // no such date
        assertFalse(LastAddedDate.isValidLastAddedDate("2021-13-23")); // invalid month

        //valid date
        assertTrue(LastAddedDate.isValidLastAddedDate("2020-02-29")); // leap year
        assertTrue(LastAddedDate.isValidLastAddedDate("2021-10-23"));;
    }
}
