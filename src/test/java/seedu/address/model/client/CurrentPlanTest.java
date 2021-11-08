package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CurrentPlanTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CurrentPlan(null));
    }

    @Test
    public void constructor_invalidCurrentPlan_throwsIllegalArgumentException() {
        String invalidCurrentPlan = " ";
        assertThrows(IllegalArgumentException.class, () -> new CurrentPlan(invalidCurrentPlan));
    }

    @Test
    public void isCurrentPlan() {
        // null address
        assertThrows(NullPointerException.class, () -> CurrentPlan.isValidCurrentPlan(null));

        // invalid addresses
        assertFalse(CurrentPlan.isValidCurrentPlan(" ")); // spaces only
        assertFalse(CurrentPlan.isValidCurrentPlan("Very very very very very long very very very very very long "
            + " very very very very long  very very very very long  very very very very long  very very very very long "
            + " very very very very long  very very very very long ")); // very long address, exceed char limit (100)

        // valid addresses
        assertTrue(CurrentPlan.isValidCurrentPlan("")); // empty string
        assertTrue(CurrentPlan.isValidCurrentPlan("Prudential Proshield")); //Any string
        assertTrue(CurrentPlan.isValidCurrentPlan("-")); // one character
        assertTrue(CurrentPlan.isValidCurrentPlan("Prduential Long String Financial Plan")); // long string
    }

    @Test
    public void equals() {
        CurrentPlan john = new CurrentPlan("John St");
        CurrentPlan otherJohn = new CurrentPlan("John St");
        CurrentPlan jane = new CurrentPlan("Jane St");

        // same object
        assertTrue(john.equals(john));

        // different object same plans
        assertTrue(john.equals(otherJohn));
        assertEquals(john.hashCode(), otherJohn.hashCode());

        // different object different plans
        assertFalse(john.equals(jane));

        // different type
        assertFalse(john.equals("john"));
    }
}
