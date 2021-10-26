package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class LastUpdatedDateTest {
    @Test
    public void lastUpdatedDate_constructorNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LastUpdatedDate(null));
    }

    @Test
    public void isValidLastUpdatedDate() {
        // null date
        assertThrows(NullPointerException.class, () -> LastUpdatedDate.isValidLastUpdatedDate(null));

        // invalid date
        assertFalse(LastUpdatedDate.isValidLastUpdatedDate("")); // empty string
        assertFalse(LastUpdatedDate.isValidLastUpdatedDate(" ")); // spaced only
        assertFalse(LastUpdatedDate.isValidLastUpdatedDate("2021-02-31")); // no such date
        assertFalse(LastUpdatedDate.isValidLastUpdatedDate("2021-13-23")); // invalid month

        //valid date
        assertTrue(LastUpdatedDate.isValidLastUpdatedDate("2020-02-29")); // leap year
        assertTrue(LastUpdatedDate.isValidLastUpdatedDate("2021-10-23"));;
    }
}
