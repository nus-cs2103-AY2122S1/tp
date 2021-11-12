package seedu.address.model.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class HourOfDayTest {
    @Test
    public void constructor_invalidHourOfDay_throwsIllegalArgumentException() {
        int outsideLowerBounds = -1;
        assertThrows(IllegalArgumentException.class, () -> new HourOfDay(outsideLowerBounds));

        int outsideUpperBounds = 24;
        assertThrows(IllegalArgumentException.class, () -> new HourOfDay(outsideUpperBounds));
    }

    // EP: invalid ranges: less than valid range and greater than valid range
    // test near the boundaries of equivalence partitions
    @Test
    public void validateHourOfDay_invalidHourOfDay_returnsFalse() {
        assertFalse(HourOfDay.isValidHourOfDay(-1));
        assertFalse(HourOfDay.isValidHourOfDay(-12));
        assertFalse(HourOfDay.isValidHourOfDay(24));
        assertFalse(HourOfDay.isValidHourOfDay(100));
    }

    // EP: within valid range
    @Test
    public void validateHourOfDay_validHourOfDays_returnsTrue() {
        assertTrue(HourOfDay.isValidHourOfDay(0));
        assertTrue(HourOfDay.isValidHourOfDay(23));
        assertTrue(HourOfDay.isValidHourOfDay(5));
        assertTrue(HourOfDay.isValidHourOfDay(8));
    }

    @Test
    public void equals() {
        HourOfDay instance = new HourOfDay(23);
        // same instance is equal
        assertEquals(instance, instance);
        // same hour field value is equal
        assertEquals(new HourOfDay(10), new HourOfDay(10));
        // different hour field value not equal
        assertNotEquals(new HourOfDay(10), new HourOfDay(9));
        // not equals to null
        assertNotEquals(new HourOfDay(10), null);
        // different type and different from int
        assertNotEquals(new HourOfDay(10), 10);
    }
}
