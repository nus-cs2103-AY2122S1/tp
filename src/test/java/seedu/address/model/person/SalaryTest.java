package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SalaryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Salary(null));
    }

    @Test
    public void constructor_invalidSalary_throwsIllegalArgumentException() {
        String invalidSalary = "";
        assertThrows(IllegalArgumentException.class, () -> new Salary(invalidSalary));
    }

    @Test
    public void constructor() {
        assertEquals(new Salary("0").toString(), "0.00");
        assertEquals(new Salary("1.99").toString(), "1.99");
        assertEquals(new Salary("1.9").toString(), "1.90");
        // Removed because Salary can now only cents up to two digits
        // assertEquals(new Salary("1.999").toString(), "1.99");
    }

    @Test
    public void isValidDollars() {
        // null dollars
        assertThrows(NullPointerException.class, () -> Salary.isValidSalary(null));

        // invalid dollars
        assertFalse(Salary.isValidDollars("")); // empty string
        assertFalse(Salary.isValidDollars(" ")); // spaces only
        assertFalse(Salary.isValidDollars("a lot")); // non-numeric string
        assertFalse(Salary.isValidDollars("-500")); // negative dollar value

        // valid dollars
        assertTrue(Salary.isValidDollars("109"));
        assertTrue(Salary.isValidDollars("0"));
    }

    @Test
    public void isValidCents() {
        // null cents
        assertThrows(NullPointerException.class, () -> Salary.isValidSalary(null));

        // invalid cents
        assertFalse(Salary.isValidCents("")); // empty string
        assertFalse(Salary.isValidCents(" ")); // spaces only
        assertFalse(Salary.isValidCents("a lot")); // non-numeric string
        assertFalse(Salary.isValidCents("-500")); // negative cents value

        // valid cents
        assertTrue(Salary.isValidCents("1")); // 1 decimal place
        // Removed because Salary can now only cents up to two digits
        //assertTrue(Salary.isValidCents("00001")); // multiple decimal places (> 2)
        assertTrue(Salary.isValidCents("01"));
    }

    @Test
    public void isValidSalary() {
        // null salary
        assertThrows(NullPointerException.class, () -> Salary.isValidSalary(null));

        // invalid salary
        assertFalse(Salary.isValidSalary("")); // empty string
        assertFalse(Salary.isValidSalary(" ")); // spaces only
        assertFalse(Salary.isValidSalary("a lot")); // non-numeric string
        assertFalse(Salary.isValidSalary("-500")); // negative dollar value
        assertFalse(Salary.isValidSalary("0.-11")); // negative cents value
        assertFalse(Salary.isValidSalary(".999")); // empty input for dollars
        assertFalse(Salary.isValidSalary("1.")); // empty input for cents
        assertFalse(Salary.isValidSalary(".")); // empty input for both dollars and cents

        // valid salary
        assertTrue(Salary.isValidSalary("1.99"));
        // Removed because Salary can now only cents up to two digits
        // assertTrue(Salary.isValidSalary("1.999")); // decimal places > 2
        assertTrue(Salary.isValidSalary("1.9")); // 1 decimal place
        assertTrue(Salary.isValidSalary("0"));
    }

    @Test
    public void convertToDollars() {
        // rounding correctly
        Salary shouldNotRoundUp = new Salary("1.50");
        assertEquals("$1.50", shouldNotRoundUp.convertToDollars());

        // salary with single digit cents displayed correctly
        Salary singleDigitCents = new Salary("1.09");
        assertEquals("$1.09", singleDigitCents.convertToDollars());

        // salary with no cents is displayed correctly
        Salary zeroDigitCents = new Salary("1.00");
        assertEquals("$1.00", zeroDigitCents.convertToDollars());
    }
}
