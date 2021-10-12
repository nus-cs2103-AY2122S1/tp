package seedu.unify.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unify.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid dates
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only
        assertFalse(Date.isValidDate("-")); // one character

        // valid dates
        assertTrue(Date.isValidDate("2021-12-11"));
        assertTrue(Date.isValidDate("2022-12-11")); // date further from now
    }

    @Test
    public void getDate() {
        Date validDate = new Date("2020-01-08");

        // valid date formats since invalid formats will be rejected at constructor
        LocalDate test1 = LocalDate.parse("2020-01-08");
        System.out.println(validDate.getDate().equals(test1));
    }
}
