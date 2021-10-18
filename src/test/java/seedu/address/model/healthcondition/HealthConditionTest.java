package seedu.address.model.healthcondition;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class HealthConditionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new HealthCondition(null));
    }

    @Test
    public void constructor_invalidHealthCondition_throwsIllegalArgumentException() {
        String invalidHealthCondition = "";
        assertThrows(IllegalArgumentException.class, () -> new HealthCondition(invalidHealthCondition));
    }

    @Test
    public void isValidHealthCondition() {
        // null tag name
        assertThrows(NullPointerException.class, () -> HealthCondition.isValidHealthCondition(null));
    }

}
