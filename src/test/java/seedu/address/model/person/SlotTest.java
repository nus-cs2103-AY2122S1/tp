package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SlotTest {

    private static final Slot MORNING = Slot.MORNING;
    private static final Slot AFTERNOON = Slot.AFTERNOON;

    private static final String MORNING_LOWERCASE = "morning";
    private static final String MORNING_UPPERCASE = "MORNING";
    private static final String MORNING_START_WITH_UPPERCASE = "Morning";
    private static final String AFTERNOON_LOWERCASE = "afternoon";
    private static final String AFTERNOON_UPPERCASE = "AFTERNOON";
    private static final String AFTERNOON_START_WITH_UPPERCASE = "Afternoon";

    @Test
    public void getValueTest() {
        assertEquals(MORNING_LOWERCASE, MORNING.getValue());
        assertEquals(AFTERNOON_LOWERCASE, AFTERNOON.getValue());
        assertNotEquals(MORNING_UPPERCASE, MORNING.getValue());
        assertNotEquals(AFTERNOON_UPPERCASE, AFTERNOON.getValue());
        assertNotEquals(MORNING_START_WITH_UPPERCASE, MORNING.getValue());
        assertNotEquals(AFTERNOON_START_WITH_UPPERCASE, AFTERNOON.getValue());
    }

    @Test
    public void getOrderTest() {
        assertEquals(0, MORNING.getOrder());
        assertEquals(1, AFTERNOON.getOrder());
    }

    @Test
    public void translateStringToSlotTest() {
        assertEquals(MORNING, Slot.translateStringToSlot(MORNING_LOWERCASE));
        assertEquals(MORNING, Slot.translateStringToSlot(MORNING_START_WITH_UPPERCASE));
        assertEquals(MORNING, Slot.translateStringToSlot(MORNING_UPPERCASE));
        assertEquals(MORNING, Slot.translateStringToSlot("   " + MORNING_LOWERCASE + "   "));
        assertEquals(AFTERNOON, Slot.translateStringToSlot(AFTERNOON_LOWERCASE));
    }

    @Test
    public void getSlotByOrderTest() {
        assertEquals(MORNING, Slot.getSlotByOrder("0"));
        assertEquals(AFTERNOON, Slot.getSlotByOrder("1"));
    }

    @Test
    public void isValidSlotTest() {
        assertTrue(Slot.isValidSlot(MORNING_LOWERCASE));
        assertTrue(Slot.isValidSlot("   " + MORNING_LOWERCASE + "   "));
        assertTrue(Slot.isValidSlot(AFTERNOON_LOWERCASE));

        assertFalse(Slot.isValidSlot(MORNING_START_WITH_UPPERCASE));
        assertFalse(Slot.isValidSlot(""));
        assertFalse(Slot.isValidSlot("random text"));
    }
}
