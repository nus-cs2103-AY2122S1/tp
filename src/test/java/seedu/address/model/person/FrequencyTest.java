package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FrequencyTest {
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

        // blank frequency
        assertFalse(Frequency.isValidFrequency(" ")); // spaces only

        // valid frequency
        assertTrue(Frequency.isValidFrequency("daily"));
        assertTrue(Frequency.isValidFrequency("weekly"));
        assertTrue(Frequency.isValidFrequency("biweekly"));
        assertTrue(Frequency.isValidFrequency("monthly"));
        assertTrue(Frequency.isValidFrequency("quarterly"));
    }
}
