package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InsuranceTest {
    @Test
    public void of_invalidInsurance_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Insurance.of("Money"));
        assertThrows(IllegalArgumentException.class, () -> Insurance.of("Hospital"));
    }

    @Test
    public void of() {
        assertEquals(new Insurance(InsuranceType.GENERAL), Insurance.of("General"));
        assertEquals(new Insurance(InsuranceType.LIFE), Insurance.of("Life"));
        assertEquals(new Insurance(InsuranceType.HEALTH), Insurance.of("Health"));

        assertEquals(new Insurance(InsuranceType.GENERAL).hashCode(),
                Insurance.of("General").hashCode());
        assertEquals(new Insurance(InsuranceType.LIFE).hashCode(),
                Insurance.of("Life").hashCode());
        assertEquals(new Insurance(InsuranceType.HEALTH).hashCode(),
                Insurance.of("Health").hashCode());

        assertEquals(new Insurance(InsuranceType.GENERAL), Insurance.of("General"));
        assertEquals(new Insurance(InsuranceType.GENERAL), Insurance.of("GENERAL"));
        assertEquals(new Insurance(InsuranceType.GENERAL), Insurance.of("general"));
        assertEquals(new Insurance(InsuranceType.GENERAL), Insurance.of("gENeRal"));
    }
}
