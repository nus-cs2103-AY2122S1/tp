package tutoraid.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import tutoraid.testutil.Assert;

public class CapacityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Capacity(null));
    }

    @Test
    public void constructor_negativeCapacity_throwsIllegalArgumentException() {
        String negativeCapacity = "-20";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Capacity(negativeCapacity));
    }

    @Test
    public void constructor_zeroCapacity_throwsIllegalArgumentException() {
        String zeroCapacity = "0";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Capacity(zeroCapacity));
    }

    @Test
    public void isValidCapacity() {
        // null Capacity number
        Assert.assertThrows(NullPointerException.class, () -> Capacity.isValidCapacity(null));

        // invalid Capacity numbers
        assertFalse(Capacity.isValidCapacity("")); // empty string
        assertFalse(Capacity.isValidCapacity(" ")); // spaces only
        assertFalse(Capacity.isValidCapacity("Capacity")); // non-numeric
        assertFalse(Capacity.isValidCapacity("9011p041")); // alphabets within digits
        assertFalse(Capacity.isValidCapacity("9312 1534")); // spaces within digits

        // valid Capacity numbers
        assertTrue(Capacity.isValidCapacity("911")); // exactly 3 numbers
        assertTrue(Capacity.isValidCapacity("93121534"));
        assertTrue(Capacity.isValidCapacity("124293842033123")); // long Capacity numbers
    }
}
