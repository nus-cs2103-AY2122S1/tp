package safeforhall.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static safeforhall.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LastDateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LastDate(null));
    }

    @Test
    public void isValidDate() {
        // null dates
        assertThrows(NullPointerException.class, () -> new LastDate(null));

        // invalid dates
        //assertFalse(LastDate.isValidDate(""));
        assertFalse(LastDate.isValidDate("10.10.2021"));
        assertFalse(LastDate.isValidDate("10/10/2021"));
        assertFalse(LastDate.isValidDate("9-9-2021"));
        assertFalse(LastDate.isValidDate("19-9-2021"));

        // valid dates
        assertTrue(LastDate.isValidDate("21-10-2021"));
    }
}
