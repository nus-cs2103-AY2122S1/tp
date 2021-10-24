package safeforhall.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static safeforhall.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CapacityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Capacity(null));
    }

    @Test
    public void constructor_invalidCapacity_throwsIllegalArgumentException() {
        String invalidCapacity = "";
        assertThrows(IllegalArgumentException.class, () -> new Capacity(invalidCapacity));
    }

    @Test
    public void isValidCapacity() {
        // null capacity
        assertThrows(NullPointerException.class, () -> Capacity.isValidCapacity(null));

        // blank capacity
        assertFalse(Capacity.isValidCapacity("")); // empty string
        assertFalse(Capacity.isValidCapacity(" ")); // spaces only

        // invalid capacity
        assertFalse(Capacity.isValidCapacity("-1"));
        assertFalse(Capacity.isValidCapacity("1000"));
        assertFalse(Capacity.isValidCapacity("3.5"));

        // valid capacity
        assertTrue(Capacity.isValidCapacity("1"));
        assertTrue(Capacity.isValidCapacity("3"));
        assertTrue(Capacity.isValidCapacity("300"));
    }
}
