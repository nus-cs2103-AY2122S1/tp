package seedu.plannermd.model.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class RiskTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Risk(null));
    }

    @Test
    public void constructor_invalidRisk_throwsIllegalArgumentException() {
        String invalidRisk = "";
        assertThrows(IllegalArgumentException.class, () -> new Risk(invalidRisk));
    }

    @Test
    public void isValidRisk() {
        // null risk
        assertThrows(NullPointerException.class, () -> Risk.isValidRisk(null));

        // invalid risks
        assertFalse(Risk.isValidRisk("")); // empty string
        assertFalse(Risk.isValidRisk(" ")); // spaces only

        // valid risks
        assertTrue(Risk.isValidRisk("HIGH"));
        assertTrue(Risk.isValidRisk("MEDIUM"));
        assertTrue(Risk.isValidRisk("LOW"));
    }

    @Test
    public void getUnclassifiedRisk() {
        // method returns correct risk level
        assertEquals(Risk.RiskLevel.UNCLASSIFIED, Risk.getUnclassifiedRisk().riskLevel);
    }

    @Test
    public void isUnclassified() {
        Risk highRisk = new Risk("HIGH");
        Risk mediumRisk = new Risk("MEDIUM");
        Risk lowRisk = new Risk("LOW");
        Risk unclassifiedRisk = Risk.getUnclassifiedRisk();

        assertFalse(highRisk.isUnclassified());
        assertFalse(mediumRisk.isUnclassified());
        assertFalse(lowRisk.isUnclassified());
        assertTrue(unclassifiedRisk.isUnclassified());
    }
}
