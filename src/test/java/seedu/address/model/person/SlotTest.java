package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SlotTest {

    private static final Slot MORNING = Slot.MORNING;
    private static final Slot AFTERNOON = Slot.AFTERNOON;

    @Test
    public void getValueTest() {
        assertEquals("morning", MORNING.getValue());
        assertEquals("afternoon", AFTERNOON.getValue());
        assertNotEquals("Morning", MORNING.getValue());
        assertNotEquals("Afternoon", AFTERNOON.getValue());
    }

    @Test
    public void getOrderTest() {
        assertEquals(0, MORNING.getOrder());
        assertEquals(1, AFTERNOON.getOrder());
    }

    @Test
    public void translateStringToSlotTest() {
        assertEquals(MORNING, Slot.translateStringToSlot("morning"));
        assertEquals(MORNING, Slot.translateStringToSlot("Morning"));
        assertEquals(MORNING, Slot.translateStringToSlot("MORNING"));
        assertEquals(MORNING, Slot.translateStringToSlot("    morning   "));
        assertEquals(AFTERNOON, Slot.translateStringToSlot("afternoon"));
    }

    @Test
    public void getSlotByOrderTest() {
        assertEquals(MORNING, Slot.getSlotByOrder("0"));
        assertEquals(AFTERNOON, Slot.getSlotByOrder("1"));
    }

    @Test
    public void isValidSlotTest() {
        assertTrue(Slot.isValidSlot("morning"));
        assertTrue(Slot.isValidSlot("  morning   "));
        assertTrue(Slot.isValidSlot("afternoon"));

        assertFalse(Slot.isValidSlot("Morning"));
        assertFalse(Slot.isValidSlot(""));
        assertFalse(Slot.isValidSlot("random text"));
    }
}
