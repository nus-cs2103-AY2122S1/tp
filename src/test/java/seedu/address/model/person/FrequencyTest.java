package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FrequencyTest {
    private final Visit VISIT = new Visit("2022-01-01 12:00");

    @Test
    public void find_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Frequency.find(null));
    }

    @Test
    public void find_invalidFrequency_throwsNullPointerException() {
        String invalidLanguage = "random";
        assertThrows(IllegalArgumentException.class, () -> Frequency.find(invalidLanguage));
    }

    @Test
    public void isValidFrequency() {
        // null frequency
        assertThrows(NullPointerException.class, () -> Frequency.isValidFrequency(null));

        // invalid frequency
        assertFalse(Frequency.isValidFrequency(" ")); // spaces only

        // valid frequency
        assertTrue(Frequency.isValidFrequency("daily"));
        assertTrue(Frequency.isValidFrequency("weekly"));
        assertTrue(Frequency.isValidFrequency("biweekly"));
        assertTrue(Frequency.isValidFrequency("monthly"));
        assertTrue(Frequency.isValidFrequency("quarterly"));
    }

    @Test
    public void nextVisit_daily() {
        Frequency f = Frequency.DAILY;
        Visit nextVisit = new Visit("2022-01-02 12:00");
        assertEquals(f.nextVisit(VISIT), nextVisit);
    }

    @Test
    public void nextVisit_weekly() {
        Frequency f = Frequency.WEEKLY;
        Visit nextVisit = new Visit("2022-01-08 12:00");
        assertEquals(f.nextVisit(VISIT), nextVisit);
    }

    @Test
    public void nextVisit_biweekly() {
        Frequency f = Frequency.BIWEEKLY;
        Visit nextVisit = new Visit("2022-01-15 12:00");
        assertEquals(f.nextVisit(VISIT), nextVisit);
    }

    @Test
    public void nextVisit_monthly() {
        Frequency f = Frequency.MONTHLY;
        Visit nextVisit = new Visit("2022-02-01 12:00");
        assertEquals(f.nextVisit(VISIT), nextVisit);
    }

    @Test
    public void nextVisit_quarterly() {
        Frequency f = Frequency.QUARTERLY;
        Visit nextVisit = new Visit("2022-04-01 12:00");
        assertEquals(f.nextVisit(VISIT), nextVisit);
    }

    @Test
    public void nextVisit_empty() {
        Frequency f = Frequency.EMPTY;
        Visit nextVisit = new Visit("");
        assertEquals(f.nextVisit(VISIT), nextVisit);
    }
}
