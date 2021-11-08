package seedu.address.model.person.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MedicalHistoryTest {

    @Test
    public void testEquals() {
        MedicalHistory medicalHistory = new MedicalHistory("ADHD");

        // same object -> returns true
        assertTrue(medicalHistory.equals(medicalHistory));

        // same values -> returns true
        MedicalHistory medicalHistoryCopy = new MedicalHistory(medicalHistory.value);
        assertTrue(medicalHistory.equals(medicalHistoryCopy));

        // different types -> returns false
        assertFalse(medicalHistory.equals(1));

        // null -> returns false
        assertFalse(medicalHistory.equals(null));

        // different MedicalHistory -> returns false
        MedicalHistory differentMedicalHistory = new MedicalHistory("Autism");
        assertFalse(medicalHistory.equals(differentMedicalHistory));
    }
}
