package seedu.plannermd.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BirthDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BirthDate(null));
    }

    @Test
    public void constructor_invalidBirthDate_throwsIllegalArgumentException() {
        String invalidBirthDate = "";
        assertThrows(IllegalArgumentException.class, () -> new BirthDate(invalidBirthDate));
    }

    @Test
    public void isValidBirthDate_validBirthDate_success() {
        assertTrue(BirthDate.isValidBirthDate("12/2/2021")); // one character
        assertTrue(BirthDate.isValidBirthDate("28/03/2021"));
        assertTrue(BirthDate.isValidBirthDate("3/3/2021"));
    }
    @Test
    public void isValidBirthDate_invalidBirthDate_failure() {
        assertFalse(BirthDate.isValidBirthDate("")); // empty string
        assertFalse(BirthDate.isValidBirthDate("2021-02-02")); // in YYYY-MM-DD format
        assertFalse(BirthDate.isValidBirthDate("12/31/2021")); // in MM/DD/YYYY format
        assertFalse(BirthDate.isValidBirthDate("28/03/2022")); // birth date can't be in the future
        assertFalse(BirthDate.isValidBirthDate("28/03/2022 2222"));

    }

    @Test
    public void isValidBirthDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> BirthDate.isValidBirthDate(null));
    }

    @Test
    public void calculateAge_validAge_returnsCorrectAge() {
        BirthDate bd = new BirthDate("20/07/1999");
        assertTrue(bd.calculateAge() == 22);

        bd = new BirthDate("30/9/1999");
        assertTrue(bd.calculateAge() == 22);
    }
}
