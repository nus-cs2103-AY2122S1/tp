package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RiskAppetiteTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RiskAppetite(null));
    }

    @Test
    public void constructor_invalidRiskAppetite_throwsIllegalArgumentException() {
        String invalidRiskAppetite = "9";
        assertThrows(IllegalArgumentException.class, () -> new RiskAppetite(invalidRiskAppetite));
    }

    @Test
    public void isValidRiskAppetite() {
        // null phone number
        assertThrows(NullPointerException.class, () -> RiskAppetite.isValidRiskAppetite(null));

        // invalid phone numbers
        assertFalse(RiskAppetite.isValidRiskAppetite(" ")); // spaces only
        assertFalse(RiskAppetite.isValidRiskAppetite("91")); // integer not within 1 and 5
        assertFalse(RiskAppetite.isValidRiskAppetite("risk")); // non-numeric
        assertFalse(RiskAppetite.isValidRiskAppetite("9011p041")); // alphabets within digits
        assertFalse(RiskAppetite.isValidRiskAppetite("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(RiskAppetite.isValidRiskAppetite("2")); // Integer between 1 and 5
        assertTrue(RiskAppetite.isValidRiskAppetite("5")); // Integer between 1 and 5
    }

    @Test
    public void equals() {
        RiskAppetite john = new RiskAppetite("5");
        RiskAppetite otherJohn = new RiskAppetite("5");
        RiskAppetite jane = new RiskAppetite("3");

        // same object
        assertTrue(john.equals(john));

        // different object same RiskAppetite
        assertTrue(john.equals(otherJohn));
        assertEquals(john.hashCode(), otherJohn.hashCode());

        // different object different RiskAppetite
        assertFalse(john.equals(jane));

        // different type
        assertFalse(john.equals("5"));

    }
}
