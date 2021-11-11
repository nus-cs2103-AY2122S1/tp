package seedu.intern.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intern.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GraduationYearMonthTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GraduationYearMonth(null));
    }

    @Test
    public void constructor_invalidGraduationYearMonth_throwsIllegalArgumentException() {
        String invalidGraduationYearMonth = "";
        assertThrows(IllegalArgumentException.class, () -> new GraduationYearMonth(invalidGraduationYearMonth));
    }

    @Test
    public void isValidGraduationYearMonth() {
        // null graduation year and month
        assertThrows(NullPointerException.class, () -> GraduationYearMonth.isValidGraduationYearMonth(null));

        // invalid graduation year and month
        assertFalse(GraduationYearMonth.isValidGraduationYearMonth("")); // empty string
        assertFalse(GraduationYearMonth.isValidGraduationYearMonth(" ")); // spaces only
        assertFalse(GraduationYearMonth.isValidGraduationYearMonth("91")); // not in MM/yyyy format
        assertFalse(GraduationYearMonth.isValidGraduationYearMonth("May 2023")); // non-numeric
        assertFalse(GraduationYearMonth.isValidGraduationYearMonth("09/")); // missing year
        assertFalse(GraduationYearMonth.isValidGraduationYearMonth("/2022")); // missing year
        assertFalse(GraduationYearMonth.isValidGraduationYearMonth("09-2024")); // hyphen between numbers
        assertFalse(GraduationYearMonth.isValidGraduationYearMonth("00/2024")); // invalid month
        assertFalse(GraduationYearMonth.isValidGraduationYearMonth("01/1500")); // year is below lower boundary

        // valid graduation year and month
        assertTrue(GraduationYearMonth.isValidGraduationYearMonth("05/2021")); // exactly 3 numbers
        assertTrue(GraduationYearMonth.isValidGraduationYearMonth("12/2024"));
        assertTrue(GraduationYearMonth.isValidGraduationYearMonth("10/2025")); // long phone numbers
    }
}
