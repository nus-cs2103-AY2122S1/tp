package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class MeasurementTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Measurement(null));
    }

    @Test
    public void constructor_invalidMeasurement_throwsIllegalArgumentException() {
        String measurement = "hello";
        assertThrows(IllegalArgumentException.class, () -> new Measurement(measurement));
    }

    @Test
    public void isValidMeasurement() {
        // null measurement
        assertThrows(NullPointerException.class, () -> Measurement.isValidMeasurement(null));

        // invalid measurement
        assertFalse(Measurement.isValidMeasurement("happy")); // only non-alphanumeric characters
        assertFalse(Measurement.isValidMeasurement("100_50")); // miss one argument
        assertFalse(Measurement.isValidMeasurement("100_60_hi")); // not integer

        // valid measurement
        assertTrue(Measurement.isValidMeasurement("170_60_80"));
        assertTrue(Measurement.isValidMeasurement("170_60_80_90"));
    }

    @Test
    public void isValidMeasurement_genderSpecific() {
        // null measurement
        assertThrows(NullPointerException.class, () -> Measurement.isValidMeasurement(null, GenderType.MALE));
        assertThrows(NullPointerException.class, () -> Measurement.isValidMeasurement(null, GenderType.FEMALE));

        // invalid measurement
        assertFalse(Measurement.isValidMeasurement("100_50_60_65", GenderType.MALE));
        assertFalse(Measurement.isValidMeasurement("100_50_70", GenderType.FEMALE));

        // valid measurement
        assertTrue(Measurement.isValidMeasurement("170_60_80", GenderType.MALE));
        assertTrue(Measurement.isValidMeasurement("170_60_80_90", GenderType.FEMALE));
    }

    @Test
    public void equal() {
        Measurement measurement = new Measurement("100_40_50");

        // same object -> returns true
        assertTrue(measurement.equals(measurement));

        // same values -> returns true
        Measurement measurementCopy = new Measurement(measurement.value);
        assertTrue(measurement.equals(measurementCopy));

        // different types -> returns false
        assertFalse(measurement.equals(1));

        // null -> returns false
        assertFalse(measurement.equals(null));

        // different measurement -> returns false
        Measurement differentMeasurement = new Measurement("100_40_60");
        assertFalse(measurement.equals(differentMeasurement));
    }

}
