package seedu.address.model.position;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Position(null));
    }

    @Test
    public void constructor_invalidPositionName_throwsIllegalArgumentException() {
        String invalidPositionName = "";
        assertThrows(IllegalArgumentException.class, () -> new Position(invalidPositionName));
    }

    @Test
    public void isValidPositionName() {
        // null position name
        assertThrows(NullPointerException.class, () -> Position.isValidPositionName(null));
    }

}
