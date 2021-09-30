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
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidBirthDate = "";
        assertThrows(IllegalArgumentException.class, () -> new BirthDate(invalidBirthDate));
    }

    @Test
    public void isValidBirthDate() {
        // null address
        assertThrows(NullPointerException.class, () -> BirthDate.isValidBirthDate(null));

//         invalid addresses
        assertFalse(BirthDate.isValidBirthDate("")); // empty string
        assertFalse(BirthDate.isValidBirthDate("2021-02-02")); // in YYYY-MM-DD format
        assertFalse(BirthDate.isValidBirthDate("12/31/2021")); // in MM/DD/YYYY format

//         valid addresses
        assertTrue(BirthDate.isValidBirthDate("2/02/2222"));
        assertTrue(BirthDate.isValidBirthDate("12/02/2021")); // one character
        assertTrue(BirthDate.isValidBirthDate("28/03/2021")); //
    }

    @Test
    public void calculate_age_success1() {
        BirthDate bd = new BirthDate("20/07/1999");
        System.out.println(bd.calculateAge());
        assertTrue(bd.calculateAge() == 22);
    }

    @Test
    public void calculate_age_success2() {
        BirthDate bd = new BirthDate("30/9/1999");
        System.out.println(bd.calculateAge());
        assertTrue(bd.calculateAge() == 22);
    }
}
