package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class LessonRatesTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LessonRates(null));
    }

    @Test
    public void constructor_invalidLessonRates_throwsIllegalArgumentException() {
        String invalidLessonRates = " ";
        assertThrows(IllegalArgumentException.class, () -> new LessonRates(invalidLessonRates));
    }

    @Test
    public void isValidLessonRates() {
        // null fee
        assertThrows(NullPointerException.class, () -> LessonRates.isValidLessonRates(null));

        // invalid fees
        assertFalse(LessonRates.isValidLessonRates("")); // empty string
        assertFalse(LessonRates.isValidLessonRates(" ")); // spaces only
        assertFalse(LessonRates.isValidLessonRates(".12")); // no dollars before decimal
        assertFalse(LessonRates.isValidLessonRates("50.")); // no cents after decimal
        assertFalse(LessonRates.isValidLessonRates("43..21")); // more than one decimal
        assertFalse(LessonRates.isValidLessonRates("3.999")); // more than two dp
        assertFalse(LessonRates.isValidLessonRates("a")); // non-numeric in dollars
        assertFalse(LessonRates.isValidLessonRates("a.a")); // non-numeric in dollars and cents
        assertFalse(LessonRates.isValidLessonRates("50cents")); // non-numeric with digits
        assertFalse(LessonRates.isValidLessonRates("99,999")); // commas within digits
        assertFalse(LessonRates.isValidLessonRates("10+10")); // non-numeric, non-period within digits
        assertFalse(LessonRates.isValidLessonRates("$50.05")); // starting with dollar sign
        assertFalse(LessonRates.isValidLessonRates("99 999")); // spaces within digits

        // valid fees
        assertTrue(LessonRates.isValidLessonRates("0")); // zero dollars
        assertTrue(LessonRates.isValidLessonRates("0.0")); // zero dollars and zero cents
        assertTrue(LessonRates.isValidLessonRates("123")); // only dollars
        assertTrue(LessonRates.isValidLessonRates("0.50")); // zero dollars and 50 cents
        assertTrue(LessonRates.isValidLessonRates("765.4")); // dollars and cents to 1 decimal places
        assertTrue(LessonRates.isValidLessonRates("99999.99")); // dollars and cents to 2 decimal places
        assertTrue(LessonRates.isValidLessonRates("00123.45")); // leading zeroes
        assertTrue(LessonRates.isValidLessonRates("124293842033123.99")); // large fee
    }
}
